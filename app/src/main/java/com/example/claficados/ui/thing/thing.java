package com.example.claficados.ui.thing;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.claficados.R;
import com.example.claficados.ui.home.RecyclerViewAdapter;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static com.example.claficados.oi.utilities.urlCover;
import static com.example.claficados.oi.utilities.urlmNames;


public class thing extends Fragment {

    private ArrayList<String> mNames2 = new ArrayList<>();
    private ArrayList<String> mImageUrls2 = new ArrayList<>();
    private ArrayList<String> mDescription2 = new ArrayList<>();

    RecyclerView recyclerView2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_thing, container, false);

        recyclerView2 =(RecyclerView)root.findViewById(R.id.recyclerViewThing);
        getImages(root);
        return root;
    }

    private void getImages(View root) {
        mImageUrls2.add("http://www.comcop.com.co/persia/imgsApp/logoComcop.png");
        mNames2.add("Estes es un nombre");
        mDescription2.add("Esta es una descripcion");

        mImageUrls2.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mNames2.add("Estes es un nombre");
        mDescription2.add("Esta es una descripcion");


        initRecyclerView(root);

    }

    private void initRecyclerView(View root) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        final RecyclerView recyclerView =root.findViewById(R.id.recyclerViewThing);
        recyclerView.setLayoutManager(layoutManager);
        horizontalrecyclerview2 adapter2 = new horizontalrecyclerview2( getContext(), mNames2, mImageUrls2,mDescription2);
        recyclerView.setAdapter(adapter2);
        Log.d(TAG, "initRecyclerView: init recyclerview");
    }
}