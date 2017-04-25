package com.example.mustu.androidphotos31;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author Mustufa Hussain and Harsh Patel
 *
 */

public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5680794215291142584L;
	public String userName;
	public ArrayList<Album> albumList;
	/**
	 * 
	 * @param userName name of the user
	 * @param albumList the list containing all the albums
	 */
	public User (String userName, ArrayList<Album> albumList ){
		this.userName = userName;
		albumList = new ArrayList<Album>();
		this.albumList = albumList;
	}
	/**
	 * 
	 * @return the selected username
	 */
	public String getUserName() {
		return userName; 
	}
}
