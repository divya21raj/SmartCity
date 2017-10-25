package com.javalab;

import com.javalab.City.City;
import com.javalab.City.Location;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Scanner;

import static com.javalab.Main.mainScreen;
import static com.javalab.UtilityMethods.*;

public class User implements Serializable
{
    private final String name;
    private transient Location location; //location was not serializable
    private Double money;

    private static final long serialVersionUID = 0; //to prevent Class mismatch error

    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    User(String name, Location location, Double money)
    {
        this.name = name;
        this.location = location;
        this.money = money;
    }

////////////////////////////////////GETTERS AND SETTERS///////////////////////////////////////////


    public Double getMoney()
    {
        return money;
    }

    public void setMoney(Double money)
    {
        this.money = money;
    }

    public String getName()
    {
        return name;
    }

    public Location getLocation()
    {
        return location;
    }

    public void setLocation(Location location)
    {
        this.location = location;
    }

    void takeCab(City city) throws IOException, ClassNotFoundException
    {
        int i, cho;

        Scanner scanner = new Scanner(System.in);

        do
        {
            i=1;
            System.out.println("Where to from your current location of " + location.name + "?:");

            printLocations(city);
            cho = Integer.parseInt(bufferedReader.readLine());

            if(cho != i)
            {
                Location newLocation = numSelectiontoLocation(cho, city.locations);

                Double travelCost = costCalc(city, city.locations, location, newLocation);

                System.out.println("That'll be " + travelCost + " Rs.");
                System.out.println("Press enter to take the ride...");
                scanner.nextLine();

                if(money < travelCost)
                {
                    System.out.println("You're not rich enough to do this....you can do one of the following:");
                    System.out.printf("1.Take this cab to the Mini Mart ATM\n2.Walk\n");

                    int chop = Integer.parseInt(bufferedReader.readLine());

                    if(chop == 1)
                    {
                        location = numSelectiontoLocation(city.locations.indexOf(nameToLocation("Mini Mart", city.locations)), city.locations);
                        money -= travelCost;
                        mainScreen();
                    }

                    else
                    {
                        walk();
                    }
                }

                else
                {
                    money -= travelCost;
                    location = newLocation;
                    mainScreen();
                    cho = i;
                }
            }

        }while (cho != i);

    }

    void walk() throws IOException, ClassNotFoundException
    {
        int cho, i=1;

        System.out.println("From your current location of " + location.name + " you can walk to the following places:");
        for (Location adjacentLocation: location.getAdjacentLocations())
            System.out.printf("%d.%s\n", i++, adjacentLocation.name);
        System.out.printf("%d.To previous menu\nWhere to(1-%d)\n", i, i);

        cho = Integer.parseInt(bufferedReader.readLine());

        if(cho!=i)
        {
            location = location.getAdjacentLocations().get(cho-1);
            mainScreen();
            //Do some health thingy here
        }

    }
}
