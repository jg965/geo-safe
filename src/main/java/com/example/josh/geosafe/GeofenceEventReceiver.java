package com.example.josh.geosafe;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.pathsense.android.sdk.location.PathsenseGeofenceEvent;
import com.pathsense.android.sdk.location.PathsenseGeofenceEventReceiver;

/**
 * Listener for geofence events. If a geofence event fires, this means that
 * you are located in ahuman trafficking hotspot. In this case, we will use
 * a notification to warn you. Upon leaving the hotspot, we will show another
 * notification that informs you that you have left the hotspot.
 */

public class GeofenceEventReceiver extends PathsenseGeofenceEventReceiver {
    public static final int NOTIFICATION_ID = 106;

    @Override
    protected void onGeofenceEvent(Context context, PathsenseGeofenceEvent pathsenseGeofenceEvent) {
        System.out.println(pathsenseGeofenceEvent.getGeofenceId());
        System.out.println(pathsenseGeofenceEvent.getLocation());
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.pathsense)
                        .setContentTitle("GeoSafe")
                        .setContentText(pathsenseGeofenceEvent.isIngress() ?
                                "You've entered a human trafficking hotspot, please be careful!" :
                                "All clear, you've exited the human trafficking hotspot")
                        .setVibrate(new long[]{0, 500, 10, 500, 10, 500});
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());

    }
}
