package com.javalab.Drones.Messenger;

import com.javalab.User;

import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable
{
    User sender, receiver;

    ArrayList<String> text;

    Boolean read;

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

    public void setSender(User sender)
    {
        this.sender = sender;
    }

    public User getReceiver()
    {
        return receiver;
    }

    public void setReceiver(User receiver)
    {
        this.receiver = receiver;
    }

    public ArrayList<String> getText()
    {
        return text;
    }

    public void setText(ArrayList<String> text)
    {
        this.text = text;
    }
}
