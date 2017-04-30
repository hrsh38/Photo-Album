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
 * Created by Mustufa on 4/29/2017.
 */

public class addTag extends AppCompatActivity {

    /**
     * These keys are to send back and forth information between the bundles and intents
     */
    public static final String PERSON_TAG = "person";
    public static final String LOCATION_TAG = "location";
    EditText person, location;
    Button save, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tag);
        save = (Button) findViewById(R.id.save);
        cancel = (Button) findViewById(R.id.cancel);
        person = (EditText) findViewById(R.id.person);
        location = (EditText) findViewById(R.id.location);
    }

    public void Cancel(View view) {
        setResult(RESULT_CANCELED);
        finish();   //Returns to previous page on call stack
    }

    public void saveTag(View view){
        String name = person.getText().toString();
        String locations = location.getText().toString();
        if(name == null || name.length()==0 || locations == null || locations.length() ==0){
            Bundle bundle = new Bundle();
            bundle.putString(AlbumDialog.MESSAGE_KEY, "Both Field Are Required");
            DialogFragment newFragment = new AlbumDialog();
            newFragment.setArguments(bundle);
            newFragment.show(getFragmentManager(), "badfields");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(PERSON_TAG, name);
        bundle.putString(LOCATION_TAG, locations);

        Intent intent = new Intent();
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }
}
