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

    public City(String name) throws IOException
    {
        this.name = name;

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

        for(Location location : locations)
        {
            String distanceLine = bufferedReader.readLine();

            StringTokenizer tokenizer = new StringTokenizer(distanceLine, ",");

            while(tokenizer.hasMoreTokens())
                location.adjacentLocationDistance.add(Double.parseDouble(tokenizer.nextToken().trim()));
        }

    }


}
