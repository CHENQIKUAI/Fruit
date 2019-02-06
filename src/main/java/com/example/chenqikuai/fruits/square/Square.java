package com.example.chenqikuai.fruits.square;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chenqikuai.fruits.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Square extends Fragment {
    private Map<String, String> mapOfFruits = new HashMap<>();
    private List<Fruit> fruitList = new ArrayList<>();
    private String[] fruits = {"apple", "banana", "carambola", "cherry", "ficus_carica", "granadilla", "grape", "guava", "kiwi", "lemon", "mandarin", "orange", "pawpaw", "peach", "pear", "pepino_melon", "persimmon", "pineapple", "pomelo", "purple_mulberry", "rambutan", "raspberry", "strawberry", "watermelon", "yellow_peach"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.square_fragment, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(layoutManager);
        initFruits();

        FruitAdapter adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void initFruits() {

        mapOfFruits.put("apple", "https://upload-images.jianshu.io/upload_images/4515180-3321b0847fe90da2.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");

        mapOfFruits.put("banana", "https://upload-images.jianshu.io/upload_images/4515180-104c66de83dd01c1.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");

        mapOfFruits.put("carambola", "https://upload-images.jianshu.io/upload_images/4515180-eea778ad0e67627f.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");

        mapOfFruits.put("cherry", "https://upload-images.jianshu.io/upload_images/4515180-96e303194c58b48a.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");

        mapOfFruits.put("ficus_carica", "https://upload-images.jianshu.io/upload_images/4515180-0feefaefb7b383f4.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");

        mapOfFruits.put("granadilla", "https://upload-images.jianshu.io/upload_images/4515180-9ced79be7bd3fbdb.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");

        mapOfFruits.put("grape", "https://upload-images.jianshu.io/upload_images/4515180-719a97b965e2db19.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");

        mapOfFruits.put("guava", "https://upload-images.jianshu.io/upload_images/4515180-3a063b087252b83a.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");

        mapOfFruits.put("kiwi", "https://upload-images.jianshu.io/upload_images/4515180-d716a8c099f6c220.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");

        mapOfFruits.put("lemon", "https://upload-images.jianshu.io/upload_images/4515180-b5027c2c37d0d2be.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");

        mapOfFruits.put("mandarin", "https://upload-images.jianshu.io/upload_images/4515180-a672763a2e8e13cd.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");

        mapOfFruits.put("orange", "https://upload-images.jianshu.io/upload_images/4515180-57e50eab84e68639.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");

        mapOfFruits.put("pawpaw", "https://upload-images.jianshu.io/upload_images/4515180-f5b5971fe7ad5952.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");

        mapOfFruits.put("peach", "https://upload-images.jianshu.io/upload_images/4515180-87414ebdd968cbe7.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");

        mapOfFruits.put("pear", "https://upload-images.jianshu.io/upload_images/4515180-17201b0a29d5e2be.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");

        mapOfFruits.put("pepino_melon", "https://upload-images.jianshu.io/upload_images/4515180-57d595cbe664e93a.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");

        mapOfFruits.put("persimmon", "https://upload-images.jianshu.io/upload_images/4515180-220eb943f1f9cfc4.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");

        mapOfFruits.put("pineapple", "https://upload-images.jianshu.io/upload_images/4515180-c17a94a81e3ff114.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");

        mapOfFruits.put("pomelo", "https://upload-images.jianshu.io/upload_images/4515180-6f190391dbe09a09.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");

        mapOfFruits.put("purple_mulberry", "https://upload-images.jianshu.io/upload_images/4515180-79dfda337bbd5cd6.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");

        mapOfFruits.put("rambutan", "https://upload-images.jianshu.io/upload_images/4515180-9c2c8f149cd7062d.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");

        mapOfFruits.put("raspberry", "https://upload-images.jianshu.io/upload_images/4515180-953dcd62b19b760a.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");

        mapOfFruits.put("strawberry", "https://upload-images.jianshu.io/upload_images/4515180-49c479be03452d4f.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");

        mapOfFruits.put("watermelon", "https://upload-images.jianshu.io/upload_images/4515180-f0a4ccffd3a982e5.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");

        mapOfFruits.put("yellow_peach", "https://upload-images.jianshu.io/upload_images/4515180-e98cc60d10e88019.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");

        fruitList.clear();
        for (int i = 0; i < fruits.length; ++i) {
            String name = fruits[i];
            String url = mapOfFruits.get(name);
            fruitList.add(new Fruit(name, url));
        }
    }
}
