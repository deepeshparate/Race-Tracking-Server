package message;

import raceData.Athlete;
import raceData.Client;
import raceServer.TrackingServer;

import java.util.ArrayList;

public class HelloMessage  extends Message {
    private String clientAddress;
    private int clientPort;

    public HelloMessage() {
        clientAddress = null;
        clientPort = 0;
    }

    public HelloMessage(String clientAddress, int clientPort) {
        this.clientAddress = clientAddress;
        this.clientPort = clientPort;
    }

    @Override
    public void execute(TrackingServer trackingServer) {
        Client c = new Client(clientAddress, clientPort);
        trackingServer.addClient(c);
        if (raceStartedFlag == true) {
            Message message = Message.createObject(raceMessage);
            message.execute(trackingServer);
            ArrayList<Athlete> athleteList = trackingServer.getAthleteList();
            Client client=trackingServer.getClientByPortNumber(clientPort);
            for (Athlete a : athleteList) {
                Message message1=Message.createObject("Registered" + "," + String.valueOf(a.getBibNumber()) + "," + a.getStart_Time() + "," + a.getFirstName() + "," + a.getLastName() + "," + a.getGender() + "," + a.getAge());
                trackingServer.sendToLateClients(message1,client);

            }
        }
    }

}
