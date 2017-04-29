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
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static com.example.mustu.androidphotos31.R.drawable.no;


/**
 * Created by harsh on 4/27/2017.
 */

public class addPhoto extends AppCompatActivity implements  View.OnClickListener{
    private static final int RESULT_LOAD_IMAGE = 1;
    private static final int RESULT_DELETE_IMAGE = 2;
    private static final int ADD_TAG_CODE = 3;
    private static final int DISPLAY_CODE = 4;
    ImageView imageToUpload;
    Button add;
    ArrayList<Photo> p = new ArrayList<>();
    Album album = new Album("hi",p);
    int index = -1;
    int size = 0;
    TextView tags;
    Tag tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.album_page);
        Intent i = getIntent();
        album = (Album)i.getSerializableExtra("album");
        imageToUpload = (ImageView) findViewById(R.id.imageView);
        //remove = (Button) findViewById(R.id.remove);

        add = (Button) findViewById(R.id.add);
        tags = (TextView) findViewById(R.id.tag);


        imageToUpload.setOnClickListener(this);
        add.setOnClickListener(this);
        //remove.setOnClickListener(this);
    }

    public void goLeft(View view){
        if(index - 1 >= 0){
            index -= 1;
            imageToUpload.setImageURI(album.getPhotoList().get(index).getImage());
            tags.setText(album.getPhotoList().get(index).getTag().toString());

        }
        else{
            Toast.makeText(getApplicationContext(),"No more images to the Left!", Toast.LENGTH_LONG).show();
        }
    }

    public void goRight(View view){
        try{
        if(album.getPhotoList().get(index+1)!=null && index!=-1){
            index += 1;
            imageToUpload.setImageURI(album.getPhotoList().get(index).getImage());
            tags.setText(album.getPhotoList().get(index).getTag().toString());

            Toast.makeText(getApplicationContext(), album.albumName, Toast.LENGTH_LONG).show();

        }
        else {
            Toast.makeText(getApplicationContext(), "No more images to the Right!", Toast.LENGTH_LONG).show();
        }}catch(Exception e){
            Toast.makeText(getApplicationContext(),"No more images to the Right!", Toast.LENGTH_LONG).show();
        }
    }
    public void display(View view){
        Intent intent = new Intent(this, photoDisplay.class);
        intent.putExtra("list", album.photoList);
        startActivityForResult(intent, DISPLAY_CODE);
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
    public void delete(View view){
        if(!album.photoList.isEmpty()) {
            album.deletePhoto(index);

            if (index - 1 >= 0) {
                index--;
                size--;
                imageToUpload.setImageURI(album.getPhotoList().get(index).getImage());
                tags.setText(album.getPhotoList().get(index).getTag().toString());
            } else {
                imageToUpload.setImageURI(Uri.EMPTY);
            }
        }
        else{
            Toast.makeText(getApplicationContext(),album.photoList.size() + "", Toast.LENGTH_LONG).show();

        }
        //Toast.makeText(getApplicationContext(),album.photoList.size() + "", Toast.LENGTH_LONG).show();

    }
    public void deleteTag(View view){
        album.getPhotoList().get(index).setTag(new Tag("Person", "Location"));
        tags.setText(album.getPhotoList().get(index).getTag().toString());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK &&data != null){
            Uri selectedImage = data.getData();
            Photo photo = new Photo("photo1","caption",selectedImage);
            album.addPhoto(photo);
            index++;
            imageToUpload.setImageURI(album.getPhotoList().get(index).getImage());
            tags.setText(album.getPhotoList().get(index).getTag().toString());
            return;
        }

        if(resultCode != RESULT_OK){
            return;
        }

        Bundle bundle = data.getExtras();
        if (bundle == null) {
            return;
        }
        String name = bundle.getString(addTag.PERSON_TAG);
        String locations = bundle.getString(addTag.LOCATION_TAG);

        if(requestCode == ADD_TAG_CODE){
            tag = new Tag(name, locations);
            album.getPhotoList().get(index).setTag(tag);
            tags.setText(tag.toString());
        }
    }

    public void addTag(View view){
        Intent intent = new Intent(this, addTag.class);
        startActivityForResult(intent, ADD_TAG_CODE);
    }
}