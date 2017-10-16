package com.javalab;
import com.javalab.Storage.Storage;

public abstract class Drone
{
    protected String id;

    protected String currentLocation;

    Storage storage;

    protected Drone()
    {
        storage = new Storage();
        //initiate stuff willya...
    }

    void photoCapture()
    {
        System.out.println("Photo was taken!");
    }

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

    public String getCurrentLocation()
    {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation)
    {
        this.currentLocation = currentLocation;
    }

}
