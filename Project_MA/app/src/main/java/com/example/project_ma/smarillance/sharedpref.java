package com.example.project_ma.smarillance;

import android.content.Context;
import android.content.SharedPreferences;

/**
 *
 */

public class sharedpref {

    SharedPreferences h;

    public sharedpref(Context c){
    h = c.getSharedPreferences("Savenum", Context.MODE_PRIVATE);
}




    public void savedata(String phonenum){
    SharedPreferences.Editor editor=h.edit();
    editor.putString("phn",phonenum);
    editor.commit();
}

public String[] getdata(){
    String[] result=new String[1];
    result[0]= h.getString("phn","");
    return result;
}}


