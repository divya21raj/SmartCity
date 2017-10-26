package com.javalab.Drones;

import com.javalab.Drone;

public class CopDrone extends Drone
{
    @Override
    protected void interact()
    {
        System.out.println("Constant Vigilance, Citizen!");
    }

    @Override
    protected void contactCop()
    {

    }
}
