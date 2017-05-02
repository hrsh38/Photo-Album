package com.example.mustu.androidphotos31;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static com.example.mustu.androidphotos31.MainActivity.albums;
import static com.example.mustu.androidphotos31.MainActivity.positions;

/**
 * Created by harsh on 5/1/2017.
 */

public class movePhoto extends AppCompatActivity implements  View.OnClickListener {
    ListView listView;
    public ArrayAdapter<Album> adapter;
    int index;
    Photo photo;
    static int posOfMove = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.move_photo);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        index = b.getInt("photoindex");
        if(index == -1){
            Toast.makeText(getApplicationContext(),"No images in gallery!", Toast.LENGTH_LONG).show();
            return;
        }
        try{
            photo = albums.get(positions).getPhotoList().get(index);
        }catch(Exception e){
            Toast.makeText(getApplicationContext(),"No images in gallery!", Toast.LENGTH_LONG).show();
        }


        EditText editText = (EditText)findViewById(R.id.editText);
        editText.setText(photo.getPhotoName(), TextView.BufferType.NORMAL);


        listView = (ListView) findViewById(R.id.list_view);

        adapter = new ArrayAdapter<Album>(this, R.layout.album, albums);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                posOfMove = position;
                v.setBackgroundColor(Color.LTGRAY);
                setResult(RESULT_OK);
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}
