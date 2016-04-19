package io.github.kathyyliang.tulyp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;

public class PhoneListenerService extends WearableListenerService {
    public void onMessageReceived(MessageEvent messageEvent) {
        String person;
        String location;


//        if( messageEvent.getPath().equalsIgnoreCase(DETAIL) ) {
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
//        }else {
//            super.onMessageReceived( messageEvent );
//        }

    }

}
