package com.example.mustu.androidphotos31;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by mustu on 4/30/2017.
 */

public class SearchTag extends AppCompatActivity{
    ArrayList<Photo> list;
    ListView listView;
    public ArrayAdapter<Photo> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        listView = (ListView) findViewById(R.id.list_view);
        DataWrapper dw = (DataWrapper) getIntent().getSerializableExtra("data");
        list = dw.getParliaments();
        adapter = new ArrayAdapter<Photo>(this, R.layout.album, list);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    public ArrayList<Photo> search(String query){

        return list;
    }
}
