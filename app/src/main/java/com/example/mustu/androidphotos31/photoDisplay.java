package com.example.mustu.androidphotos31;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.mustu.androidphotos31.MainActivity.albums;
import static com.example.mustu.androidphotos31.MainActivity.positions;

/**
 * Created by harsh on 4/29/2017.
 */

public class photoDisplay extends AppCompatActivity implements  View.OnClickListener{

    ArrayList<Photo> photoList = new ArrayList<Photo>();
    ImageAdapter adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_display);
        Intent i = getIntent();
        photoList = albums.get(positions).getPhotoList();
        Bitmap[] images = new Bitmap[photoList.size()];
        int j = 0;
        for(Photo p: photoList){
            images[j] = (p.getImage());
            j++;
        }
        GridView gridview = (GridView) findViewById(R.id.gridview);
        adapter = new ImageAdapter(this,images);
        gridview.setAdapter(adapter);


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("pos", position);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }


    @Override
    public void onClick(View view) {

    }
}
