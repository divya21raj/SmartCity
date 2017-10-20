package com.javalab;

import com.javalab.City.Location;

import java.io.Serializable;

public class User implements Serializable
{
    private String name;
    private transient Location location; //location was not serializable
    private Double money;

    private static final long serialVersionUID = 0; //to prevent Class mismatch error

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

}
