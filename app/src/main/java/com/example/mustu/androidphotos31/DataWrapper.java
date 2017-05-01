package com.example.mustu.androidphotos31;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Mustufa on 4/30/2017.
 */

public class DataWrapper implements Serializable {

    private ArrayList<Photo> parliaments;

    public DataWrapper(ArrayList<Photo> data) {
        this.parliaments = data;
    }

    public ArrayList<Photo> getParliaments() {
        return this.parliaments;
    }

}