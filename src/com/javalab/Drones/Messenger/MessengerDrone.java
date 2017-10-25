package com.javalab.Drones.Messenger;

import com.javalab.Drone;
import com.javalab.User;

import java.io.*;
import java.util.ArrayList;

import static com.javalab.Main.getCurrentUser;
import static com.javalab.Main.getUsers;
import static com.javalab.UtilityMethods.checkInvalidInput;
import static com.javalab.UtilityMethods.checkUsers;
import static com.javalab.UtilityMethods.saveMessages;


public class MessengerDrone extends Drone
{
    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    private ArrayList<Message> messages;

    @Override
    public void interact() throws IOException
    {
        int cho;

        ArrayList<User> users = getUsers();
        User currentUser = getCurrentUser();

        System.out.println("Hey! My name is Messenger bot " + this.getId());
        do
        {
            do
            {
                try
                {
                    System.out.println("What do you want to do?:");
                    System.out.printf("1.Send a message to someone...\n2.To previous menu\n");
                    cho = Integer.parseInt(bufferedReader.readLine());
                    checkInvalidInput(2, cho);
                } catch (Exception e)
                {
                    System.out.println("Invalid Input");
                    cho = -1;
                }
            } while (cho<=0);

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

        int userIndex = checkUsers(name, users);

        if(userIndex != -1)
        {
            ArrayList<String> text = new ArrayList<>();

            System.out.printf("Enter your message...enter 'End'(without quotes) as a separate line to finish...\n");

            while(!(text.contains("End")))
            {
                text.add(bufferedReader.readLine());
            }

            messages.add(new Message(currentUser, users.get(userIndex), text));

            System.out.println("Message sent successfully!");

            saveMessages(messages);
        }

        else
        {
            System.out.println("Sorry, that user doesn't exist...");
        }
    }

    public void loadMessages() throws IOException, ClassNotFoundException
    {
        try
        {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(System.getProperty("user.home") + File.separator + "SmartCity" + File.separator + "messages"));

            ArrayList<Message> temp = (ArrayList<Message>) objectInputStream.readObject();

            if(temp!=null)
                messages = temp;

            objectInputStream.close();
        }catch (EOFException e)
        {}
    }

    public MessengerDrone() throws IOException, ClassNotFoundException
    {
        messages = new ArrayList<>();

        loadMessages();
    }

    @Override
    protected void contactCop()
    {

    }

    //////////////////////Getters and setters///////////////////////////////


    public ArrayList<Message> getMessages()
    {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages)
    {
        this.messages = messages;
    }
}


