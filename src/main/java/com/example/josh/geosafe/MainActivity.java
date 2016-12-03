package com.example.josh.geosafe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void registerGeofences(View view){
        //TODO: add geofences
    }

    public void unregisterGeofences(View view){
        //TODO: remove geofences
    }

    private Geofence[] getGeofences(){
        //TODO: get geofences
        return new Geofence[0];
    }
}
