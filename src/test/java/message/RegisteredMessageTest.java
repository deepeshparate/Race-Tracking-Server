package message;

import org.junit.Test;
import raceData.Athlete;
import raceData.Client;
import raceServer.TrackingServer;

import java.net.InetAddress;

import static org.junit.Assert.*;

public class RegisteredMessageTest {

    @Test
    public void testExecute() throws Exception {
        TrackingServer trackingServer = new TrackingServer(12044);

        Athlete athlete1 = new Athlete(1,"Joseph", "Clark", "M", 27, "Registered",1234.56, 12, 60, 95);
        Athlete athlete2 = new Athlete(2,"Christina", "Ray", "F", 26, "OnCourse",5678.20, 15, 40, 80 );

        Client client1 = new Client(InetAddress.getLocalHost().getHostAddress(), 12045);
        Client client2 = new Client(InetAddress.getLocalHost().getHostAddress(), 12046);

        trackingServer.addClient(client1);
        trackingServer.addClient(client2);


        RegisteredMessage registeredMessage=new RegisteredMessage(1,12,"Joseph", "Clark", "M", 27);

        registeredMessage.execute(trackingServer);

        assertEquals("Joseph",trackingServer.getAthleteByBibNumber(1).getFirstName());


    }


}