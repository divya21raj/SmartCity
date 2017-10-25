package com.javalab;

import com.javalab.City.City;
import com.javalab.City.Location;
import com.javalab.Drones.CopDrone;
import com.javalab.Drones.FunnyDrone;
import com.javalab.Drones.Messenger.Message;
import com.javalab.Drones.Messenger.MessengerDrone;
import com.javalab.Drones.TourGuideDrone;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import static com.javalab.UtilityMethods.*;

@SuppressWarnings("ConstantConditions")
public class Main
{
    private static User currentUser;

    private static City city;

    private static ArrayList<User> users = new ArrayList<>();

    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    private static final String fileSeparator = File.separator;

    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        int cho;

        city = new City("SNU", 0.1d);

        initUsers();

        dronesInit();

        do
        {
            System.out.printf("\n1.Start\n2.Exit\n");
            cho = Integer.parseInt(bufferedReader.readLine());

            if(cho == 1)
            {
                userInit();

                mainScreen();

                saveProgress(users);
            }

        }while(cho == 1);
    }

    public static void mainScreen() throws IOException, ClassNotFoundException
    {
        int cho;
        do
        {
            clrscr();
            displayMessages();

            System.out.println("");
            System.out.println("You are at " + currentUser.getLocation().name + ".");
            System.out.println("This place has " + Integer.toString(currentUser.getLocation().getDrones().size()) + " knowledgeable drones flying around, try talking to them...");


            System.out.printf("\nWhat do you want to do?\n1.Talk to one of the drones\n2.Walk to some place else\n3.Call a cab\n4.Log out\n");

            cho = Integer.parseInt(bufferedReader.readLine());

            switch(cho)
            {
                case 1:
                    droneInteraction();
                    break;

                case 2:
                    currentUser.walk();
                    cho = 4;
                    break;

                case 3:
                    currentUser.takeCab(city);
                    cho = 4;
                    break;

                default:
                    cho = 4;
                    break;
            }

        }while (cho != 4);

    }

    private static void droneInteraction() throws IOException
    {
        int cho, i = 1;
        String droneName;

        do
        {
            System.out.println("What drone do you want to talk to?: ");
            for (Drone drone: currentUser.getLocation().getDrones())
            {
                droneName = droneType(drone);
                System.out.printf("%d. %s drone\n", i++, droneName);
            }
            System.out.printf("%d. To previous menu\n", i);

            cho = Integer.parseInt(bufferedReader.readLine());

            if(cho != i)
            {
                currentUser.getLocation().getDrones().get(cho-1).interact();
                cho = i;
            }

        }while(cho != i);
    }

    private static void displayMessages() throws IOException, ClassNotFoundException
    {
        ArrayList<Message> messages = null;
        Scanner scanner = new Scanner(System.in);

        loadAllMessengerDroneMessages(currentUser.getLocation());

        for(Drone drone: currentUser.getLocation().getDrones())
        {
            if(drone instanceof MessengerDrone)
            {
                messages = ((MessengerDrone) drone).getMessages();

                ArrayList<Integer> messageIndices = checkUserInMessages(currentUser.getName(), messages);

                if(!messageIndices.isEmpty())
                {
                    System.out.println("You have the following messages...");

                    for(int index: messageIndices)
                    {
                        Message temp = messages.get(index);

                        System.out.println("From : " + temp.getSender().getName());
                        System.out.printf("%s\n", "----");
                        for (String line: temp.getText())
                        {
                            if(line.equals("End"))
                            {
                                System.out.printf("%s\n", "----");
                                break;
                            }
                            else
                                System.out.println(line);
                        }

                        messages.get(index).setRead(true);
                        System.out.println("Press enter to continue");
                        scanner.nextLine();
                    }
                }
            }
        }

        saveMessages(messages);
    }

    private static void userInit() throws IOException
    {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Please enter name: ");
        String name = bufferedReader.readLine();

        int index = checkUsers(name, users);

        if(index == -1)
        {

            System.out.println("Choose starting location: ");
            printLocations(city);

            Location location = numSelectiontoLocation(Integer.parseInt(bufferedReader.readLine()), city.locations);

            currentUser = new User(name, location, 200000d);
            users.add(currentUser);

            writeUser(currentUser);
        }

        else
        {
            currentUser = users.get(index);
            System.out.println("Welcome Back!");
            System.out.println("Press enter to continue...");
            scanner.nextLine();
        }


    }

    private static void dronesInit() throws IOException, ClassNotFoundException
    {
        for(int i=0; i<city.locations.size(); i++)
        {
            CopDrone copDrone = new CopDrone();
            copDrone.setCurrentLocation(city.locations.get(i));
            TourGuideDrone tourGuideDrone = new TourGuideDrone();
            tourGuideDrone.setCurrentLocation(city.locations.get(i));
            FunnyDrone funnyDrone = new FunnyDrone();
            funnyDrone.setCurrentLocation(city.locations.get(i));
            MessengerDrone messengerDrone = new MessengerDrone();
            messengerDrone.setCurrentLocation((city.locations.get(i)));
            city.locations.get(i).getDrones().add(copDrone);
            city.locations.get(i).getDrones().add(tourGuideDrone);
            city.locations.get(i).getDrones().add(funnyDrone);
            city.locations.get(i).getDrones().add(messengerDrone);

        }

        if(!(new File(System.getProperty("user.home")+ fileSeparator +"SmartCity"+ fileSeparator +"drones_not_at_Locations.txt").exists()))
            Files.copy(new File("files"+ fileSeparator +"City"+ fileSeparator +"drones_not_at_Locations.txt").toPath(), new File(System.getProperty("user.home")+ fileSeparator +"SmartCity"+ fileSeparator +"drones_not_at_Locations.txt").toPath(), StandardCopyOption.REPLACE_EXISTING);

        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.home")+ fileSeparator +"SmartCity"+ fileSeparator +"drones_not_at_Locations.txt"));

        int j=0;
        while (true)
        {
            String droneLine = bufferedReader.readLine();

            if(droneLine == null)
                break;

            StringTokenizer stringTokenizer = new StringTokenizer(droneLine, ",");

            while(stringTokenizer.hasMoreTokens())
            {
                String drone = stringTokenizer.nextToken().trim();

                if(!drone.equals(""))
                removeDrone(drone, city.locations.get(j));
            }

            j++;
        }
    }

    private static void initUsers() throws IOException
    {
        setupSaving();

        BufferedReader nbufferedReader = new BufferedReader(new FileReader(System.getProperty("user.home") + fileSeparator +"SmartCity"+ fileSeparator +"Names.txt"));
        BufferedReader lbufferedReader = new BufferedReader(new FileReader(System.getProperty("user.home") + fileSeparator +"SmartCity"+ fileSeparator +"Locations.txt"));
        BufferedReader mbufferedReader = new BufferedReader(new FileReader(System.getProperty("user.home") + fileSeparator +"SmartCity"+ fileSeparator +"Money.txt"));

        while(true)
        {
            String name = nbufferedReader.readLine();
            String location = lbufferedReader.readLine();

            if(name == null)
                break;

            Double money = Double.parseDouble(mbufferedReader.readLine());

            User user = new User(name, findLocation(location, city.locations), money);

            users.add(user);

        }

        nbufferedReader.close();
        lbufferedReader.close();
        mbufferedReader.close();

    }

/////////////////////////////Getters and Setters////////////////////////////////////////////////

    public static ArrayList<User> getUsers()
    {
        return users;
    }

    public static User getCurrentUser()
    {
        return currentUser;
    }

    public static City getCity()
    {
        return city;
    }
}
