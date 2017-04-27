package com.example.mustu.androidphotos31;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


/**
 * Created by harsh on 4/27/2017.
 */

public class addPhoto extends AppCompatActivity implements  View.OnClickListener{
    private static final int RESULT_LOAD_IMAGE = 1;
    ImageView imageToUpload;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.album_page);

        imageToUpload = (ImageView) findViewById(R.id.imageView);

        add = (Button) findViewById(R.id.add);

        imageToUpload.setOnClickListener(this);
        add.setOnClickListener(this);

    }
    public void Cancels(View view) {
        setResult(RESULT_CANCELED);
        finish();   //Returns to previous page on call stack
    }
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.add){
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent,RESULT_LOAD_IMAGE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK &&data != null){
            Uri selectedImage = data.getData();
            Photo photo = new Photo("photo1","caption",selectedImage);
            imageToUpload.setImageURI(photo.getImage());
        }
    }
}
