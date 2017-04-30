package com.example.mustu.androidphotos31;


import android.media.Image;
import android.net.Uri;

import java.io.File;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author Mustufa Hussain and Harsh Patel
 *
 */

public class Photo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1634564310973488332L;
	private String photoName;
	private String caption;
	private Tag tag1 = new Tag("Person ", "Location");

	private String photoAdr;
	private String dateCreated;
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private Uri im;
	
	/**
	 * 
	 * @param photoName name of the photo
	 * //@param url location of the photo
	 * @param caption list to hold a photo's captions
	 * @param tag list to hold a photo's tags
	 */
	public Photo(String photoName, String caption, Uri img){
		this.photoName = photoName;
		//this.photoAdr = url;
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MILLISECOND, 0);
		this.dateCreated = dateFormat.format(cal.getTime()); //sets the time
		this.caption = caption;
		this.caption = caption; //makes a new empty caption list
		im = img;

	}

	public Uri getImage(){
		return im;
	}
	/**
	 * 
	 * @return the tag list
	 */
	/**
	 * 
	 * @return the name of the photo
	 */
	public String getPhotoName(){
		return photoName;
	}
	
	/**
	 * Set new name for photo
	 * @param String: photoName
	 */
	public void setPhotoName(String photoName){
		this.photoName = photoName;
	}
	
	/**
	 *  
	 * @return the file address of the photo
	 */
	public String getPhotoAdr(){
		return photoAdr;
	}
	
	/**
	 * 
	 * @return the date of creation
	 */
	public String getDate(){
		return dateCreated;
	}
	
	/**
	 * Return photo caption
	 * @return
	 */
	public String getCaption() 
	{
		return caption;
	}
	
	/**
	 * Set new photo caption
	 * @param caption
	 */
	public void setCaption(String caption){
		this.caption = caption;
	}
	
	/**
	 * Retrieve total number of tags attached to photo
	 * @return int
	 */

	
	/**
	 * Set new tag if there is not currently a tag of the same type on the photo
	 * @param tagType
	 * @param tagValue
	 */
	public void setTag(Tag tag){
		//Tag tag = new Tag(tagType, tagValue);
		// Add tag pair if it's not a duplicate or tag list is currently empty
		this.tag1 = tag;
	}
	public Tag getTag(){
		return tag1;
	}

	public boolean checkTag(){
		if(tag1.getValue()== null && tag1.getValue() == null){
			return false;
		}
		return true;
	}
	/**
	 * Remove the specified tag type from photo tag list
	 * @param String: tagType
	 */
	public void deleteTag(){
		tag1 =  null;
	}
	
	/**
	 * Check if the tag type is already added
	 * @param tag
	 * @return
	 */

		
}