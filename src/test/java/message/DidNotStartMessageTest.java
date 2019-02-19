package message;

import org.junit.Test;
import raceData.Athlete;
import raceServer.TrackingServer;

import static org.junit.Assert.*;

public class DidNotStartMessageTest {
    @Test
    public void testExecute() throws Exception {
        TrackingServer trackingServer = new TrackingServer(12051);

        Athlete athlete1 = new Athlete(1, "Joseph", "Clark", "M", 27, "Registered", 1234.56, 12, 60, 95);
        Athlete athlete2 = new Athlete(2,"Christina", "Ray", "F", 26, "OnCourse",5678.20, 15, 40, 80 );

        trackingServer.addAthlete(athlete1);
        trackingServer.addAthlete(athlete2);

        DidNotStartMessage didNotStartMessage=new DidNotStartMessage(1,12);
        didNotStartMessage.execute(trackingServer);
        assertEquals("did not start",trackingServer.getAthleteByBibNumber(1).getStatus());


    }

}