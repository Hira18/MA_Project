package com.example.project_ma.smarillance;

//import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.RemoteMessage;

//import android.app.NotificationCompat;

/**
 * Created by Hamzza on 6/28/2020.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    public void onMessageReceived(RemoteMessage remoteMessage) {
        //handle when receive notification via data event
        if(remoteMessage.getData().size()>0){
            showNotification(remoteMessage.getData().get("title"),remoteMessage.getData().get("message"));
        }

        //handle when receive notification
        if(remoteMessage.getNotification()!=null){
            showNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
        }

    }

    private RemoteViews getCustomDesign(String title, String message){
        RemoteViews remoteViews=new RemoteViews(getApplicationContext().getPackageName(), R.layout.notification);
        remoteViews.setTextViewText(R.id.title,title);
        remoteViews.setTextViewText(R.id.message,message);
        remoteViews.setImageViewResource(R.id.icon,R.drawable.applogo);
        return remoteViews;
    }

    public void showNotification(String title, String message){
        Intent intent=new Intent(this, startscreen.class);
        String channel_id="web_app_channel";
        intent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent= PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_ONE_SHOT);
        Uri uri= RingtoneManager.getDefaultUri( RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.applogo)
                .setSound(uri)
                .setAutoCancel(true)
                .setVibrate(new long[]{1000,1000,1000,1000,1000})
                .setOnlyAlertOnce(true)
                .setContentIntent(pendingIntent);

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.JELLY_BEAN){
            builder=builder.setContent(getCustomDesign(title,message));
        }
        else{
            builder=builder.setContentTitle(title)
                    .setContentText(message)
                    .setSmallIcon(R.drawable.applogo);
        }

        NotificationManager notificationManager= (NotificationManager) getSystemService( Context.NOTIFICATION_SERVICE);
       /* if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel notificationChannel=new NotificationChannel(channel_id,"web_app",NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setSound(uri,null);
            notificationManager.createNotificationChannel(notificationChannel);
        }*/

        notificationManager.notify(0,builder.build());
    }

    //app part ready now let see how to send differnet users
    //like send to specific device
    //like specifi topic
}
