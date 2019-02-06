package com.example.chenqikuai.fruits.my;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Update {
    private int serialId;
    private String profilePhoto;
    private String name;
    private String content;
    private String[] photos;
    private Date date;

    public Update(int serialId, String profilePhoto, String name, String content, String[] photos, Date date) {
        this.serialId = serialId;
        this.profilePhoto = profilePhoto;
        this.name = name;
        this.content = content;
        this.photos = photos;
        this.date = date;
    }

    public int getSerialId() {
        return serialId;
    }

    public String getRelativeTime() {
        long sub = new Date().getTime() - date.getTime();
        sub /= 1000;
        if (sub < 60 * 60) {
            if (sub / 60 <= 1)
                return "1分钟前";
            return sub / 60 + "分钟前";
        } else if (sub < 60 * 60 * 24) {
            return sub / 60 / 60 + "小时前";
        } else if (sub < 60 * 60 * 24 * 30) {
            return sub / 60 / 60 / 24 + "天前";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
            String s = sdf.format(date);
            return s;
        }
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name.trim();
    }

    public String[] getPhotos() {
        return photos;
    }
}