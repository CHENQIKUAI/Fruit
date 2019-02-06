package com.example.chenqikuai.fruits.my;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.chenqikuai.fruits.MyDatabaseHelper;
import com.example.chenqikuai.fruits.R;

import java.util.LinkedList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpdateItemAdapter extends ArrayAdapter<Update> {
    private int resourceId;
    private Context mContext;
    private List<Update> mList;

    public UpdateItemAdapter(Context context, int textViewResourceId,
                             List<Update> objects) {
        super(context, textViewResourceId, objects);
        mContext = context;
        resourceId = textViewResourceId;
        mList = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int index = position;

        final Update item = getItem(position); // 获取当前项的Fruit实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent,
                false);

        //控件控制权限的获取
        ImageView profileImage = (ImageView) view.findViewById(R.id.profile_image);
        TextView username = (TextView) view.findViewById(R.id.username);
        TextView content = (TextView) view.findViewById(R.id.content);
        TextView updateTime = (TextView) view.findViewById(R.id.update_time);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.my_listview_right);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.my_listview_recycleview);
        Button btn_delete = (Button) view.findViewById(R.id.delete);


        //控件内容的显示
        if (!item.getPhotos()[0].equals("")) {
            List<String> list = new LinkedList<>();

            String[] ss = item.getPhotos();
            for (int i = 0; i < ss.length; ++i) {
                list.add(ss[i]);
            }
            GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
            recyclerView.setLayoutManager(layoutManager);
            PhotoItemAdapter adapter = new PhotoItemAdapter(list);
            recyclerView.setAdapter(adapter);
        } else {
            linearLayout.removeView(recyclerView);
        }

        if (item.getContent().equals("")) {
            linearLayout.removeView(content);
        } else {
            content.setText(item.getContent());
        }

        username.setText(item.getName());
        updateTime.setText(item.getRelativeTime());
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
                dialog.setTitle("This is Dialog");
                dialog.setMessage("你确定要删除该说说嘛？");
                dialog.setCancelable(true);
                dialog.setPositiveButton("OK", new DialogInterface.
                        OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MyDatabaseHelper dbHelper = new MyDatabaseHelper(mContext, "FruitApp.db", null, 1);
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        int serialId = item.getSerialId();
                        db.execSQL("delete from Talking where serial_id = " + serialId);
                        mList.remove(index);
                        UpdateItemAdapter.this.notifyDataSetChanged();
                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.
                        OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dialog.show();
            }
        });


        return view;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

}
