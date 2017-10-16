package com.javalab;

import com.javalab.City.City;
import com.javalab.City.Location;
import static com.javalab.UtilityMethods.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    static User currentUser;

    static City city;

    static ArrayList<User> users = new ArrayList<>();

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException
    {
        titleMenu();
    }

    static void initUsers() throws IOException
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

    static void titleMenu() throws IOException
    {
        int cho;

        System.out.printf("\n1.Start\n2.Exit");
        cho = Integer.parseInt(bufferedReader.readLine());

        if(cho == 1)
        {
            city = new City("SNU");

            initUsers();

            mainScreen();
        }

    }

    static void mainScreen() throws IOException
    {
        userInit();

        clrscr();

    }

    static void userInit() throws IOException
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
}
