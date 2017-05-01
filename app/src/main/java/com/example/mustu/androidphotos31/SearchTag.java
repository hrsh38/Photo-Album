package com.example.mustu.androidphotos31;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.mustu.androidphotos31.MainActivity.albums;
import static com.example.mustu.androidphotos31.MainActivity.positions;
import static com.example.mustu.androidphotos31.addPhoto.*;

/**
 * Created by mustu on 4/30/2017.
 */

public class SearchTag extends AppCompatActivity implements SearchView.OnQueryTextListener{
    ArrayList<Photo> list = new ArrayList<Photo>();
    ListView listView;
    public ArrayAdapter<Photo> adapter;
    SearchView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        listView = (ListView) findViewById(R.id.lstView);
        search = (SearchView) findViewById(R.id.searchView);
        search.setOnQueryTextListener(this);

        adapter = new ArrayAdapter<Photo>(this, R.layout.album, list);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(),listView.getCount() + " ", Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(!newText.equals("")) {
            List<Photo> lstFound = new ArrayList<Photo>();
            for (Album al : albums) {
                for (Photo photo : al.getPhotoList()) {
                    if (photo.getTag().getType().contains(newText) || photo.getTag().getValue().contains(newText)) {
                        lstFound.add(photo);
                    }
                }
            }
            List<String> list3 = new ArrayList<>();
            for (Photo p : lstFound) {
                list3.add("Album Name: " + albums.get(positions).getAlbumName() + " , PhotoName: " + p.getPhotoName() +
                        "\nPerson: " + p.getTag().getType() + " , Location: " + p.getTag().getValue());
            }
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list3);
            listView.setAdapter(adapter);
            return true;
        }
        else{
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
            listView.setAdapter(adapter);
            return true;
        }
    }


}
