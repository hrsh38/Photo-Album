package com.example.mustu.androidphotos31;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button openAlbum;
    EditText edit;
    public ArrayList<Album> albums;
    public ArrayAdapter<Album> adapter;
    public static final int EDIT_ALBUM_CODE = 1;
    public static final int ADD_ALBUM_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.album_list);
        //handleIntent(getIntent());
    }

    public void Create(View view){
        Intent intent = new Intent(this, AddEditAlbum.class);
        startActivityForResult(intent, ADD_ALBUM_CODE);
    }

    public void handleIntent(Intent intent){
        showAlbumList();
    }

    private void showAlbumList(){
        listView.setAdapter(new ArrayAdapter<Album>(this, android.R.layout.simple_list_item_1, albums));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showAlbum(position);
            }
        });
    }

    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode != RESULT_OK) {
            return;
        }

        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            return;
        }

        String name = bundle.getString(AddEditAlbum.ALBUM_NAME);
        int index = bundle.getInt(AddEditAlbum.ALBUM_INDEX);



        if (resultCode == EDIT_ALBUM_CODE){
            Album album = albums.get(index);
            album.albumName = name;
        }
        else if (resultCode == ADD_ALBUM_CODE){
            Toast.makeText(getApplicationContext(), name + ", " + index, Toast.LENGTH_LONG).show();
            //ArrayList<Photo> photos = new ArrayList<>();
            //albums.add(new Album(name, photos));
        }
        listView.setAdapter(new ArrayAdapter<Album>(this, android.R.layout.simple_list_item_1, albums));

    }

    private void showAlbum(int position){
        Bundle bundle = new Bundle();
        Album album = albums.get(position);
        bundle.putInt(AddEditAlbum.ALBUM_INDEX, position);
        bundle.putString(AddEditAlbum.ALBUM_NAME, album.albumName);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        startActivityForResult(intent, EDIT_ALBUM_CODE);
    }

}
