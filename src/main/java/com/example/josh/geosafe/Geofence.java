package com.example.josh.geosafe;

/**
 * This class defines a discrete representation of a geofence. We will populate these
 * using our fancy CSV file, and register geofences that way. Cool
 */

public class Geofence {
    private double latitude, longitude;
    private String id, type;

    public Geofence(double latitude, double longitude, String id, String type) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.id = id;
        this.type = type;
    }

    /**
     * Getter for latitude of the geofence
     *
     * @return the latitude of the geofence
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Setter for the latitude of the geofence
     *
     * @param latitude: new latitude of the geofence
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Getter for the longitude of the geofence
     *
     * @return: the longitude of the geofence
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Setter for the longitude of the geofence
     *
     * @param longitude: new longitude for the geofence
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Getter for the id of the geofence
     *
     * @return the id of the geofence
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for the id of the geofence
     *
     * @param id: new id for the geofence
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for the type of geofence
     *
     * @return: the type of the geofence
     */
    public String getType() {
        return type;
    }

    /**
     * Setter for the type of geofence
     *
     * @param type: the new type of the geofence
     */
    public void setType(String type) {
        this.type = type;
    }
}
