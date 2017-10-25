package com.javalab.City;

import com.javalab.Drone;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Location
{
    public String name;
    ArrayList<Location> adjacentLocations;
    ArrayList<Double> adjacentLocationDistance;
    public Category category;

    private ArrayList<Drone> drones;

    Location(String name) throws IOException
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

    public void setAdjacentLocations(ArrayList<Location> adjacentLocations)
    {
        this.adjacentLocations = adjacentLocations;
    }

    public ArrayList<Double> getAdjacentLocationDistance()
    {
        return adjacentLocationDistance;
    }

    public void setAdjacentLocationDistance(ArrayList<Double> adjacentLocationDistance)
    {
        this.adjacentLocationDistance = adjacentLocationDistance;
    }

    public ArrayList<Drone> getDrones()
    {
        return drones;
    }

    public void setDrones(ArrayList<Drone> drones)
    {
        this.drones = drones;
    }
}
