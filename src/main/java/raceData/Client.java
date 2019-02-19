package raceData;

import message.Message;
import raceServer.Communicator;
import raceServer.TrackingServer;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import static java.net.InetAddress.getByName;

public class Client implements Observer {

    /*Inet address of client
    port number of client*/

    private String address;
    private int port;

    /*Client Constructor
      @param address: client InetAddress
             port: client port number
     */

    public Client(String address, int port) {
        this.address = address;
        this.port = port;
    }

    //@return  get the Inet address which client is using

    public String getAddress() {
        return address;
    }

    /*@return  set the Inet address which client is using
      @param address: Inet address of client
     */


    //@return  get the port number which client is using

    public int getPort() {
        return port;
    }

    /*@return  get the port number which client is using
     * @param port: port number of client
     */



    /*
    method is called whenever the observed object is changed
    @param o: the observable object.
           arg : an argument passed to send the message to all observers
     */
    @Override

    public void update(Observable o, Object arg){
        TrackingServer trackingServer = (TrackingServer) o;
        Communicator communicator = trackingServer.getCommunicator();

        Message msg = (Message) arg;
        try {
            communicator.send(msg.toString(), getByName(address), port);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
