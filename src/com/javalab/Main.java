package com.javalab;

import com.javalab.City.City;
import com.javalab.City.Location;
import com.javalab.Drones.CopDrone;
import com.javalab.Drones.FunnyDrone;
import com.javalab.Drones.TourGuideDrone;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import static com.javalab.UtilityMethods.*;

public class Main
{
    private static User currentUser;

    private static City city;

    private static ArrayList<User> users = new ArrayList<>();

    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException
    {
        int cho;

        do
        {
            System.out.printf("\n1.Start\n2.Exit\n");
            cho = Integer.parseInt(bufferedReader.readLine());

            if(cho == 1)
            {
                city = new City("SNU");

                initUsers();

                dronesInit();

                mainScreen();
            }

        }while(cho == 1);
    }

    private static void mainScreen() throws IOException
    {
        userInit();

        clrscr();



    }

    private static void dronesInit() throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("files/City/drones_at_Locations.txt"));
        int i=0;

        while (true)
        {
            String droneLine = bufferedReader.readLine();

            if(droneLine == null)
                break;

            StringTokenizer stringTokenizer = new StringTokenizer(droneLine, ",");

            while(stringTokenizer.hasMoreTokens())
            {
                String drone = stringTokenizer.nextToken().trim();

                switch (drone)
                {
                    case "cop":
                        CopDrone copDrone = new CopDrone();
                        copDrone.setCurrentLocation(city.locations.get(i));
                        city.locations.get(i).getDrones().add(copDrone);
                        break;

                    case "tour":
                        TourGuideDrone tourGuideDrone = new TourGuideDrone();
                        tourGuideDrone.setCurrentLocation(city.locations.get(i));
                        city.locations.get(i).getDrones().add(tourGuideDrone);
                        break;

                    case "funny":
                        FunnyDrone funnyDrone = new FunnyDrone();
                        funnyDrone.setCurrentLocation(city.locations.get(i));
                        city.locations.get(i).getDrones().add(funnyDrone);
                        break;
                }
            }

            i++;
        }
    }

    private static void userInit() throws IOException
    {
        int i = 0;
        Scanner scanner = new Scanner(System.in);

        currentUser = new User();

        System.out.println("Please enter name: ");
        currentUser.setName(bufferedReader.readLine());

        int index = checkUsers(currentUser.getName(), users);

        if(index == -1)
        {

            System.out.println("Choose starting location: ");
            for(Location location: city.locations)
            {
                i++;
                System.out.printf("%d. %s\n", i, location.name);
            }

            currentUser.setLocation(numSelectiontoLocation(Integer.parseInt(bufferedReader.readLine()), city.locations));

            users.add(currentUser);

            writeUser(currentUser);
        }

        else
        {
            currentUser = users.get(index);
            System.out.println("Welcome Back!");
            System.out.println("You're currently at " + currentUser.getLocation().name);
            scanner.nextLine();
        }


    }

    private static void initUsers() throws IOException
    {
        FileReader namefileReader = new FileReader("files/Users/Names.txt");
        FileReader locfileReader = new FileReader("files/Users/Locations.txt");
        BufferedReader nbufferedReader = new BufferedReader(namefileReader);
        BufferedReader lbufferedReader = new BufferedReader(locfileReader);

        while(true)
        {
            String name = nbufferedReader.readLine();
            String location = lbufferedReader.readLine();

            if(name == null)
                break;

            User user = new User();

            user.setName(name);
            user.setLocation(findLocation(location, city.locations));

            users.add(user);

        }

        namefileReader.close();
        nbufferedReader.close();
        locfileReader.close();
        lbufferedReader.close();

    }
}
