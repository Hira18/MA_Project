package com.example.project_ma.smarillance;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_ma.R;

import java.util.Calendar;

public class startscreen extends AppCompatActivity {
    private static int Spalshtimeout=4000;
    private Calendar FirebaseInstanceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_startscreen);
//for sending to all device using own server code subscribe your app to one topic
        Log.d("TOken ",""+FirebaseInstanceId.getInstance().get());
        FirebaseInstanceId.getInstance().set("allDevices");
        //intent active
        new Handler().postDelayed( new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        },Spalshtimeout);
    }


}
