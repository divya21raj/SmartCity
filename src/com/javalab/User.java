package com.javalab;

import com.javalab.City.Location;

public class User
{
    private String name;
    private Location location;
    private Double money;

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
