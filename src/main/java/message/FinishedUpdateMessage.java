package message;

import raceData.Athlete;
import raceServer.TrackingServer;

public class FinishedUpdateMessage extends Message {
    private int bibNo;
    private int time;

    public FinishedUpdateMessage(int bibNo, int time)
    {
        this.bibNo=bibNo;
        this.time=time;

    }
    @Override
    public void execute(TrackingServer trackingServer) {
        Athlete athlete=trackingServer.getAthleteByBibNumber(bibNo);
        athlete.setStatus("Finished");
        athlete.setFinish_Time(time);
        trackingServer.sendAthleteStatus(athlete);

    }
}

