package raceServer;

import message.AthleteStatusMessage;
import message.MessageProcessor;
import message.Message;
import raceData.Athlete;
import raceData.Client;

import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Observable;

public class TrackingServer extends Observable implements Runnable {

    // Creating an ArrayList of raceData.Athlete
    // Creating an ArrayList of raceData.Client
    // creating object of communicator
    // creating object of MessageProcessor



    private ArrayList<Athlete> athleteList = new ArrayList<Athlete>();
    private ArrayList<Client> clientList = new ArrayList<>();
    private Communicator communicator=null;
    private MessageProcessor messageProcessor =null;

    /*Tracking Server Constructor
        @param
     */

    public TrackingServer(){}

    public TrackingServer(int localPort) throws SocketException, Exception
    {
        communicator = new Communicator(localPort);
        messageProcessor =new MessageProcessor("raceServer.TrackingServer",this);
        communicator.setProcessor(messageProcessor);
    }

    // Method to get Communicator Object

    public Communicator getCommunicator() {
        return communicator;
    }

    public ArrayList<Athlete> getAthleteList(){
        return athleteList;
    }

    public ArrayList<Client> getClientList(){
        return clientList;
    }

    public Client getClientByPortNumber(int portNo){
        for(Client c: clientList)
        {
            if(c.getPort()==portNo) {
                return c;
            }
        }
        return null;

    }

    //method to get Athlete object by its BibNumber

    public Athlete getAthleteByBibNumber(int bibNo)
    {
        for(Athlete a: athleteList)
        {
            if(a.getBibNumber()==bibNo) {
                return a;
            }
        }
        return null;
    }

    //method to add Athlete in athleteList

    public void addAthlete(Athlete a)
    {
        athleteList.add(a);
    }

    //method to add Athlete in clientList

    public void addClient(Client c)
    {
        clientList.add(c);
    }


    //method to add particular client in Athlete array list who subscribed that Athlete

    public void addClientsToAthlete(int bibNo, Client c){
        for(Athlete a: athleteList)
        {
            if(a.getBibNumber()==bibNo) {
                a.addClient(c);
                break;
            }
        }
    }

    //method to remove particular client in Athlete array list who unsubscribed that Athlete

    public void removeClientFromAthlete(int bibNo,Client c){
        for(Athlete a: athleteList)
        {
            if(a.getBibNumber()==bibNo) {
                a.removeClient(c);
                break;
            }
        }

    }

    //method to send message to the clients

    public void sendToClients(Message message, ArrayList<Client> clients)
    {
        for(Client c: clients)
        {
            c.update(this,message);

        }

    }

    //method to send message to all the clients present at the particular moment

    public void sendToAllClients(Message message)
    {
        // sendToClients(message, clientList);
        for (Client c : clientList) {
            c.update(this,message);
        }
    }

    public void sendToLateClients(Message message, Client c)
    {
        c.update(this,message);
    }


    //method to send updated status of athlete to client

    public void sendAthleteStatus(Athlete athlete)
    {
        ArrayList<Client> clients= athlete.getClients();
        Message message= new AthleteStatusMessage(athlete);
        sendToClients(message,clients);

    }
    /**
     * Run loop for runnable
     */
    public void run() {

        communicator.start();
    }

}

