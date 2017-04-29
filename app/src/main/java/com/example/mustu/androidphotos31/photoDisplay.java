package com.example.mustu.androidphotos31;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by harsh on 4/29/2017.
 */

public class photoDisplay extends AppCompatActivity implements  View.OnClickListener{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_display);

        GridView gridview = (GridView) findViewById(R.id.gridview);
       // gridview.setAdapter(new ImageAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

            }
        });

    }


    @Override
    public void onClick(View view) {

    }
}
