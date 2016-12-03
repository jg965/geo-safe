package com.example.josh.geosafe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.pathsense.android.sdk.location.PathsenseLocationProviderApi;

public class MainActivity extends AppCompatActivity {
    public static final int RANGE = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void registerGeofences(View view){
        //TODO: add geofences
        PathsenseLocationProviderApi providerApi = PathsenseLocationProviderApi.getInstance(view.getContext());
        Geofence[] geofences = getGeofences();
        for(Geofence geofence: geofences){
            if(geofence.getType().equals("caution")){
                providerApi.addGeofence(geofence.getId(),geofence.getLatitude(),geofence.getLongitude(),500,GeofenceEventReceiver.class);
            }
        }
    }

    public void unregisterGeofences(View view){
        PathsenseLocationProviderApi providerApi = PathsenseLocationProviderApi.getInstance(view.getContext());
        Geofence[] geofences = getGeofences();
        providerApi.removeGeofences();
        providerApi.destroy();
    }

    private Geofence[] getGeofences(){
        //TODO: get geofences
        return new Geofence[0];
    }
}
