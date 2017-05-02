package com.example.mustu.androidphotos31;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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


import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static com.example.mustu.androidphotos31.MainActivity.albums;
import static com.example.mustu.androidphotos31.MainActivity.positions;
import static com.example.mustu.androidphotos31.R.drawable.no;
import static com.example.mustu.androidphotos31.movePhoto.posOfMove;
import static com.example.mustu.androidphotos31.photoDisplay.pen;


/**
 * Created by harsh on 4/27/2017.
 */

public class addPhoto extends AppCompatActivity implements  View.OnClickListener {
    private static final int RESULT_LOAD_IMAGE = 1;
    private static final int RESULT_DELETE_IMAGE = 2;
    private static final int ADD_TAG_CODE = 3;
    private static final int DISPLAY_CODE = 4;
    private static final int SEARCH_TAG_CODE = 5;
    private static final int MOVE_CODE = 6;
    ImageView imageToUpload;
    Button add;
    ArrayList<Photo> p = new ArrayList<>();
    Album album = new Album("hi", p);
    int index = -1;
    int size = 0;
    int count = 0;
    TextView tags;
    Tag tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.album_page);
        Intent i = getIntent();
        album = albums.get(positions);
        imageToUpload = (ImageView) findViewById(R.id.imageView);
        add = (Button) findViewById(R.id.add);
        tags = (TextView) findViewById(R.id.tag);
        imageToUpload.setOnClickListener(this);
        add.setOnClickListener(this);
    }

    public void goLeft(View view) {
        if (index - 1 >= 0) {
            index -= 1;
            imageToUpload.setImageBitmap(album.getPhotoList().get(index).getImage());
            tags.setText(album.getPhotoList().get(index).getTag().toString());
        } else {
            Toast.makeText(getApplicationContext(), "No more images to the Left!", Toast.LENGTH_LONG).show();
        }
    }

    public void goRight(View view) {
        try {
            if (album.getPhotoList().get(index + 1) != null && index != -1) {
                index += 1;
                imageToUpload.setImageBitmap(album.getPhotoList().get(index).getImage());
                tags.setText(album.getPhotoList().get(index).getTag().toString());
            } else {
                Toast.makeText(getApplicationContext(), "No more images to the Right!", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "No more images to the Right!", Toast.LENGTH_LONG).show();
        }
    }

    public void display(View view) {
        Intent intent = new Intent(this, photoDisplay.class);
        startActivityForResult(intent, DISPLAY_CODE);

    }

    //MOVING PICTURES
    public void Move(View view) {
        try {
            Intent i = new Intent(this, movePhoto.class);
            Bundle bundle = new Bundle();
            bundle.putInt("photoindex", index);
            i.putExtras(bundle);
            startActivityForResult(i, MOVE_CODE);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "No images!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.add) {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
        }
    }

    public void delete(View view) {
        size = album.photoList.size();
        if (!album.photoList.isEmpty()) {
            album.deletePhoto(index);
            size--;
            if (index - 1 >= 0 || size != 0) {
                if (index - 1 >= 0)
                    index--;
                else
                    index = 0;
                imageToUpload.setImageBitmap(album.getPhotoList().get(index).getImage());
                tags.setText(album.getPhotoList().get(index).getTag().toString());
            } else {
                try {
                    index = -1;
                    imageToUpload.setImageBitmap(null);
                } catch (Exception e) {

                }
            }
        } else {
            try {
                Toast.makeText(getApplicationContext(), "No photos to delete", Toast.LENGTH_LONG).show();
            } catch (Exception e) {

            }
        }
    }

    public void d() {
        size = album.photoList.size();
        album.deletePhoto(index);
        size--;
        if (index - 1 >= 0 || size != 0) {
            if (index - 1 >= 0)
                index--;
            else
                index = 0;
            imageToUpload.setImageBitmap(album.getPhotoList().get(index).getImage());
            tags.setText(album.getPhotoList().get(index).getTag().toString());
        } else {
            imageToUpload.setImageBitmap(null);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle bundle = new Bundle();
        switch (requestCode) {
            case (RESULT_LOAD_IMAGE): {
                if (resultCode == RESULT_OK && data != null) {
                    Uri IMAGE_URI = data.getData();
                    InputStream image_stream = null;
                    try {
                        image_stream = getContentResolver().openInputStream(IMAGE_URI);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(image_stream);
                        count++;
                        Photo photo = new Photo("photo " + count, "caption", bitmap);
                        album.addPhoto(photo);
                        index++;
                        imageToUpload.setImageBitmap(album.getPhotoList().get(index).getImage());
                        tags.setText(album.getPhotoList().get(index).getTag().toString());
                        return;
                    } catch (Exception e) {
                    }

                }
                break;
            }

            case (ADD_TAG_CODE): {
                try {
                    bundle = data.getExtras();
                } catch (Exception e) {
                    break;
                }
                if (bundle == null) {
                    return;
                }
                String name = bundle.getString(addTag.PERSON_TAG);
                String locations = bundle.getString(addTag.LOCATION_TAG);

                try {
                    tag = new Tag(name, locations);
                    album.getPhotoList().get(index).setTag(tag);
                    tags.setText(tag.toString());
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Must have photo for tag to work", Toast.LENGTH_LONG).show();
                    return;
                }
                break;
            }
            case (DISPLAY_CODE): {
                try {
                    index = pen;
                    imageToUpload.setImageBitmap(album.getPhotoList().get(index).getImage());
                    tags.setText(album.getPhotoList().get(index).getTag().toString());
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "No photos selected!", Toast.LENGTH_LONG).show();

                }
                break;

            }
            case (MOVE_CODE): {
                if (resultCode == RESULT_OK) {
                    Photo temp = albums.get(positions).getPhotoList().get(index);
                    d();
                    albums.get(posOfMove).getPhotoList().add(temp);
                }
            }
            default: {
                break;
            }
        }

    }

    public void addTag(View view) {
        Intent intent = new Intent(this, addTag.class);
        startActivityForResult(intent, ADD_TAG_CODE);
    }

    public void deleteTag(View view) {
        try {
            album.getPhotoList().get(index).setTag(new Tag(" ", " "));
            tags.setText(album.getPhotoList().get(index).getTag().toString());
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "No Tag Has Been Set Yet", Toast.LENGTH_LONG).show();
        }
    }

    public void searchTag(View view) {
        Bundle bundle = new Bundle();
        Intent i = new Intent(this, SearchTag.class);
        startActivityForResult(i, SEARCH_TAG_CODE);
    }
}