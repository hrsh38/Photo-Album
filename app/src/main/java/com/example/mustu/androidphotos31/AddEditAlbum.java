package com.example.mustu.androidphotos31;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.DialogFragment;
import android.widget.Toast;

import static android.R.id.input;

/**
 * Created by Mustufa on 4/25/2017.
 */

public class AddEditAlbum extends AppCompatActivity {

    /**
     * These keys are to send back and forth information between the bundles and intents
     */
    public static final String ALBUM_INDEX = "albumIndex";
    public static final String ALBUM_NAME = "albumName";
    EditText input;
    Button save, cancel;
    int albumIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_album);

        save = (Button) findViewById(R.id.save);
        cancel = (Button) findViewById(R.id.cancel);
        input = (EditText) findViewById(R.id.add);

        // see if info was passed in to populate field
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            albumIndex = bundle.getInt(ALBUM_INDEX);
            input.setText(bundle.getString(ALBUM_NAME));
        }
    }

    public void Cancel(View view) {
        setResult(RESULT_CANCELED);
        finish();   //Returns to previous page on call stack
    }

    public void addAlbum(View view){
        String name = input.getText().toString();
        //Checks to see if input is null and returns
        if(name == null || name.length()==0){
            Bundle bundle = new Bundle();
            bundle.putString(AlbumDialog.MESSAGE_KEY, "Album Name Required");
            DialogFragment newFragment = new AlbumDialog();
            newFragment.setArguments(bundle);
            newFragment.show(getFragmentManager(), "badfields");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(ALBUM_INDEX, albumIndex);
        bundle.putString(ALBUM_NAME, name);

        Intent intent = new Intent();
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }
}
