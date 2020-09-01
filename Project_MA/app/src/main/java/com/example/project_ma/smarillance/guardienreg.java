package com.example.project_ma.smarillance;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_ma.R;

public class guardienreg extends AppCompatActivity {

    private EditText gnewuserid,gnewuserpass,gnewuserage,gphonetwo;
    private Button gregbtn;
    private TextView dialer;
    private RadioButton gmale,gfemale;
    int mblnum;
    String phnnum;
    static final int REQUEST_SELECT_PHONE_NUMBER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardienreg);
        gnewuserid = (EditText) findViewById( R.id.etnewuseremailg);
        dialer=(TextView)findViewById(R.id.phoneapp);
        //gphonetwo=(EditText)findViewById(R.id.gphntwo);
        gnewuserpass = (EditText) findViewById(R.id.etnewuserpassg);
        gmale = (RadioButton) findViewById(R.id.radioButtonmaleg);
        gfemale = (RadioButton) findViewById(R.id.radioButtonfemaleg);
        gnewuserage = (EditText) findViewById(R.id.etnewuserageg);
        gregbtn = (Button) findViewById(R.id.btnregisterg);

        //SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        //final SharedPreferences.Editor editor = pref.edit();

        gmale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gfemale.setChecked(false);
            }
        });
        gfemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gmale.setChecked(false);
            }
        });

        dialer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectContact();
            }
        });


        gregbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //phnnum=gnewuserage.getText().toString();


                //editor.putString("guardiancont",phnnum);
                //editor.commit();
                //upload data to firebase database
                //validation
                //sharedpref pref = new sharedpref();
                //pref.savedata(gnewuserage.getText().toString());
                saving();

                if (gnewuserid.getText().toString().matches("") || gnewuserpass.getText().toString().matches("") || gnewuserage.getText().toString().matches("")) {
                    Toast.makeText(getApplicationContext(), "Fill Required Fields.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }

            }
        });//firebase/*

    }
        public void saving()
    {
        sharedpref pref = new sharedpref(this);
        pref.savedata(gnewuserage.getText().toString());
        //pref.savedata(gphonetwo.getText().toString());
    }

    public void selectContact() {
        // Start an activity for the user to pick a phone number from contacts
        Intent intent = new Intent( Intent.ACTION_PICK);
        intent.setType( ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_SELECT_PHONE_NUMBER);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SELECT_PHONE_NUMBER && resultCode == RESULT_OK) {
            // Get the URI and query the content provider for the phone number
            Uri contactUri = data.getData();
            String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER};
            Cursor cursor = getContentResolver().query(contactUri, projection,
                    null, null, null);
            // If the cursor returned is valid, get the phone number
            if (cursor != null && cursor.moveToFirst()) {
                int numberIndex = cursor.getColumnIndex( ContactsContract.CommonDataKinds.Phone.NUMBER);
                String number = cursor.getString(numberIndex);
                // Do something with the phone number
                gnewuserage.setText(number);
                //...
            }
        }

    }}
