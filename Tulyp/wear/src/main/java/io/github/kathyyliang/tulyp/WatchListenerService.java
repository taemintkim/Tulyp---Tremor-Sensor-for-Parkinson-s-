package io.github.kathyyliang.tulyp;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;

public class WatchListenerService extends WearableListenerService {
    private static final String START = "/start";
    public void onMessageReceived(MessageEvent messageEvent) {



        if( messageEvent.getPath().equalsIgnoreCase(START) ) {
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            sendLocalNotification(value);
//
//            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
//            Intent intent = new Intent(this, detail.class );
//            String[] message = value.split(";");
//
//
//            person = message[0];
//            location = message[1];
//            intent.putExtra("location", location);
//            intent.putExtra("person", person);
//            System.out.println("phone listener received " + location);
//
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//            //you need to add this flag since you're starting a new activity from a service
//
//
//
//
//            startActivity(intent);
//
//
//
//        }else if( messageEvent.getPath().equalsIgnoreCase(SHAKE) ) {
//
//            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
//            showShake = new Intent(this, results.class);
//            sendIntent = new Intent(this, PhoneToWatchService.class);
//            sendIntent.putExtra("TYPE", "show");
//
//            showShake.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            String[] coordinates = value.split(",");
//            currLong = coordinates[0];
//            currLat = coordinates[1];
//
//
//
//
//
//
//        }else {
//            super.onMessageReceived( messageEvent );
        }

    }
    private void sendLocalNotification(String s) {
        int notificationId = 001;

        // Create a pending intent that starts this wearable app
        Intent startIntent = new Intent(this, MainActivity.class).setAction(Intent.ACTION_MAIN);
        // Add extra data for app startup or initialization, if available
        startIntent.putExtra("extra", s);
        PendingIntent startPendingIntent =
                PendingIntent.getActivity(this, 0, startIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        Notification notify = new NotificationCompat.Builder(this)
                .setContentTitle(s)
//                .setSmallIcon(R.drawable.ic_launcher)
                .setAutoCancel(true)
                .setContentIntent(startPendingIntent)
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, notify);
    }

}
