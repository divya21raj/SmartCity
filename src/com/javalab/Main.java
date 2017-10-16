package com.javalab;

import com.javalab.City.City;
import com.javalab.City.Location;
import static com.javalab.UtilityMethods.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class Main
{
    static User currentUser;

    static City city;

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException
    {
        titleMenu();
    }

    static void titleMenu() throws IOException
    {
        int cho;

        System.out.printf("\n1.Start\n2.Exit");
        cho = Integer.parseInt(bufferedReader.readLine());

        if(cho == 1)
        {
            city = new City("SNU");
            mainScreen();
        }

    }

    static void mainScreen() throws IOException
    {
        userInit();


    }

    static void userInit() throws IOException
    {
        int i = 0;
        currentUser = new User();

        System.out.println("Please enter name: ");
        currentUser.setName(bufferedReader.readLine());

        System.out.println("Choose starting location: ");
        for(Location location: city.locations)
        {
            i++;
            System.out.printf("%d. %s\n", i, location.name);
        }

        currentUser.setLocation(numSelectiontoLocation(Integer.parseInt(bufferedReader.readLine()), city.locations));

    }
}
