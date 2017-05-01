package com.example.mustu.androidphotos31;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by harsh on 4/29/2017.
 */

public class ImageAdapter extends BaseAdapter {


    public Bitmap imageUri;
    Bitmap[] photo;

    private Context mContext;

    public ImageAdapter(Context c, Bitmap[] photo) {
        mContext = c;
        this.photo = photo;
    }

    @Override
    public int getCount() {

        return photo.length;
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
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(1, 1, 1, 1);
        }
        else
        {
            imageView = (ImageView) convertView;
        }
        imageView.setImageBitmap(photo[position]);
        return imageView;
    }

}