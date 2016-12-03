package com.example.josh.geosafe;

import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Switch;

import com.pathsense.android.sdk.location.PathsenseLocationProviderApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final int RANGE = 500;
    public static final String isRegistered = "REGISTERED";
    private static final String MY_PREFS_NAME = "GeoSafePrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        boolean registered = prefs.getBoolean(isRegistered, false);
        Switch mySwitch = (Switch) findViewById(R.id.mySwitch);
        mySwitch.setChecked(registered);
        mySwitch.setText(registered ? "Alerts On" : "Alerts Off");
    }

    /**
     * This method handles the clicking of the toggle switch for alerts in the app
     * @param view: the switch
     */
    public void checkSwitch(View view) {
        Switch mySwitch = (Switch) findViewById(R.id.mySwitch);
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        if (mySwitch.isChecked()) {
            registerGeofences(view);
            editor.putBoolean(isRegistered, true);
            editor.apply();
            mySwitch.setText("Alerts On");
        } else {
            editor.putBoolean(isRegistered, false);
            editor.apply();
            mySwitch.setText("Alerts Off");
            unregisterGeofences(view);
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.cancel(GeofenceEventReceiver.NOTIFICATION_ID);
        }
    }

    /**
     * This handles registration of geofences
     * @param view: required parameter to make this an onclick of a button
     */
    public void registerGeofences(View view) {
        PathsenseLocationProviderApi providerApi = PathsenseLocationProviderApi.getInstance(view.getContext());
        Geofence[] geofences = getGeofences();
        for (Geofence geofence : geofences) {
            if (geofence.getType().equals("caution")) {
                providerApi.addGeofence(geofence.getId(), geofence.getLatitude(), geofence.getLongitude(), RANGE, GeofenceEventReceiver.class);
            }
        }
    }

    /**
     * This handles unregistration of the geofence
     * @param view: required parameter to make this an onclick of a button
     */
    public void unregisterGeofences(View view) {
        PathsenseLocationProviderApi providerApi = PathsenseLocationProviderApi.getInstance(view.getContext());
        Geofence[] geofences = getGeofences();
        providerApi.removeGeofences();
        providerApi.destroy();
    }

    /**
     * Method to access the assets file for geofence hotspot information
     * and gather a list of hotspot geofences
     * @return a list of geofences representing our hotspots
     */
    private Geofence[] getGeofences() {
        try {
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(this.getAssets().open("data_philippines.csv")));
            ArrayList<String> lines = new ArrayList<>();
            String str = inputStream.readLine();
            while (str != null) {
                lines.add(str);
                str = inputStream.readLine();
            }
            Geofence[] geofences = new Geofence[lines.size()];
            for (int i = 0; i < lines.size(); i++) {
                String[] infoBlobs = lines.get(i).split(",");
                geofences[i] = new Geofence(Double.parseDouble(infoBlobs[1]), Double.parseDouble(infoBlobs[2]), infoBlobs[0], infoBlobs[3]);
            }
            return geofences;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Geofence[0];
    }
}
