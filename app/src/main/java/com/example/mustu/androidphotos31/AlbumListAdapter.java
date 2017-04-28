package com.example.mustu.androidphotos31;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.List;

/**
 * Created by mustu on 4/28/2017.
 */


public class AlbumListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Album> albumList;

    public AlbumListAdapter(Context mContext, List<Album> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public int getCount(){
        return albumList.size();
    }

    @Override
    public Object getItem(int position){
        return albumList.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View v = View.inflate(mContext, R.layout.album, null);
        TextView text = (TextView) v.findViewById(R.id.text_aaa);
        v.setTag(albumList.get(position).getAlbumName());
        return v;
    }
}
