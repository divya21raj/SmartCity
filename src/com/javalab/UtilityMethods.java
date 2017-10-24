package com.javalab;

import com.javalab.City.City;
import com.javalab.City.Location;
import com.javalab.Drones.CopDrone;
import com.javalab.Drones.FunnyDrone;
import com.javalab.Drones.Messenger.Message;
import com.javalab.Drones.Messenger.MessengerDrone;
import com.javalab.Drones.TourGuideDrone;
import com.javalab.Misc.ShortestPath;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class UtilityMethods
{

    static String fileSeperator = File.separator;
    
    //For randomID
    private static int j = 0;
    static ArrayList<Integer> numList = new ArrayList<>();
    ///

    static void clrscr()
    {
        for(int i = 0; i<70; i++)     //works as clrscr()
            System.out.println();
    }

    static void removeDrone(String droneType, Location location) throws IOException, ClassNotFoundException
    {
        Iterator<Drone> iterator;

        switch (droneType)
        {
            case "Cop":
                iterator= location.getDrones().iterator();
                while (iterator.hasNext())
                {
                    Drone drone = iterator.next();
                    if(drone instanceof CopDrone)
                        iterator.remove();
                }
                break;

            case "Messenger":
                iterator= location.getDrones().iterator();
                while (iterator.hasNext())
                {
                    Drone drone = iterator.next();
                    if(drone instanceof MessengerDrone)
                        iterator.remove();
                }
                break;

            case "Funny":
                iterator= location.getDrones().iterator();
                while (iterator.hasNext())
                {
                    Drone drone = iterator.next();
                    if(drone instanceof FunnyDrone)
                        iterator.remove();
                }
                break;

            case "TourGuideDrone":
                iterator= location.getDrones().iterator();
                while (iterator.hasNext())
                {
                    Drone drone = iterator.next();
                    if(drone instanceof TourGuideDrone)
                        iterator.remove();
                }
                break;
        }

    }

    static void setupSaving() throws IOException
    {
        File namefile = new File(System.getProperty("user.home") + fileSeperator + "SmartCity" + fileSeperator + "Names.txt");
        File locfile = new File(System.getProperty("user.home") + fileSeperator + "SmartCity" + fileSeperator + "Locations.txt");
        File moneyfile = new File(System.getProperty("user.home") + fileSeperator + "SmartCity" + fileSeperator + "Money.txt");
        File messagefile = new File(System.getProperty("user.home") + fileSeperator + "SmartCity" + fileSeperator + "messages");
        if(!namefile.exists())
        {
            namefile.getParentFile().mkdirs();
            namefile.createNewFile();
            locfile.createNewFile();
            moneyfile.createNewFile();

            messagefile.getParentFile().mkdirs();
            messagefile.createNewFile();
        }
    }

    static void loadAllMessengerDroneMessages(Location location) throws IOException, ClassNotFoundException
    {
        for(Drone drone: location.getDrones())
        {
            if(drone instanceof MessengerDrone)
                ((MessengerDrone) drone).loadMessages();
        }
    }

    static ArrayList<Integer> checkUserInMessages(String name, ArrayList<Message> messages, ArrayList<User> users) throws IOException
    {
        ArrayList<Integer> indices = new ArrayList<>();

        if(messages != null)
        {
            for (Message message: messages)
            {
                String recName = message.getReceiver().getName();

                if(recName.equals(name) && !message.getRead())
                {
                    int index = messages.indexOf(message);
                    indices.add(index);
                }
            }
        }

        return indices;
    }

    static public void saveMessages(ArrayList<Message> messages) throws IOException
    {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(System.getProperty("user.home") + fileSeperator + "SmartCity" + fileSeperator + "messages"));

        objectOutputStream.writeObject(messages);

        objectOutputStream.close();
    }


    static Location numSelectiontoLocation(int num, ArrayList<Location> locations)
    {
        Location location = null;

        if(num <= locations.size())
            location = locations.get(num-1);

        return location;
    }

    public static int checkUsers(String userName, ArrayList<User> users) throws IOException
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
        FileWriter namefile = new FileWriter(System.getProperty("user.home") + fileSeperator + "SmartCity" + fileSeperator + "Names.txt", true);

        FileWriter locfile = new FileWriter(System.getProperty("user.home") + fileSeperator + "SmartCity" + fileSeperator + "Locations.txt", true);

        FileWriter moneyfile = new FileWriter(System.getProperty("user.home") + fileSeperator + "SmartCity" + fileSeperator + "Money.txt", true);

        BufferedWriter nbufferedWriter = new BufferedWriter(namefile);
        BufferedWriter lbufferedWriter = new BufferedWriter(locfile);
        BufferedWriter mbufferedWriter = new BufferedWriter(moneyfile);


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
        ////////////////////////////////Users' names, money, locations///////////////////////////////////////////////////////
        File namefile = new File(System.getProperty("user.home") + fileSeperator + "SmartCity" + fileSeperator + "Names.txt");
        namefile.getParentFile().mkdirs();

        File locfile = new File(System.getProperty("user.home") + fileSeperator + "SmartCity" + fileSeperator + "Locations.txt");
        locfile.getParentFile().mkdirs();

        File moneyfile = new File(System.getProperty("user.home") + fileSeperator + "SmartCity" + fileSeperator + "Money.txt");
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

        //////////////////\\//////////Drone Positions/////////////////////////////////////////



    }

    static Double costCalc(City city, ArrayList<Location> locations, Location start, Location end, Double rate)
    {
        Double cost = null;

        int endIndex = 0;

        for(Location location: locations)
        {
            if(location.equals(end))
            {
                endIndex = locations.indexOf(location);
                break;
            }
        }

        ShortestPath shortestPath = new ShortestPath();
        Double[] dist = shortestPath.dijkstra(city.locationDistanceGraph, city.locations.indexOf(start));

        cost = Math.ceil(dist[endIndex]*city.cabRate);

        return cost;
    }

    static String randomID()
    {
        String id;

        if(numList.isEmpty())
        {
            for(int i=0; i<200; i++)
                numList.add(i);

            Collections.shuffle(numList);
        }

        id = Integer.toString(numList.get(j++));

        return id;
    }

    static String droneType(Drone drone)
    {
        String droneName = null;

        if(drone instanceof CopDrone)
            droneName = "Cop";

        else if(drone instanceof FunnyDrone)
            droneName = "Funny";

        else if(drone instanceof TourGuideDrone)
            droneName = "TourGuide";

        else if(drone instanceof MessengerDrone)
            droneName = "Messenger";

        return droneName;
    }

}
