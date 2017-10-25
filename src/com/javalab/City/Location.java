package com.javalab.City;

import com.javalab.Drone;

import java.io.IOException;
import java.util.ArrayList;

public class Location
{
    public final String name;
    ArrayList<Location> adjacentLocations;
    ArrayList<Double> adjacentLocationDistance;
    public Category category;

    private ArrayList<Drone> drones;

    Location(String name)
    {
        this.name = name;

        adjacentLocations = new ArrayList<>();
        adjacentLocationDistance = new ArrayList<>();

        drones = new ArrayList<>();
    }

    ////////////////////////////////GETTERS & SETTERS///////////////////////////////////////////////////

    public ArrayList<Location> getAdjacentLocations()
    {
        return adjacentLocations;
    }

// --Commented out by Inspection START (25/10/2017 20:16):
//    public void setAdjacentLocations(ArrayList<Location> adjacentLocations)
//    {
//        this.adjacentLocations = adjacentLocations;
//    }
// --Commented out by Inspection STOP (25/10/2017 20:16)

// --Commented out by Inspection START (25/10/2017 20:16):
//    public ArrayList<Double> getAdjacentLocationDistance()
//    {
//        return adjacentLocationDistance;
//    }
// --Commented out by Inspection STOP (25/10/2017 20:16)

// --Commented out by Inspection START (25/10/2017 20:16):
//    public void setAdjacentLocationDistance(ArrayList<Double> adjacentLocationDistance)
//    {
//        this.adjacentLocationDistance = adjacentLocationDistance;
//    }
// --Commented out by Inspection STOP (25/10/2017 20:16)

    public ArrayList<Drone> getDrones()
    {
        return drones;
    }

// --Commented out by Inspection START (25/10/2017 20:16):
//    public void setDrones(ArrayList<Drone> drones)
//    {
//        this.drones = drones;
//    }
// --Commented out by Inspection STOP (25/10/2017 20:16)
}
