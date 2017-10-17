package com.javalab;

import com.javalab.City.Location;
import com.javalab.Drones.CopDrone;
import com.javalab.Drones.FunnyDrone;
import com.javalab.Drones.MessengerDrone;
import com.javalab.Drones.TourGuideDrone;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

class UtilityMethods
{
    static int j = 0;

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
        FileWriter namefileWriter = new FileWriter("files/Users/Names.txt", true);
        FileWriter locfileWriter = new FileWriter("files/Users/Locations.txt", true);
        FileWriter moneyfileWriter = new FileWriter("files/Users/Money.txt", true);

        BufferedWriter nbufferedWriter = new BufferedWriter(namefileWriter);
        BufferedWriter lbufferedWriter = new BufferedWriter(locfileWriter);
        BufferedWriter mbufferedWriter = new BufferedWriter(moneyfileWriter);

        nbufferedWriter.write(user.getName());
        nbufferedWriter.newLine();
        lbufferedWriter.write(user.getLocation().name);
        lbufferedWriter.newLine();
        mbufferedWriter.write(Double.toString(user.getMoney()));
        mbufferedWriter.newLine();

        nbufferedWriter.close();
        lbufferedWriter.close();
        mbufferedWriter.close();
        namefileWriter.close();
        locfileWriter.close();
        moneyfileWriter.close();
    }

    static void saveProgress(ArrayList<User> users, ArrayList<Location> locations) throws IOException
    {
        BufferedWriter nbufferedWriter = new BufferedWriter(new FileWriter("files/Users/Names.txt"));
        BufferedWriter lbufferedWriter = new BufferedWriter(new FileWriter("files/Users/Locations.txt"));
        BufferedWriter mbufferedWriter = new BufferedWriter(new FileWriter("files/Users/Money.txt"));

        nbufferedWriter.flush();
        lbufferedWriter.flush();
        mbufferedWriter.flush();

        for (User user: users)
        {
            nbufferedWriter.write(user.getName());
            nbufferedWriter.newLine();
            lbufferedWriter.write(user.getLocation().name);
            lbufferedWriter.newLine();
            mbufferedWriter.write(Double.toString(user.getMoney()));
            mbufferedWriter.newLine();
        }

        nbufferedWriter.close();
        lbufferedWriter.close();
        mbufferedWriter.close();

        BufferedWriter dronebufferedWriter = new BufferedWriter(new FileWriter("files/City/drones_at_Locations"));
        String droneName = null;
        String droneLine = null;

        for (Location location: locations)
        {
            for (Drone drone: location.getDrones())
            {
                if(drone instanceof CopDrone)
                    droneName = "cop";

                else if(drone instanceof FunnyDrone)
                    droneName = "funny";

                else if(drone instanceof TourGuideDrone)
                    droneName = "tour";

                else if(drone instanceof MessengerDrone)
                    droneName = "messenger";

                droneLine = droneName + ", ";
            }

            droneLine = droneLine.replaceAll(", $", ""); //removes trailing comma

            dronebufferedWriter.write(droneLine);
            dronebufferedWriter.newLine();
        }

        dronebufferedWriter.close();

    }

    static Double costCalc(ArrayList<Location> locations, Location start, Location end, Double rate)
    {
        Double cost;

        int endIndex = 0;

        for(Location location: locations)
        {
            if(location.equals(end))
            {
                endIndex = locations.indexOf(location);
                break;
            }
        }

        cost = rate * start.adjacentLocationDistance.get(endIndex);

        return cost;
    }

    static String randomID()
    {
        String id;

        ArrayList<Integer> numList = new ArrayList<>();

        for(int i=0; i<101; i++)
            numList.add(i);

        Collections.shuffle(numList);

        id = Integer.toString(numList.get(j++));

        return id;
    }


}
