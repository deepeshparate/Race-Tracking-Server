package message;

import raceData.Athlete;
import raceServer.TrackingServer;

public class StartedUpdateMessage extends Message {
    private int bibNo;
    private int time;


    public StartedUpdateMessage(int bibNo, int time)
    {
        this.bibNo=bibNo;
        this.time=time;

    }
    @Override
    public void execute(TrackingServer trackingServer) {
        Athlete athlete=trackingServer.getAthleteByBibNumber(bibNo);
        System.out.println(athlete.getFirstName());
        athlete.setStatus("Started");
        athlete.setStart_Time(time);
        trackingServer.sendAthleteStatus(athlete);

    }
}
