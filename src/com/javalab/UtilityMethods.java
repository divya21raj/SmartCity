package com.javalab;

import com.javalab.City.Location;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class UtilityMethods
{
    static void clrscr()
    {
        for(int i = 0; i<70; i++)     //works as clrscr()
            System.out.println();
    }

    static Location numSelectiontoLocation(int num, ArrayList<Location> locations)
    {
        Location location = null;

        if(num <= locations.size())
            location = locations.get(num-1);

        return location;
    }

    static int checkUsers(String userName, ArrayList<User> users) throws IOException
    {
        int index = -1;

        for(User user: users)
        {
            if (user.getName().equals(userName))
            {
                index = users.indexOf(user);
                break;
            }
        }
        return index;
    }

    static Location findLocation(String locationName, ArrayList<Location> locations)
    {
        Location key = null;

        for(Location location: locations)
        {
            if(location.name.equals(locationName))
            {
                key = location;
                break;
            }
        }

        return key;

    }

    static void writeUser(User user) throws IOException
    {
        FileWriter namefileWriter = new FileWriter("files/Users/Names.txt");
        FileWriter locfileWriter = new FileWriter("files/Users/Locations.txt");

        BufferedWriter nbufferedWriter = new BufferedWriter(namefileWriter);
        BufferedWriter lbufferedWriter = new BufferedWriter(locfileWriter);

        nbufferedWriter.write(user.getName());
        nbufferedWriter.newLine();
        lbufferedWriter.write(user.getLocation().name);
        lbufferedWriter.newLine();

        nbufferedWriter.close();
        lbufferedWriter.close();
        namefileWriter.close();
        locfileWriter.close();
    }

}
