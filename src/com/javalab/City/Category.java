package com.javalab.City;

import java.util.ArrayList;

public class Category
{
    public String name;
    public ArrayList<Location> locations;

    public Category(String name, ArrayList<Location> locations)
    {
        this.name = name;
        this.locations = locations;
    }
}
