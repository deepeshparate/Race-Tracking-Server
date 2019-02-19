package message;

import org.junit.Test;
import raceData.Athlete;
import raceData.Client;
import raceServer.TrackingServer;

import java.net.InetAddress;

import static message.Message.raceStartedFlag;
import static org.junit.Assert.*;

public class HelloMessageTest {
    @Test

    public void testExecute()throws Exception
    {
        Client client1 = new Client(InetAddress.getLocalHost().getHostAddress(), 12055);
        TrackingServer trackingServer = new TrackingServer(12056);
        Athlete athlete1 = new Athlete(1,"Joseph", "Clark", "M", 27, "Registered",1234.56, 12, 60, 95);
        Athlete athlete2 = new Athlete(2,"Christina", "Ray", "F", 26, 15 );

        trackingServer.addClient(client1);

        String inetAddress = null;
        inetAddress = InetAddress.getLocalHost().getHostAddress();
        String message = "Race,Bension Loop,16090";
        int port = 12055;
        Message message1 = Message.createObject(message+","+InetAddress.getLocalHost().getHostAddress()+"," +String.valueOf(port));
        message1.execute(trackingServer);

        TrackingServer trackingServer1 = new TrackingServer(12035);
        trackingServer1.addAthlete(athlete1);
        trackingServer1.addAthlete(athlete2);

        HelloMessage helloMessage=new HelloMessage();

        HelloMessage helloMessage1= new HelloMessage(InetAddress.getLocalHost().getHostAddress(), 12036) ;
        HelloMessage helloMessage2= new HelloMessage(InetAddress.getLocalHost().getHostAddress(), 12037) ;


        helloMessage1.execute(trackingServer1);
        helloMessage2.execute(trackingServer1);


        assertEquals(12036,trackingServer1.getClientList().get(0).getPort());
        assertEquals(12037,trackingServer1.getClientList().get(1).getPort());

        raceStartedFlag=true;
        helloMessage2.execute(trackingServer1);
        raceStartedFlag=false;
    }


}