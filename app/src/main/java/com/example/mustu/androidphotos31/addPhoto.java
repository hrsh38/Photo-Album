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
import android.widget.ImageSwitcher;
import android.widget.ImageView;


/**
 * Created by harsh on 4/27/2017.
 */

public class addPhoto extends AppCompatActivity implements  View.OnClickListener{
    private static final int RESULT_LOAD_IMAGE = 1;
    private static final int RESULT_DELETE_IMAGE = 2;
    ImageView imageToUpload;
    //ImageSwitcher images;
    Button add, remove;
    Album album;
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.album_page);
        Intent i = getIntent();
        album = (Album)i.getSerializableExtra("album");
        add = (Button) findViewById(R.id.add);
        remove = (Button) findViewById(R.id.remove);

        //imageToUpload.setOnClickListener(this);
        add.setOnClickListener(this);
        remove.setOnClickListener(this);

    }
    public void Cancels(View view) {
        setResult(RESULT_CANCELED);
        finish();   //Returns to previous page on call stack
    }
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.add){
            Intent addIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(addIntent,RESULT_LOAD_IMAGE);
        }
        if(view.getId() == R.id.remove){
            if(!album.photoList.isEmpty()){
                Intent crimsonIntent = new Intent(Intent.ACTION_PICK, album.photoList.get(index).getImage());
                startActivityForResult(crimsonIntent,RESULT_DELETE_IMAGE);
            }
            else{
                //NOTHING TO REMOVE MESSAGE
            }
            //Intent crimsonIntent = new Intent(Intent.ACTION_DELETE,imageToUpload.getI)

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK &&data != null){
            Uri selectedImage = data.getData();
            Photo photo = new Photo("photo1","caption",selectedImage);
            album.addPhoto(photo);
            //images.setImageURI(photo.getImage());
            imageToUpload.setImageURI(photo.getImage());
        }
        else if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            Photo photo = new Photo("temp","caption",selectedImage);
            album.deletePhoto(photo);
            if(index-1<0){
                index -= 1;
                imageToUpload.setImageURI(album.getPhotoList().get(index).getImage());
            }
            else{
                imageToUpload.setTag(R.drawable.no);
            }
        }

    }
}
