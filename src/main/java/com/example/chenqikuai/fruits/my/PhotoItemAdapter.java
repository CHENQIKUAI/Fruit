package com.example.chenqikuai.fruits.my;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.chenqikuai.fruits.MyDatabaseHelper;
import com.example.chenqikuai.fruits.R;

import java.util.List;

public class PhotoItemAdapter extends RecyclerView.Adapter<PhotoItemAdapter.ViewHolder> {
    private Context mContext;
    private List<String> mPhotoList;


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.photoOfRecyclerView);
        }
    }

    public PhotoItemAdapter(List<String> list) {
        mPhotoList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mContext == null) {
            mContext = viewGroup.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.my_listview_photos, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String s = mPhotoList.get(i);
        Glide.with(mContext).load(s).into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return mPhotoList.size();
    }
}
