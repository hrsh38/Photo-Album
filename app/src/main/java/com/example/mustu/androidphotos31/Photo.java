package com.example.mustu.androidphotos31;


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
	private ArrayList<Tag> tagList;
	private String photoAdr;
	private String dateCreated;
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	/**
	 * 
	 * @param photoName name of the photo
	 * @param url location of the photo
	 * @param caption list to hold a photo's captions
	 * @param tag list to hold a photo's tags
	 */
	public Photo(String photoName, String url, String caption){
		this.photoName = photoName;
		this.photoAdr = url;
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MILLISECOND, 0);
		this.dateCreated = dateFormat.format(cal.getTime()); //sets the time
		this.caption = caption;
		tagList = new ArrayList<Tag>();
		this.caption = caption; //makes a new empty caption list
	}
	/**
	 * 
	 * @return the tag list
	 */
	public ArrayList<Tag> getTagList(){
		return tagList;
	}
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
	public int getTotalTags(){
		return tagList.size();
	}
	
	/**
	 * Set new tag if there is not currently a tag of the same type on the photo
	 * @param tagType
	 * @param tagValue
	 */
	public void setTag(String tagType, String tagValue){
		Tag tag = new Tag(tagType, tagValue);
		// Add tag pair if it's not a duplicate or tag list is currently empty
		if (!isDuplicateTagType(tag) || tagList.size() == 0){
			tagList.add(tag);
		}
	}
	
	/**
	 * Remove the specified tag type from photo tag list
	 * @param String: tagType
	 */
	public void deleteTag(String tagType){
		for (int i = 0; i < tagList.size(); i++){
			String currTagType = tagList.get(i).tagType;
			if (tagType.equals(currTagType))
				tagList.remove(i);
		}
	}
	
	/**
	 * Check if the tag type is already added
	 * @param tag
	 * @return
	 */
	private boolean isDuplicateTagType(Tag tag){
		for (int i = 0; i < tagList.size(); i++){
			if (tag.tagType.equalsIgnoreCase(tagList.get(i).tagType))
				return true;
		}
		return false;
	}
		
}