package com.example.mustu.androidphotos31;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.mustu.androidphotos31.MainActivity.albums;
import static com.example.mustu.androidphotos31.MainActivity.positions;
import static com.example.mustu.androidphotos31.addPhoto.*;

/**
 * Created by mustu on 4/30/2017.
 */

public class SearchTag extends AppCompatActivity{
    ArrayList<Photo> list = new ArrayList<Photo>();
    ListView listView;
    public ArrayAdapter<Photo> adapter;
    SearchView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        listView = (ListView) findViewById(R.id.list_view);
        //search = (SearchView) findViewById(R.id.search);
        DataWrapper dw = (DataWrapper) getIntent().getSerializableExtra("data");
        /*
        list = dw.getParliaments();
        adapter = new ArrayAdapter<Photo>(this, R.layout.album, list);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(), dw.toString() + "has been passed", Toast.LENGTH_LONG).show();
        */

        //Toast.makeText(getApplicationContext(), album.getPhotoList().get(index).getTag().getType(), Toast.LENGTH_LONG).show();
        //Toast.makeText(getApplicationContext(), album.getPhotoList().get(index-1).getTag().getType(), Toast.LENGTH_LONG).show();
        searchTag();
        adapter = new ArrayAdapter<Photo>(this, R.layout.album, list);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(),listView.getCount() + " ", Toast.LENGTH_LONG).show();

        search("new");
    }

    public void searchTag(String query){
        for(Album al:albums){
            for(Photo photo:al.getPhotoList()) {
                if(photo.getTag().getType().contains(query)||photo.getTag().getValue().contains(query)){
                    list.add(photo);
                }
            }
        }
    }
}
