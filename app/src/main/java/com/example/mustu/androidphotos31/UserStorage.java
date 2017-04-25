package com.example.mustu.androidphotos31;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class UserStorage implements Serializable {
	
	private static final long serialVersionUID = 7292986950430510446L;	//To ensure the data is returend the same way its saved
	ArrayList<User> userSavingList;
	public static final String storeDir = "data";
	public static final String storeFile = "users.dat";
		
	public UserStorage(){
		userSavingList = new ArrayList<User>();
	}
	
	public void addUserToSavedList(User p){
		userSavingList.add(p);
	}
	
	public int getUserStorageListSize(){
		return userSavingList.size();
	}
	
	public ArrayList<User> getSavedList(){
		return userSavingList;
	}

	public static void write(UserStorage user)throws IOException{
		ObjectOutputStream saveTo = new ObjectOutputStream(new FileOutputStream(storeDir +File.separator+storeFile));
		saveTo.writeObject(user);
	}
	
	public static void write()throws IOException{
		ObjectOutputStream saveTo = new ObjectOutputStream(new FileOutputStream(storeDir +File.separator+storeFile));
	}
	
	
	public static UserStorage read()throws IOException, ClassNotFoundException{
		ObjectInputStream loadFrom = new ObjectInputStream(new FileInputStream(storeDir + File.separator+ storeFile ));
		return (UserStorage)loadFrom.readObject();
	}
}
