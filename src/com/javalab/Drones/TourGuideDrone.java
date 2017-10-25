package com.javalab.Drones;

import com.javalab.City.Location;
import com.javalab.Drone;
import com.javalab.Main;
import com.javalab.Misc.ShortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import static com.javalab.UtilityMethods.checkInvalidInput;
import static com.javalab.UtilityMethods.findNearest;

public class TourGuideDrone extends Drone
{
    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    protected void interact() throws IOException
    {
        int cho;
        Scanner scanner = new Scanner(System.in);

        Double dist[] = new Double[Main.getCity().getLocations().size()];
        Location nearestLocation;

        System.out.println("Hello! My name is TourGuide Drone " + this.getId());

        do
        {
            try
            {
                System.out.println("What do you like out of the following?:");
                System.out.printf("1. Studying\n2. Shopping\n3. Eating\n4. Sports\n5. To previous menu\n");
                cho = Integer.parseInt(bufferedReader.readLine());
                checkInvalidInput(5, cho);
            } catch (Exception e)
            {
                System.out.println("Invalid Input!");
                cho = -1;
            }
        } while (cho<=0);

        if(cho != 5)
        {
            ShortestPath shortestPath = new ShortestPath();
            dist = shortestPath.dijkstra(Main.getCity().locationDistanceGraph, Main.getCity().locations.indexOf(Main.getCurrentUser().getLocation()));
        }

        switch (cho)
        {
            case 1:
                nearestLocation = findNearest(dist, "Study");
                System.out.println("The nearest study point is " + nearestLocation.name + "." );
                scanner.nextLine();
                break;

            case 2:
                nearestLocation = findNearest(dist, "Shopping");
                System.out.println("The nearest shopping point is " + nearestLocation.name + ".");
                scanner.nextLine();
                break;

            case 3:
                nearestLocation = findNearest(dist, "Food");
                System.out.println("The nearest food point is " + nearestLocation.name + ".");
                scanner.nextLine();
                break;

            case 4:
                nearestLocation = findNearest(dist, "Sports");
                System.out.println("The nearest Sports point is " + nearestLocation.name + ".");
                scanner.nextLine();
                break;

            default:break;
        }
    }

    @Override
    protected void contactCop()
    {
        //contact nearest copDrone
    }


}
