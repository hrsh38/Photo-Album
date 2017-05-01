package com.example.mustu.androidphotos31;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author Mustufa Hussain and Harsh Patel
 *
 */

public class Album implements Parcelable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4571691897272213938L;
	public static String albumName;
	public static ArrayList<Photo> photoList = new ArrayList<>();
	/**
	 * 
	 * @param albumName name of the album
	 * @param photoList list to hold the photos
	 */
	public Album(String albumName, ArrayList<Photo> photoList){
		this.albumName = albumName;
		photoList = new ArrayList<Photo>();
		this.photoList = photoList;
	}

	protected Album(Parcel in) {
        albumName = in.readString();
        photoList = in.readArrayList(Photo.class.getClassLoader());

	}

	public static final Creator<Album> CREATOR = new Creator<Album>() {
		@Override
		public Album createFromParcel(Parcel in) {
			return new Album(in);
		}

		@Override
		public Album[] newArray(int size) {
			return new Album[size];
		}
	};

	/**
	 * 
	 * @return the count of all the photos in an album
	 */
	public static int getPhotoCount(){
		return photoList.size();
	}

	public static void addPhoto(Photo photo){
        photoList.add(photo);
    }

    public static void deletePhoto(int index) {
        photoList.remove(index);
    }

    /**
	 * 
	 * @return the album name
	 */
	public static  String getAlbumName(){
		return albumName;
	}
	@Override
	public String toString() {
		return getAlbumName();
	}

	/**
	 * 
	 * @param albName passes a string
	 * @return changes it to what ever new string
	 */
	public static String setAlbumName(String albName){
		return albumName = albName;
	}
	/**
	 * 
	 * @return gets the photo list
	 */
	public static ArrayList<Photo> getPhotoList(){
		return photoList;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(albumName);
		parcel.writeList((photoList));
	}
}
