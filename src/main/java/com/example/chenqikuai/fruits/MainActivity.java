package com.example.chenqikuai.fruits;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.chenqikuai.fruits.my.My;
import com.example.chenqikuai.fruits.search.Search;
import com.example.chenqikuai.fruits.square.Square;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new MyDatabaseHelper(this, "FruitApp.db", null, 1);
        dbHelper.getWritableDatabase();


        Search search = new Search();
        getFragmentManager().beginTransaction().replace(R.id.main_layout, search).commit();

        //上面一栏的显示
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.menu);
        }

        //左侧栏的菜单显示
        navView.setCheckedItem(R.id.nav_search);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                mDrawerLayout.closeDrawers();
                if (menuItem.getTitle().equals("搜索")) {
                    Search search = new Search();
                    getFragmentManager().beginTransaction().replace(R.id.main_layout, search).commit();
                } else if (menuItem.getTitle().equals("广场")) {
                    Square square = new Square();
                    getFragmentManager().beginTransaction().replace(R.id.main_layout, square).commit();
                } else if (menuItem.getTitle().equals("我的")) {
                    My my = new My();
                    getFragmentManager().beginTransaction().replace(R.id.main_layout, my).commit();
                }
                return true;
            }
        });

        //权限申请
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.INTERNET}, 1);
        } else {
//            initFruits();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("message", "main activity destroied");
    }

    //选择菜单后回调该函数
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }

    //当权限申请操作完毕后， 回调该函数
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    initFruits();
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
        }
    }


    @Override
    public void finish() {
        /**
         * 记住不要执行此句 super.finish(); 因为这是父类已经实现了改方法
         * 设置该activity永不过期，即不执行onDestroy()

         */
        moveTaskToBack(true);
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d("message", "main activity resume");
    }
}
