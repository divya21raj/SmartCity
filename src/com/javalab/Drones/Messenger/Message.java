package com.javalab.Drones.Messenger;

import com.javalab.User;

import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable
{
    private User sender, receiver;

    private ArrayList<String> text;

    private Boolean read;

    Message(User sender, User receiver, ArrayList<String> text)
    {
        this.sender = sender;
        this.receiver = receiver;
        this.text = text;
        this.read = false;
    }
    /////////////////////Getters and Setters/////////////////////////////


    public Boolean getRead()
    {
        return read;
    }

    public void setRead(Boolean read)
    {
        this.read = read;
    }

    public User getSender()
    {
        return sender;
    }

// --Commented out by Inspection START (25/10/2017 20:13):
//    public void setSender(User sender)
//    {
//        this.sender = sender;
//    }
// --Commented out by Inspection STOP (25/10/2017 20:13)

    public User getReceiver()
    {
        return receiver;
    }

// --Commented out by Inspection START (25/10/2017 20:13):
//    public void setReceiver(User receiver)
//    {
//        this.receiver = receiver;
//    }
// --Commented out by Inspection STOP (25/10/2017 20:13)

    public ArrayList<String> getText()
    {
        return text;
    }

// --Commented out by Inspection START (25/10/2017 20:13):
//    public void setText(ArrayList<String> text)
//    {
//        this.text = text;
//    }
// --Commented out by Inspection STOP (25/10/2017 20:13)
}
