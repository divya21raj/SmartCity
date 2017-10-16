package com.javalab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main
{
    User currentUser = new User();

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
            mainScreen();

    }

    static void mainScreen()
    {

    }
}
