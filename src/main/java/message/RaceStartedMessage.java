package message;

import raceServer.TrackingServer;

public class RaceStartedMessage extends Message {
    private String raceName;
    private int courseLength;

    public RaceStartedMessage(String raceName, int courseLength)
    {
        this.courseLength=courseLength;
        this.raceName=raceName;

    }
    @Override
    public void execute(TrackingServer trackingServer) {


        trackingServer.sendToAllClients(this);
    }

    @Override
    public String toString() {

        return "Race,"+raceName+","+String.valueOf(courseLength);
    }
}

