package com.example.mustu.androidphotos31;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author Mustufa Hussain and Harsh Patel
 *
 */

public class Album implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4571691897272213938L;
	public String albumName;
	public ArrayList<Photo> photoList = new ArrayList<>();
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
	/**
	 * 
	 * @return the count of all the photos in an album
	 */
	public int getPhotoCount(){
		return photoList.size();
	}

	public void addPhoto(Photo photo){
        photoList.add(photo);
    }

    public void deletePhoto(int index) {
        photoList.remove(index);
    }

    /**
	 * 
	 * @return the album name
	 */
	public String getAlbumName(){
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
	public String setAlbumName(String albName){
		return this.albumName = albName;
	}
	/**
	 * 
	 * @return gets the photo list
	 */
	public ArrayList<Photo> getPhotoList(){
		return photoList;
	}
}
