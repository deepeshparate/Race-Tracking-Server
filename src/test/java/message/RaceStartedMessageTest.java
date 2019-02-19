package message;

import org.junit.Test;
import raceData.Client;
import raceServer.TrackingServer;

import java.net.InetAddress;

import static org.junit.Assert.*;

public class RaceStartedMessageTest {

        @Test
        public void testExecute() throws Exception{
            Client client1 = new Client(InetAddress.getLocalHost().getHostAddress(), 12033);
            TrackingServer trackingServer = new TrackingServer(12034);
            trackingServer.addClient(client1);
            String inetAddress = null;
            inetAddress = InetAddress.getLocalHost().getHostAddress();
            String message = "Race,Bension Loop,16090";
            int port = 12033;
            Message message1 = Message.createObject(message+","+InetAddress.getLocalHost().getHostAddress()+"," +String.valueOf(port));
            message1.execute(trackingServer);

        }
    }

