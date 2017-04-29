package com.example.mustu.androidphotos31;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by harsh on 4/29/2017.
 */

public class ImageAdapter extends BaseAdapter {


    public Uri imageUri;


    private Context mContext;

    public ImageAdapter(Context c) {
        this.mContext = c;
    }

    @Override
    public int getCount() {

        return 5;
    }

    @Override
    public Object getItem(int position) {

        return null;
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }




    public View getView(int position, View convertView, ViewGroup parent) {

        imageUri = null;


        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageURI(null);
        imageView.setImageURI(imageUri);

        return imageView;
    }
}