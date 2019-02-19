package message;

import org.junit.Test;
import raceData.Athlete;
import raceServer.TrackingServer;

import static org.junit.Assert.*;

public class AthleteStatusMessageTest {

    @Test
    public void testConstruction() throws Exception {
        TrackingServer trackingServer = new TrackingServer(12049);

        Athlete athlete1 = new Athlete(1, "Joseph", "Clark", "M", 27, "Registered", 1234.56, 12, 60, 95);
        AthleteStatusMessage athleteStatusMessage=new AthleteStatusMessage(athlete1);
        athleteStatusMessage.execute(trackingServer);
        athleteStatusMessage.toString();
    }

}
