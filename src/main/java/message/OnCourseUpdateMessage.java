package message;

import raceData.Athlete;
import raceServer.TrackingServer;

public class OnCourseUpdateMessage extends Message {
    private int bibNo;
    private int time;
    private double distanceCovered;

    public OnCourseUpdateMessage(int bibNo,int time,double distanceCovered)
    {
        this.bibNo=bibNo;
        this.time=time;
        this.distanceCovered=distanceCovered;
    }

    public void execute(TrackingServer trackingServer) {
        Athlete athlete=trackingServer.getAthleteByBibNumber(bibNo);
        athlete.setStatus("OnCourse");
        athlete.setLast_Updated_Time(time);
        athlete.setDistance_Covered(distanceCovered);
        trackingServer.sendAthleteStatus(athlete);
    }

}