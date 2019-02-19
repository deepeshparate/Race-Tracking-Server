package message;

import org.junit.Test;
import raceData.Athlete;
import raceServer.TrackingServer;

import static org.junit.Assert.*;

public class FinishedUpdateMessageTest {

    @Test
    public void testExecute() throws Exception {
        TrackingServer trackingServer = new TrackingServer(12052);

        Athlete athlete1 = new Athlete(1, "Joseph", "Clark", "M", 27, 60);
        Athlete athlete2 = new Athlete(2,"Christina", "Ray", "F", 26, "OnCourse",5678.20, 15, 40, 80 );

        trackingServer.addAthlete(athlete1);
        trackingServer.addAthlete(athlete2);

        FinishedUpdateMessage finishedUpdateMessage=new FinishedUpdateMessage(1,90);
        finishedUpdateMessage.execute(trackingServer);
        assertEquals("Finished",trackingServer.getAthleteByBibNumber(1).getStatus());
        assertEquals(90,trackingServer.getAthleteByBibNumber(1).getFinish_Time());


    }

}