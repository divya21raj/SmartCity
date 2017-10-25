package com.javalab.City;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static com.javalab.UtilityMethods.addCategory;

public class City
{
    private final String name;

    public ArrayList<Location> locations;
    private ArrayList<Category> locationCategories;
    public Double cabRate;

    public Double[][] locationDistanceGraph;

    private static String fileSeparator = File.separator;

    public City(String name, Double cabRate) throws IOException
    {
        this.name = name;
        this.cabRate = cabRate;

        locations = new ArrayList<>();
        locationCategories = new ArrayList<>();

        FileReader fileReader = new FileReader("files"+ fileSeparator +"City"+ fileSeparator +"list_of_Locations.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        while(true)
        {
            String location = bufferedReader.readLine();
            String locationName = null, locationCategory;


            if(location == null)
                break;

            StringTokenizer stringTokenizer = new StringTokenizer(location, ",");
            int k = 0;

            while (stringTokenizer.hasMoreTokens())
            {
                if(k%2 == 0)
                    locationName = stringTokenizer.nextToken();
                else
                {
                    locationCategory = stringTokenizer.nextToken().trim();
                    Location temp = new Location(locationName);
                    locations.add(temp);
                    addCategory(locationCategory, locationCategories, temp);
                }
                k++;
            }
        }

        fileReader = new FileReader("files"+ fileSeparator +"City"+ fileSeparator +"distance_between_Locations.txt");
        bufferedReader = new BufferedReader(fileReader);

        int i = 0, j, k;
        locationDistanceGraph = new Double[locations.size()][locations.size()];

        for(Location location : locations)
        {
            String distanceLine = bufferedReader.readLine();

            StringTokenizer tokenizer = new StringTokenizer(distanceLine, ",");

            j = 0;
            k = 0;
            while(tokenizer.hasMoreTokens())
            {
                Double distance = Double.parseDouble(tokenizer.nextToken().trim());

                if(distance != 0d)
                    location.adjacentLocations.add(locations.get(k));

                location.adjacentLocationDistance.add(distance);
                this.locationDistanceGraph[i][j++] = distance;

                k++;
            }

            i++;
        }

    }

    /////////////////////////////////////GETTERS & SETTERS//////////////////////////////////


// --Commented out by Inspection START (25/10/2017 20:16):
//    public String getName()
//    {
//        return name;
//    }
// --Commented out by Inspection STOP (25/10/2017 20:16)

    public ArrayList<Location> getLocations()
    {
        return locations;
    }

// --Commented out by Inspection START (25/10/2017 20:16):
//    public Double getCabRate()
//    {
//        return cabRate;
//    }
// --Commented out by Inspection STOP (25/10/2017 20:16)
}
