package com.javalab.Drones;

import com.javalab.Drone;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class FunnyDrone extends Drone
{
    private Scanner scanner = new Scanner(System.in);

    private static ArrayList<ArrayList<String>> jokes = new ArrayList<>();

    @Override
    protected void interact() throws IOException
    {
        System.out.println("Hey! My name is Funny Drone " + this.getId());

        printRandomJoke();
    }

    private void printRandomJoke()
    {
        Random random = new Random();

        int jokeIndex = random.nextInt(jokes.size());

        System.out.println("Here's a rather funny joke...");
        System.out.println("Press enter to see it...");
        scanner.nextLine();

        for (String line: jokes.get(jokeIndex))
            System.out.println(line);

        System.out.printf("\n:);):);):);):);):);):);)See ya!\n");
        System.out.println("Press enter to continue");
        scanner.nextLine();
    }

    public FunnyDrone() throws IOException
    {
        if(jokes.isEmpty())
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("files/Jokes/jokes.txt"));

            String line;
            ArrayList<String> joke = new ArrayList<>();

            while(true)
            {
                line = bufferedReader.readLine();

                if(line == null)
                    break;

                if(line.equals(""))
                {
                    jokes.add(joke);
                    joke = new ArrayList<>();
                }
                else
                    joke.add(line);

            }
        }
    }

    @Override
    protected void contactCop()
    {}
}
