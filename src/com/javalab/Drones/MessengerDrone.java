package com.javalab.Drones;

import com.javalab.Drone;
import com.javalab.User;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicIntegerArray;

import static com.javalab.Main.getCurrentUser;
import static com.javalab.Main.getUsers;
import static com.javalab.UtilityMethods.checkUsers;

public class MessengerDrone extends Drone
{
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    ArrayList<String>[][] messages;   //horizontal - sender, vertical - receiver

    @Override
    public void interact() throws IOException
    {
        int cho;

        ArrayList<User> users = getUsers();
        User currentUser = getCurrentUser();

        System.out.println("Hey! My name is Messenger bot " + this.getId());
        do
        {
            System.out.println("What do you want to do?:");
            System.out.printf("1.Send a message to someone...\n2.To previous menu\n");
            cho = Integer.parseInt(bufferedReader.readLine());

            if(cho == 1)
            {
                sendMessage(currentUser, users);
            }
        }while(cho != 2);
    }

    private void sendMessage(User currentUser, ArrayList<User> users) throws IOException
    {
        System.out.println("Enter the name of the person you want to send this message to: ");
        String name = bufferedReader.readLine();

        int recIndex = checkUsers(name, users);

        if(recIndex != -1)
        {
            int sendIndex = checkUsers(currentUser.getName(), users);
            ArrayList<String> message = new ArrayList<>();

            System.out.printf("Enter your message...enter 'End'(without quotes) as a separate line to finish...\n");

            while(!(message.contains("End")))
                message.add(bufferedReader.readLine());

            messages[recIndex][sendIndex] = message;

            System.out.println("Message sent successfully!");

            saveMessages();

        }

        else
            System.out.println("Sorry, that user doesn't exist...");
    }

    public void saveMessages() throws IOException
    {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(System.getProperty("user.home") + "/SmartCity/messages"));

        objectOutputStream.writeObject(this.messages);

        objectOutputStream.close();
    }

    public void loadMessages() throws IOException, ClassNotFoundException
    {
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.home") + "/SmartCity/messages");
        try
        {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            if(objectInputStream.readObject() != null)
                this.messages = (ArrayList<String>[][]) objectInputStream.readObject();

            objectInputStream.close();
        } catch (EOFException e)
        {
            System.out.println("EOF");
        }

    }

    public MessengerDrone(ArrayList<User> users) throws IOException, ClassNotFoundException
    {
        messages = new ArrayList[users.size() + 3][users.size() + 3];

        for (int i =0; i< messages.length; i++)
        {
            for (int j=0; j<messages[i].length; j++)
                messages[i][j] = new ArrayList<>();
        }

        loadMessages();
    }

    @Override
    protected void contactCop()
    {

    }

    //////////////////////Getters and setters///////////////////////////////

    public void setMessages(ArrayList<String>[][] messages)
    {
        this.messages = messages;
    }


    public ArrayList<String>[][] getMessages()
    {
        return messages;
    }
}
