package com.javalab;

import com.javalab.City.Location;
import com.javalab.Drones.CopDrone;
import com.javalab.Drones.FunnyDrone;
import com.javalab.Drones.MessengerDrone;
import com.javalab.Drones.TourGuideDrone;

import java.io.*;
import java.nio.file.Files;
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

    static void setupSaving() throws IOException
    {
        File namefile = new File(System.getProperty("user.home") + "/SmartCity/Names.txt");
        File locfile = new File(System.getProperty("user.home") + "/SmartCity/Locations.txt");
        File moneyfile = new File(System.getProperty("user.home") + "/SmartCity/Money.txt");
        if(!namefile.exists())
        {
            namefile.getParentFile().mkdirs();
            namefile.createNewFile();
            locfile.createNewFile();
            moneyfile.createNewFile();
        }
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
        File namefile = new File(System.getProperty("user.home") + "/SmartCity/Names.txt");
        namefile.getParentFile().mkdirs();

        File locfile = new File(System.getProperty("user.home") + "/SmartCity/Locations.txt");
        locfile.getParentFile().mkdirs();

        File moneyfile = new File(System.getProperty("user.home") + "/SmartCity/Money.txt");
        moneyfile.getParentFile().mkdirs();

        BufferedWriter nbufferedWriter = new BufferedWriter(new FileWriter(namefile));
        BufferedWriter lbufferedWriter = new BufferedWriter(new FileWriter(locfile));
        BufferedWriter mbufferedWriter = new BufferedWriter(new FileWriter(moneyfile));


        nbufferedWriter.write(user.getName());
        nbufferedWriter.newLine();
        lbufferedWriter.write(user.getLocation().name);
        lbufferedWriter.newLine();
        mbufferedWriter.write(Double.toString(user.getMoney()));
        mbufferedWriter.newLine();

        nbufferedWriter.close();
        lbufferedWriter.close();
        mbufferedWriter.close();
    }

    static void saveProgress(ArrayList<User> users, ArrayList<Location> locations) throws IOException
    {
        File namefile = new File(System.getProperty("user.home") + "/SmartCity/Names.txt");
        namefile.getParentFile().mkdirs();

        File locfile = new File(System.getProperty("user.home") + "/SmartCity/Locations.txt");
        locfile.getParentFile().mkdirs();

        File moneyfile = new File(System.getProperty("user.home") + "/SmartCity/Money.txt");
        moneyfile.getParentFile().mkdirs();

        BufferedWriter nbufferedWriter = new BufferedWriter(new FileWriter(namefile));
        BufferedWriter lbufferedWriter = new BufferedWriter(new FileWriter(locfile));
        BufferedWriter mbufferedWriter = new BufferedWriter(new FileWriter(moneyfile));

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

        BufferedWriter dronebufferedWriter = new BufferedWriter(new FileWriter(System.getProperty("user.home")+"/SmartCity/drones_at_Locations.txt"));
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
