package com.example.mustu.androidphotos31;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    private ArrayList<Album> albums;
    public static final int EDIT_MOVIE_CODE = 1;
    public static final int ADD_MOVIE_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        listView = (ListView) findViewById(R.id.album_list);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Create(View view){
        Intent intent = new Intent(this, AddEditAlbum.class);
        startActivityForResult(intent, ADD_MOVIE_CODE);
    }


}
