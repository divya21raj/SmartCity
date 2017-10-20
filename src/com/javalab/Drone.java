package com.javalab;

import com.javalab.City.Location;

import java.io.IOException;
import java.io.Serializable;

import static com.javalab.UtilityMethods.randomID;

public abstract class Drone implements Serializable
{
    private String id;

    private Location currentLocation;

    protected Drone()
    {
        currentLocation = null;

        id = randomID();
    }

    void photoCapture()
    {
        System.out.println("Photo was taken!");
    }

    protected abstract void interact() throws IOException;

    protected abstract void contactCop();

/////////////////////////////////GETTERS AND SETTERS////////////////////////////////////////////////////

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public Location getCurrentLocation()
    {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation)
    {
        this.currentLocation = currentLocation;
    }

}
