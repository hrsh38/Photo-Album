package com.example.mustu.androidphotos31;

import java.io.Serializable; 

/**
 * 
 * @author Mustufa Hussain and Harsh Patel
 *
 */

public class Tag implements Serializable 
{
	private static final long serialVersionUID = 7526472295622776147L;
	
	
	public String tagType; 						// e.g. types are location, person, etc
	public String tagValue;						
	
	
	public Tag(String tagType, String tagValue){
		this.tagType = tagType; 
		this.tagValue = tagValue;
	}
	
	/**
	 * 
	 * @return String 
	 * Returns the tagValue
	 */
	public String getValue(){
		return tagValue;
	}

	public void setTagType(String tagType){
		this.tagType = tagType;
	}

	public void setTagValue(String tagValue){
		this.tagValue = tagValue;
	}
	
	/**
	 * Return the tag's type. E.g. location, person, etc
	 * @return String
	 */
	public String getType(){
		return tagType;
	}
	
	/**
	 * Convert tagType and tagValue into a string to display to user
	 */
	public String toString(){
		return tagType + ": " + tagValue+" ";
	}
}