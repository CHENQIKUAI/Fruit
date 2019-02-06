package com.example.chenqikuai.fruits.my.photoAdd;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chenqikuai.fruits.MyDatabaseHelper;
import com.example.chenqikuai.fruits.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

public class PhotoAddActivity extends AppCompatActivity implements View.OnClickListener {
    private List<String> photoList = new ArrayList<>();

    public static final int REQUEST_IMAGE = 2;
    private MyDatabaseHelper dbHelper;
    private Uri imageUri;
    private PhotosAdapter adapter;
    private Button btn_post;
    private EditText editContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photos_add_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.photos);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PhotosAdapter(photoList);
        recyclerView.setAdapter(adapter);

        btn_post = findViewById(R.id.post);
        btn_post.setOnClickListener(this);
        Button btn_selectphoto = (Button) findViewById(R.id.btn_selectphoto);
        btn_selectphoto.setOnClickListener(this);
        final Button btn_cancel = (Button) findViewById(R.id.cancel);
        btn_cancel.setOnClickListener(this);
        editContent = (EditText) findViewById(R.id.photosText);

        btn_post.setClickable(false);
        btn_post.getBackground().mutate().setAlpha(100);


        editContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String content = s.toString();
                if (content.equals("")) {
                    btn_post.setClickable(false);
                    btn_post.getBackground().mutate().setAlpha(100);
                } else {
                    btn_post.setClickable(true);
                    btn_post.getBackground().mutate().setAlpha(255);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        List<String> permissionList = new LinkedList<>();
        if (ContextCompat.checkSelfPermission(PhotoAddActivity.this, Manifest.
                permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
//        if (ContextCompat.checkSelfPermission(PhotoAddActivity.this, Manifest.permission.CAMERA)
//                != PackageManager.PERMISSION_GRANTED) {
//            permissionList.add(Manifest.permission.CAMERA);
//        }

        if (ContextCompat.checkSelfPermission(PhotoAddActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        if (!permissionList.isEmpty()) {
            String[] permissions = (String[]) permissionList.toArray(new String[permissionList.
                    size()]);
            ActivityCompat.requestPermissions(PhotoAddActivity.this, permissions, 2);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {

            case REQUEST_IMAGE:
                // 获取返回的图片列表
                if (resultCode == RESULT_OK) {
                    List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                    //因为只有在同一个list里面才能实现notify更新所以用for循环来添加字符
                    for (String temp : path) {
                        photoList.add(temp);
                    }
                    adapter.notifyDataSetChanged();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_selectphoto:
                //权限申请

                MultiImageSelector.create(PhotoAddActivity.this)
                        .start(PhotoAddActivity.this, REQUEST_IMAGE);

                break;
            case R.id.post:
                dbHelper = new MyDatabaseHelper(this, "FruitApp.db", null, 1);
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();

                String strPhoto = "";// photo
                for (String s : photoList) {
                    strPhoto += (s + " ");
                }
                String content = editContent.getText().toString();// Content;
                Date date = new Date();
                String time = date.getTime() + ""; //Date

                values.put("name", "Peter");
                values.put("photo", strPhoto);
                values.put("content", content.trim());
                values.put("time", time);
                db.insert("Talking", null, values);
                finish();
                break;
            case R.id.cancel:
                finish();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (photoList.size() != 0) {
            btn_post.setClickable(true);
            btn_post.getBackground().mutate().setAlpha(255);
        }
    }

    //当权限申请操作完毕后，回调该函数
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 2:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, result,
                                    Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                } else {
                    Toast.makeText(this, "发生未知错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }
}
