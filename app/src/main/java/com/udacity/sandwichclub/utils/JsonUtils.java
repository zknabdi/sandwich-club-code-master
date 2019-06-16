package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.udacity.sandwichclub.MainActivity;
import java.util.ArrayList;

public class JsonUtils {
    private static String TAGISSUE = MainActivity.class.getSimpleName();
    private static ArrayList<String> listofKnownAS;
    private static ArrayList<String> ingredArrayList;
    public static Sandwich parseSandwichJson(String json) {

        try {
            JSONObject jsonRoot = new JSONObject(json); //Initial the root of the file
            JSONObject name = jsonRoot.getJSONObject("name"); //getting JSON object
            String mName =  name.getString("mainName");
            JSONArray alsoKnownAsArray = name.getJSONArray("alsoKnownAs");
            listofKnownAS = new ArrayList<>(); //adding list of known as in temp arrayList
            for(int i =0; i< alsoKnownAsArray.length(); i++){
                listofKnownAS.add(alsoKnownAsArray.getString(i));
            }

            String pOfOrigin = jsonRoot.getString("placeOfOrigin");
            String desc = jsonRoot.getString("description");
            String img = jsonRoot.getString("image");
            JSONArray ingreArray = jsonRoot.getJSONArray("ingredients");
            ingredArrayList = new ArrayList<>();
            for(int i= 0; i <ingreArray.length(); i++){
                ingredArrayList.add(ingreArray.getString(i));
            }

            return  new Sandwich(mName, listofKnownAS, pOfOrigin,desc,img,ingredArrayList);

        } catch ( final JSONException ex) {
            Log.e(TAGISSUE,"JSON parsing Error: "+ ex.getMessage());

        }
        return null;
    }
}
