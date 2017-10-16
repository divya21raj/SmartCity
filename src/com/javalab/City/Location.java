package com.javalab.City;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Location
{
    public String name;
    ArrayList<Location> adjacentLocations;
    ArrayList<Double> adjacentLocationDistance;

    Location(String name) throws IOException
    {
        this.name = name;

    }

}
