package message;

import raceData.Athlete;
import raceServer.TrackingServer;

public class AthleteStatusMessage extends Message{

    private Athlete athlete;


    public AthleteStatusMessage(Athlete athlete) {
        this.athlete = athlete;
    }

    @Override
    public void execute(TrackingServer trackingServer) {

    }

    @Override
    public String toString() {

        return "Status,"+athlete.getBibNumber()+","+athlete.getStatus()+","+athlete.getStart_Time()+","
                +athlete.getDistance_Covered()+","+athlete.getLast_Updated_Time()+","+athlete.getFinish_Time();
    }
}
