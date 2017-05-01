package com.example.mustu.androidphotos31;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Parcelable;
import android.preference.PreferenceManager;
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button openAlbum, deleteAlbum;
    EditText edit;
    public static ArrayList<Album> albums = new ArrayList<Album>();
    public static String fileName = "createResumeForm.ser";
    public ArrayAdapter<Album> adapter;
    //public AlbumListAdapter adapters;
    public static final int EDIT_ALBUM_CODE = 1;
    public static final int ADD_ALBUM_CODE = 2;
    public static final int OPEN_ALBUM_CODE = 3;
    public static final int SERIALIZABLE = 4;
    public static int positions = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        positions = -1;

        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.album_list);
        adapter = new ArrayAdapter<Album>(this, R.layout.album, albums);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                positions = position;
                Toast.makeText(getApplicationContext(), "Positions at: " + positions + " was clicked", Toast.LENGTH_SHORT).show();
                view.setBackgroundColor(Color.LTGRAY);
            }
        });

    }
    @Override
    public void onPause(){
        super.onPause();

    }

    public void Create(View view){
        Intent intent = new Intent(this, AddEditAlbum.class);
        startActivityForResult(intent, ADD_ALBUM_CODE);
    }

    public void OpenAlbum(View view){
        Intent intent = new Intent(this, addPhoto.class);
        if(positions !=-1) {
            intent.putExtra("k", positions);
            startActivityForResult(intent, OPEN_ALBUM_CODE);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode,intent);
        Bundle bundle = new Bundle();

       switch(requestCode) {
           case(EDIT_ALBUM_CODE):{
               if (bundle == null) {
                   return;
               }
               try {
                   bundle = intent.getExtras();
               }catch (Exception e){
                   break;
               }
               String name = bundle.getString(AddEditAlbum.ALBUM_NAME);
               int index = bundle.getInt(AddEditAlbum.ALBUM_INDEX);
               Album album = albums.get(index);
               album.albumName = name;
               break;

           }
           case(ADD_ALBUM_CODE): {
               if (bundle == null) {
                   return;
               }
               try {
                   bundle = intent.getExtras();
               }catch (Exception e){
                   break;
               }

               String name = bundle.getString(AddEditAlbum.ALBUM_NAME);
               ArrayList<Photo> photos = new ArrayList<>();
               albums.add(new Album(name, photos));
               break;
               //Toast.makeText(getApplicationContext(), positions, Toast.LENGTH_LONG).show();
           }
           case(OPEN_ALBUM_CODE) :{
               if(resultCode == addPhoto.RESULT_OK) {
                   Bundle b = intent.getExtras();
                   albums.set(positions,(Album) b.getParcelable("album"));
               }
               Toast.makeText(getApplicationContext(), albums.get(positions).getPhotoCount() + " hi", Toast.LENGTH_LONG).show();
               break;
           }
           default:{
               break;
           }

       }
        positions = -1;

        adapter = new ArrayAdapter<Album>(this, R.layout.album, albums);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void rename(View view){
        try{
            Bundle bundle = new Bundle();
            Album album = albums.get(positions);
            bundle.putInt(AddEditAlbum.ALBUM_INDEX, positions);
            bundle.putString(AddEditAlbum.ALBUM_NAME, album.albumName);
            Intent intent = new Intent(this, AddEditAlbum.class);
            intent.putExtras(bundle);
            startActivityForResult(intent, EDIT_ALBUM_CODE);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "No album was selected to be renamed", Toast.LENGTH_SHORT).show();
        }

    }

    public void delete(View view){
        try{
            albums.remove(positions);
            adapter = new ArrayAdapter<Album>(this, R.layout.album, albums);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }catch(Exception e){
            Toast.makeText(getApplicationContext(), "No album was selected to be deleted", Toast.LENGTH_SHORT).show();
        }
    }


    public void writeToFile() {
        try {
            FileOutputStream fOut = openFileOutput("write.txt",MODE_PRIVATE);
            String s = "";
            for(Album a: albums){
                s = a.albumName;
                fOut.write(s.getBytes());
            }
            fOut.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
    /*
    private void readFromFile() {
        int c = 0;
        String temp = "";
        FileInputStream fin = openFileInput("write.txt");
    }
    */
}