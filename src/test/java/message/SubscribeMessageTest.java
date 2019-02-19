package message;

import org.junit.Test;
import raceData.Athlete;
import raceData.Client;
import raceServer.TrackingServer;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.junit.Assert.*;

public class SubscribeMessageTest {
    @Test
    public void testExecute() throws Exception {
        TrackingServer trackingServer = new TrackingServer(12040);

        Athlete athlete1 = new Athlete(1,"Joseph", "Clark", "M", 27, "Registered",1234.56, 12, 60, 95);
        Athlete athlete2 = new Athlete(2,"Christina", "Ray", "F", 26, "OnCourse",5678.20, 15, 40, 80 );

        Client client1 = new Client(InetAddress.getLocalHost().getHostAddress(), 12038);
        Client client2 = new Client(InetAddress.getLocalHost().getHostAddress(), 12039);

        trackingServer.addAthlete(athlete1);
        trackingServer.addAthlete(athlete2);

        trackingServer.addClient(client1);
        trackingServer.addClient(client2);

        SubscribeMessage subscribeMessage1=new SubscribeMessage(1,InetAddress.getLocalHost().getHostAddress(),12038);
        SubscribeMessage subscribeMessage2=new SubscribeMessage(1,InetAddress.getLocalHost().getHostAddress(),12039);

        subscribeMessage1.execute(trackingServer);
        subscribeMessage2.execute(trackingServer);

        assertEquals(12039,athlete1.getClients().get(1).getPort());



    }

}