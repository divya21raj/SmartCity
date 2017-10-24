package com.javalab.City;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class City
{
    String name;

    public ArrayList<Location> locations;
    public Double cabRate;

    public Double[][] locationDistanceGraph;

    public City(String name, Double cabRate) throws IOException
    {
        this.name = name;
        this.cabRate = cabRate;

        locations = new ArrayList<>();

        FileReader fileReader = new FileReader("files/City/list_of_Locations.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        while(true)
        {
            String location = bufferedReader.readLine();

            if(location == null)
                break;

            locations.add(new Location(location));
        }

        for(Location ilocation : locations)
            ilocation.adjacentLocations.addAll(locations);

        fileReader = new FileReader("files/City/distance_between_Locations.txt");
        bufferedReader = new BufferedReader(fileReader);

        int i = 0, j;
        locationDistanceGraph = new Double[locations.size()][locations.size()];

        for(Location location : locations)
        {
            String distanceLine = bufferedReader.readLine();

            StringTokenizer tokenizer = new StringTokenizer(distanceLine, ",");

            j = 0;
            while(tokenizer.hasMoreTokens())
            {
                Double distance = Double.parseDouble(tokenizer.nextToken().trim());

                location.adjacentLocationDistance.add(distance);
                this.locationDistanceGraph[i][j++] = distance;
            }

            i++;
        }

    }

    /////////////////////////////////////GETTERS & SETTERS//////////////////////////////////


    public String getName()
    {
        return name;
    }

    public ArrayList<Location> getLocations()
    {
        return locations;
    }

    public Double getCabRate()
    {
        return cabRate;
    }
}
