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

    void takeCab(City city) throws IOException, ClassNotFoundException
    {
        int cho;

        Scanner scanner = new Scanner(System.in);

        do
        {
            do
            {
                try
                {
                    System.out.println("Where to from your current location of " + location.name + "?:");
                    printLocations(city);

                    cho = Integer.parseInt(bufferedReader.readLine());
                    checkInvalidInput(city.getLocations().size() + 1, cho);

                } catch (Exception e)
                {
                    System.out.println("Invalid Input");
                    cho = -1;
                }
            } while (cho<=0);

            if(cho != city.getLocations().size() + 1)
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
                        Main.mainScreen();
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
                    Main.mainScreen();
                    cho = city.getLocations().size();
                }
            }

        }while (cho != city.getLocations().size() + 1);

    }

    void walk() throws IOException, ClassNotFoundException
    {
        int cho, i=1;

        do
        {
            try
            {
                System.out.println("From your current location of " + location.name + " you can walk to the following places:");
                for (Location adjacentLocation: location.getAdjacentLocations())
                    System.out.printf("%d.%s\n", i++, adjacentLocation.name);
                System.out.printf("%d.To previous menu\nWhere to(1-%d)\n", i, i);

                cho = Integer.parseInt(bufferedReader.readLine());
                checkInvalidInput(i, cho);
            } catch (Exception e)
            {
                System.out.println("Invalid Input!");
                cho = -1;
                i=1;
            }
        } while (cho<=0);

        if(cho!=i)
        {
            location = location.getAdjacentLocations().get(cho-1);
            Main.mainScreen();
            //Do some health thingy here
        }
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
}
