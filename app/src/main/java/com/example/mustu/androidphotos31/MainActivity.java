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
    Button openAlbum, deleteAlbum;
    EditText edit;
    public ArrayList<Album> albums = new ArrayList<Album>();
    public ArrayAdapter<Album> adapter;
    //public AlbumListAdapter adapters;
    public static final int EDIT_ALBUM_CODE = 1;
    public static final int ADD_ALBUM_CODE = 2;
    public static final int OPEN_ALBUM_CODE = 3;
    public static final int DELETE_ALBUM_CODE = 4;

    int positions = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.album_list);
        handleIntent(getIntent());
    }

    public void Create(View view){
        Intent intent = new Intent(this, AddEditAlbum.class);
        startActivityForResult(intent, ADD_ALBUM_CODE);
    }

    public void OpenAlbum(View view){
        Intent intent = new Intent(this, addPhoto.class);
        ArrayList<Photo> photos = new ArrayList<>();
        albums.add(new Album("random",photos));
        intent.putExtra("album", albums.get(0));
        startActivityForResult(intent, OPEN_ALBUM_CODE);
    }

    public void handleIntent(Intent intent){
        showAlbumList();
    }

    private void showAlbumList(){
        listView.setAdapter(new ArrayAdapter<Album>(this, R.layout.album, albums));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //showAlbum(position);
                positions = position;
                Toast.makeText(getApplicationContext(), "Positions at: " + positions + " was clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode,intent);

        if (resultCode != RESULT_OK) {
            return;
        }

        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            return;
        }

        String name = bundle.getString(AddEditAlbum.ALBUM_NAME);
        int index = bundle.getInt(AddEditAlbum.ALBUM_INDEX);

        if (requestCode == EDIT_ALBUM_CODE){
            Album album = albums.get(index);
            album.albumName = name;
        }
        else if (requestCode == ADD_ALBUM_CODE){
            ArrayList<Photo> photos = new ArrayList<>();
            albums.add(new Album(name, photos));
            //Toast.makeText(getApplicationContext(), positions, Toast.LENGTH_LONG).show();
        }
        else if (requestCode == DELETE_ALBUM_CODE){
            albums.remove(index);
        }
        else if(requestCode == OPEN_ALBUM_CODE){

        }
        adapter = new ArrayAdapter<Album>(this, R.layout.album, albums);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    /*
    private void showAlbum(int position){
        Bundle bundle = new Bundle();
        Album album = albums.get(position);
        bundle.putInt(AddEditAlbum.ALBUM_INDEX, position);
        bundle.putString(AddEditAlbum.ALBUM_NAME, album.albumName);
        Intent intent = new Intent(this, AddEditAlbum.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, EDIT_ALBUM_CODE);
    }
    */

    public void rename(View view){
        Bundle bundle = new Bundle();
        Album album = albums.get(positions);
        bundle.putInt(AddEditAlbum.ALBUM_INDEX, positions);
        bundle.putString(AddEditAlbum.ALBUM_NAME, album.albumName);
        Intent intent = new Intent(this, AddEditAlbum.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, EDIT_ALBUM_CODE);
    }

    public void delete(View view){
        albums.remove(positions);
        adapter = new ArrayAdapter<Album>(this, R.layout.album, albums);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
