package com.javalab;

import com.javalab.City.Location;
import com.javalab.Drones.CopDrone;
import com.javalab.Drones.FunnyDrone;
import com.javalab.Drones.Messenger.Message;
import com.javalab.Drones.Messenger.MessengerDrone;
import com.javalab.Drones.TourGuideDrone;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class UtilityMethods
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
        File messagefile = new File(System.getProperty("user.home") + "/SmartCity/messages");
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

    public static ArrayList<Integer> checkUserInMessages(String name, ArrayList<Message> messages, ArrayList<User> users) throws IOException
    {
        ArrayList<Integer> indices = new ArrayList<>();

        for (Message message: messages)
        {
            String recName = message.getReceiver().getName();

            if(recName.equals(name) && message.getRead() == false)
            {
                int index = messages.indexOf(message);
                indices.add(index);
            }
        }

        return indices;
    }

    static public void saveMessages(ArrayList<Message> messages) throws IOException
    {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(System.getProperty("user.home") + "/SmartCity/messages"));

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
        FileWriter namefile = new FileWriter(System.getProperty("user.home") + "/SmartCity/Names.txt", true);

        FileWriter locfile = new FileWriter(System.getProperty("user.home") + "/SmartCity/Locations.txt", true);

        FileWriter moneyfile = new FileWriter(System.getProperty("user.home") + "/SmartCity/Money.txt", true);

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

        /////////////////////////////Drone Positions/////////////////////////////////////////

        BufferedWriter dronebufferedWriter = new BufferedWriter(new FileWriter(System.getProperty("user.home")+"/SmartCity/drones_at_Locations.txt"));
        String droneName;
        String droneLine = null;
        dronebufferedWriter.flush();

        for (Location location: locations)
        {
            for (Drone drone: location.getDrones())
            {
                droneName = droneType(drone);

                if(droneLine!= null)
                    droneLine += droneName + ", ";
                else if (droneLine == null)
                    droneLine = droneName + ", ";
            }

            droneLine = droneLine.substring(0, droneLine.lastIndexOf(",")); //removes trailing comma

            dronebufferedWriter.write(droneLine);
            dronebufferedWriter.newLine();
            droneLine = null;
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
