package com.javalab;

import com.javalab.City.Location;

import java.util.ArrayList;

public class UtilityMethods
{
    public static void clrscr()
    {
        for(int i = 0; i<70; i++)     //works as clrscr()
            System.out.println();
    }

    public static Location numSelectiontoLocation(int num, ArrayList<Location> locations)
    {
        Location location = null;

        if(num < locations.size())
            location = locations.get(num-1);

        return location;
    }

}
