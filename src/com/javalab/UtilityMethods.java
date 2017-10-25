package com.javalab;

import com.javalab.City.Category;
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

    private static final String fileSeparator = File.separator;
    
    //For randomID
    private static int j = 0;
    private static ArrayList<Integer> numList = new ArrayList<>();
    ///

    static void clrscr()
    {
        for(int i = 0; i<70; i++)     //works as clrscr()
            System.out.println();
    }

    public static void printLocations(City city)
    {
        Category category = city.getLocations().get(0).category;
        int i=1;

        for(Location location: city.getLocations())
        {
            if(!location.category.equals(category))
            {
                System.out.printf("\n%d.%-19s\t", i++, location.name);
                category = location.category;
            }

            else
                System.out.printf("%d.%-19s\t", i++, location.name);
        }

        System.out.printf("\n%d.To previous menu\n", i);
        System.out.printf("Enter location no.(1 - %d):\n", city.locations.size());

    }

    public static void addCategory(String categoryName, ArrayList<Category> locationCategories, Location location)
    {
        int index = -1;

        for(Category category: locationCategories)
        {
            if(category.name.equals(categoryName))
            {
                index = locationCategories.indexOf(category);
                break;
            }
        }

        if(index == -1)
        {
            ArrayList<Location> locations = new ArrayList<>();
            locations.add(location);

            Category category = new Category(categoryName, locations);
            category.locations.get(locations.size()-1).category = category;

            locationCategories.add(category);
        }

        else
        {
            location.category = locationCategories.get(index);
            locationCategories.get(index).locations.add(location);
        }
    }
    static void removeDrone(String droneType, Location location)
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
        File nameFile = new File(System.getProperty("user.home") + fileSeparator + "SmartCity" + fileSeparator + "Names.txt");
        File locFile = new File(System.getProperty("user.home") + fileSeparator + "SmartCity" + fileSeparator + "Locations.txt");
        File moneyFile = new File(System.getProperty("user.home") + fileSeparator + "SmartCity" + fileSeparator + "Money.txt");
        File messageFile = new File(System.getProperty("user.home") + fileSeparator + "SmartCity" + fileSeparator + "messages");
        if(!nameFile.exists())
        {
            nameFile.getParentFile().mkdirs();
            nameFile.createNewFile();
            locFile.createNewFile();
            moneyFile.createNewFile();

            messageFile.getParentFile().mkdirs();
            messageFile.createNewFile();
        }
    }

    static Location nameToLocation(String locationName, ArrayList<Location> locations)
    {
        Location location = null;

        for (Location l: locations)
        {
            if (l.name.equals(locationName))
            {
                location = l;
                break;
            }
        }

        return location;
    }
    static void loadAllMessengerDroneMessages(Location location) throws IOException, ClassNotFoundException
    {
        for(Drone drone: location.getDrones())
        {
            if(drone instanceof MessengerDrone)
                ((MessengerDrone) drone).loadMessages();
        }
    }

    static ArrayList<Integer> checkUserInMessages(String name, ArrayList<Message> messages)
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
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(System.getProperty("user.home") + fileSeparator + "SmartCity" + fileSeparator + "messages"));

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

    public static int checkUsers(String userName, ArrayList<User> users)
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
        FileWriter nameFile = new FileWriter(System.getProperty("user.home") + fileSeparator + "SmartCity" + fileSeparator + "Names.txt", true);

        FileWriter locFile = new FileWriter(System.getProperty("user.home") + fileSeparator + "SmartCity" + fileSeparator + "Locations.txt", true);

        FileWriter moneyFile = new FileWriter(System.getProperty("user.home") + fileSeparator + "SmartCity" + fileSeparator + "Money.txt", true);

        BufferedWriter nbufferedWriter = new BufferedWriter(nameFile);
        BufferedWriter lbufferedWriter = new BufferedWriter(locFile);
        BufferedWriter mbufferedWriter = new BufferedWriter(moneyFile);


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

    static void saveProgress(ArrayList<User> users) throws IOException
    {
        ////////////////////////////////Users' names, money, locations///////////////////////////////////////////////////////
        File nameFile = new File(System.getProperty("user.home") + fileSeparator + "SmartCity" + fileSeparator + "Names.txt");
        nameFile.getParentFile().mkdirs();

        File locFile = new File(System.getProperty("user.home") + fileSeparator + "SmartCity" + fileSeparator + "Locations.txt");
        locFile.getParentFile().mkdirs();

        File moneyFile = new File(System.getProperty("user.home") + fileSeparator + "SmartCity" + fileSeparator + "Money.txt");
        moneyFile.getParentFile().mkdirs();

        BufferedWriter nbufferedWriter = new BufferedWriter(new FileWriter(nameFile));
        BufferedWriter lbufferedWriter = new BufferedWriter(new FileWriter(locFile));
        BufferedWriter mbufferedWriter = new BufferedWriter(new FileWriter(moneyFile));

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

    static Double costCalc(City city, ArrayList<Location> locations, Location start, Location end)
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

        ShortestPath shortestPath = new ShortestPath();
        Double[] dist = shortestPath.dijkstra(city.locationDistanceGraph, city.locations.indexOf(start));

        cost = Math.ceil(dist[endIndex]*city.cabRate);

        return cost;
    }

    public static Location findNearest(Double dist[], String categoryName)
    {
        Double distance = Double.MAX_VALUE;

        City city = Main.getCity();

        Location nearestLocation = null;

        for(int i=0; i<dist.length; i++)
        {
            if(city.getLocations().get(i).category.name.equals(categoryName) && dist[i] < distance)
            {
                nearestLocation = Main.getCity().getLocations().get(i);
                distance = dist[i];
            }
        }

        return nearestLocation;
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
