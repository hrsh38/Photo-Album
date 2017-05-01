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

import static com.example.mustu.androidphotos31.addPhoto.*;

/**
 * Created by mustu on 4/30/2017.
 */

public class SearchTag extends AppCompatActivity{
    ArrayList<Photo> list;
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

        for(index = 0; index< p.size(); index++){
            Toast.makeText(getApplicationContext(), "it didn't work", Toast.LENGTH_LONG).show();
            try{
                if(album.getPhotoList().get(index).getTag().getType() == "dog" || album.getPhotoList().get(index).getTag().getValue() == "dog"){
                    adapter = new ArrayAdapter<Photo>(this, R.layout.album, list);
                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }catch(Exception e){
                Toast.makeText(getApplicationContext(), "it didn't work", Toast.LENGTH_LONG).show();
            }
        }


        search("new");
    }

    public void search(String query){
        for(int i =0 ; i < p.size(); i++){
            try{
                if(album.getPhotoList().get(i).getTag().getType() == query || album.getPhotoList().get(index).getTag().getValue() == query){
                    adapter = new ArrayAdapter<Photo>(this, R.layout.album, list);
                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }catch(Exception e){
                Toast.makeText(getApplicationContext(), "it didn't work", Toast.LENGTH_LONG).show();
            }
        }
    }
}
