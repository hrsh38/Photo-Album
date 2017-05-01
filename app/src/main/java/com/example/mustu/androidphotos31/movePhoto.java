package com.example.mustu.androidphotos31;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.move_photo);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        index = b.getInt("photoindex");
        photo = albums.get(positions).getPhotoList().get(index);

        EditText editText = (EditText)findViewById(R.id.editText);
        editText.setText(photo.getPhotoName(), TextView.BufferType.NORMAL);


        listView = (ListView) findViewById(R.id.list_view);
        adapter = new ArrayAdapter<Album>(this, R.layout.album, albums);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}
