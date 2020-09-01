package com.example.project_ma.smarillance;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.project_ma.R;

public class dashboard extends AppCompatActivity {

    private CardView locationmap,help,sendsms,guardienreg;
    private TextView appsup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_dashboard);

        locationmap=(CardView)findViewById(R.id.bankcardId);
        sendsms=(CardView)findViewById(R.id.bankcardId1);
        guardienreg=(CardView)findViewById(R.id.bankcardId2);
        help=(CardView)findViewById(R.id.bankcardId3);
        appsup=(TextView)findViewById(R.id.steps);


        locationmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




            }});
       appsup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(getApplicationContext(),appsupport.class);
                startActivity(intent);


            }});
        sendsms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(getApplicationContext(),smsservice.class);
                startActivity(intent);


            }});
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




            }});

        guardienreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(getApplicationContext(),guardienreg.class);
                startActivity(intent);


            }});
    }
}
