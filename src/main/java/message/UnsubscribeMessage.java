package message;

import raceData.Client;
import raceServer.TrackingServer;

public class UnsubscribeMessage extends Message {
    private int athleteBibNumber;
    private String clientAddress;
    private int clientPortNumber;


    public UnsubscribeMessage(int athleteBibNumber,String clientAddress, int clientPortNumber) {
        this.athleteBibNumber = athleteBibNumber;
        this.clientAddress = clientAddress;
        this.clientPortNumber = clientPortNumber;
    }
    @Override
    public void execute(TrackingServer trackingServer) {
        Client client=trackingServer.getClientByPortNumber(clientPortNumber);
        trackingServer.removeClientFromAthlete(athleteBibNumber,client);

    }
}
