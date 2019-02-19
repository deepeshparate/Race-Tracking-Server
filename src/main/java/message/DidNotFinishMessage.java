package message;

import raceData.Athlete;
import raceServer.TrackingServer;

public class DidNotFinishMessage extends Message {
    private int bibNo;
    private int time;

    public  DidNotFinishMessage(int bibNo, int Time)
    {
        this.bibNo=bibNo;
        this.time=time;

    }
    @Override
    public void execute(TrackingServer trackingServer) {
        Athlete athlete=trackingServer.getAthleteByBibNumber(bibNo);
        athlete.setStatus("did not finish");
        athlete.setLast_Updated_Time(time);
        trackingServer.sendAthleteStatus(athlete);


    }
}

