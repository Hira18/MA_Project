package com.example.project_ma.smarillance;

import android.Manifest;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import com.example.project_ma.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class smsservice extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    final int handlerState = 0;
    private Button sendnoti;
    private TextView num, numtwo;
    //<------------------>
    //protected LocationManager locationManager;
    //protected LocationListener locationListener;
    //protected Context context;
    TextView txtLat;
    String lat;
    String provider;
    protected String latitude, longitude;
    protected boolean gps_enabled, network_enabled;
    private FusedLocationProviderClient fusedLocationClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_smsservice);

        //final SharedPreferences pref=getApplicationContext().getSharedPreferences("myprefs",0);
        //final SharedPreferences.Editor editor=pref.edit();

        sendnoti = (Button) findViewById(R.id.sendnoti);
        num = (TextView) findViewById(R.id.textViewnum);
        numtwo = (TextView) findViewById(R.id.textViewNumtwo);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                            String abc;
                            abc = location.toString();
                            numtwo.setText(abc);
                        }
                    }
                });


        sendnoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hamza();
                addNotification();
            }
        });
    }


    public void hamza() {
        String[] data = new String[1];
        sharedpref pref = new sharedpref(this);
        data = pref.getdata();
        num.setText(data[0]);
        //numtwo.setText(data[1]);
        //String locationlink=txtLat.getText().toString();
        String phn = num.getText().toString();
        String myloc = numtwo.getText().toString();
        //String phntwo=numtwo.getText().toString();
        try {

            SmsManager smgr = SmsManager.getDefault();
            smgr.sendTextMessage((phn), null, "Smarillance  Alert.\nYour User Is In Critical Situation.\n" + "At Location:" + "Https://maps.google.com/maps?q="+myloc.substring(14, 32).toString(), null, null);
            Toast.makeText(smsservice.this, "SMS Sent Successfully", Toast.LENGTH_SHORT).show();
            // TODO Auto-generated method stub
            String phone_no = phn.replaceAll("-", "");
            Intent callIntent = new Intent( Intent.ACTION_CALL);
            //callIntent.setData(Uri.parse(phone_no));
            callIntent.setData( Uri.parse("tel:" + phone_no));
            //callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivity(callIntent);
        }
        catch (Exception e){
            Toast.makeText(smsservice.this, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(smsservice.this, "sent", Toast.LENGTH_SHORT).show();


    }


        private void addNotification() {
            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.drawable.applogo)
                            .setContentTitle("Smarillance Alert.")
                            .setContentText("Your User is in critical situation.");

            Intent notificationIntent = new Intent(this, smsservice.class);
            PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            Uri alarmSound = RingtoneManager.getDefaultUri( RingtoneManager.TYPE_NOTIFICATION);
            builder.setContentIntent(contentIntent);
            builder.setAutoCancel(true);
            builder.setLights( Color.BLUE, 500, 500);
            long[] pattern = {500,500,500,500,500,500,500,500,500};
            builder.setVibrate(pattern);
            builder.setSound(alarmSound);
            builder.setStyle(new NotificationCompat.InboxStyle());

            // Add as notification
            NotificationManager manager = (NotificationManager) getSystemService( Context.NOTIFICATION_SERVICE);
            manager.notify(0, builder.build());
        }



}

