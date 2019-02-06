package com.example.chenqikuai.fruits.my;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.chenqikuai.fruits.MyDatabaseHelper;
import com.example.chenqikuai.fruits.R;
import com.example.chenqikuai.fruits.my.photoAdd.PhotoAddActivity;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class My extends Fragment {
    private MyDatabaseHelper dbHelper;
    private List<Update> list = new LinkedList<>();
    private UpdateItemAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_fragment, container, false);
        adapter = new UpdateItemAdapter(getActivity(), R.layout.my_listview, list);
        ListView listView = (ListView) view.findViewById(R.id.list_view);

        View header = LayoutInflater.from(getActivity()).inflate(R.layout.head_view, null, false);
        listView.addHeaderView(header);
        listView.setAdapter(adapter);

        FloatingActionButton btn_floating = (FloatingActionButton) view.findViewById(R.id.fab);
        btn_floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PhotoAddActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        list.clear();
        select();
        adapter.notifyDataSetChanged();
//        Toast.makeText(getActivity(), "回来啦", Toast.LENGTH_SHORT).show();
    }

    public void select() {
        {
            //获取数据库说说数据
            dbHelper = new MyDatabaseHelper(getActivity(), "FruitApp.db", null, 1);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query("Talking", null, null, null, null, null, "serial_id desc");
            if (cursor.moveToFirst()) {
                do {
                    int serialId = cursor.getInt(cursor.getColumnIndex("serial_id"));
                    String name = cursor.getString(cursor.getColumnIndex
                            ("name"));
                    String photo = cursor.getString(cursor.getColumnIndex
                            ("photo"));
                    String content = cursor.getString(cursor.getColumnIndex
                            ("content"));
                    String time = cursor.getString(cursor.getColumnIndex
                            ("time"));
                    Update u = new Update(serialId, "http://upload-images.jianshu.io/upload_images/4515180-ca27cbef615d4456.jpeg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240", name, content, photo.split(" "), new Date(Long.parseLong(time)));
                    list.add(u);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
    }
}