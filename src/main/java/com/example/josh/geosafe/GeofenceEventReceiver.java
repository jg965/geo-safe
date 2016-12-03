package com.example.josh.geosafe;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.pathsense.android.sdk.location.PathsenseGeofenceEvent;
import com.pathsense.android.sdk.location.PathsenseGeofenceEventReceiver;

/**
 * Created by josh on 12/2/16.
 */

public class GeofenceEventReceiver extends PathsenseGeofenceEventReceiver {
    public static final int NOTIFICATION_ID = 106;
    @Override
    protected void onGeofenceEvent(Context context, PathsenseGeofenceEvent pathsenseGeofenceEvent) {
        if (pathsenseGeofenceEvent.isEgress()) {
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context)
                            .setSmallIcon(R.drawable.pathsense)
                            .setContentTitle("GeoSafe")
                            .setContentText("You've entered a human trafficing hotspot, please be careful!");
            NotificationManager mNotificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
        }
    }
}
