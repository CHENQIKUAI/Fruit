package com.example.chenqikuai.fruits.my.photoAdd;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.chenqikuai.fruits.R;

import java.io.File;
import java.util.List;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {
    private List<String> mList;
    private Context mContext;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView photo;

        public ViewHolder(View view) {
            super(view);
            photo = (ImageView) view.findViewById(R.id.photoOfImageView);
        }
    }

    public PhotosAdapter(List<String> list) {
        mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mContext == null) {
            mContext = viewGroup.getContext();
        }
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.photos_add_recycler_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String s = mList.get(i);
        File file = new File(s);
        Glide.with(mContext).load(file).into(viewHolder.photo);// 把图片加载到photo里面}
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
