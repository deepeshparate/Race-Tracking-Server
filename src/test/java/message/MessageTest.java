package message;

import org.junit.Test;
import raceData.Athlete;
import raceData.Client;
import raceServer.TrackingServer;

import java.net.InetAddress;

import static org.junit.Assert.*;

public class MessageTest {

    @Test
    public void testExecute() throws Exception {
        TrackingServer trackingServer= new TrackingServer(20147);

        Message message1=Message.createObject("Hello,127.0.0.1,12047");
        assertTrue(message1.getClass()==message.HelloMessage.class);

        Message message2=Message.createObject("Subscribe,1,127.0.0.1,12047");
        assertTrue(message2.getClass()==message.SubscribeMessage.class);

        Message message3=Message.createObject("Unsubscribe,1,127.0.0.1,12047");
        assertTrue(message3.getClass()==message.UnsubscribeMessage.class);

        Message message4=Message.createObject("Race,Benison Loop,2000,127.0.0.1,12047");
        assertTrue(message4.getClass()==message.RaceStartedMessage.class);

        Message message5=Message.createObject("Registered,1,20,Cary,Jhon,F,23,127.0.0.1,12047");
        assertTrue(message5.getClass()==message.RegisteredMessage.class);

        Message message6=Message.createObject("DidNotStart,1,23,127.0.0.1,12047");
        assertTrue(message6.getClass()==message.DidNotStartMessage.class);

        Message message7=Message.createObject("Started,1,20,127.0.0.1,12047");
        assertTrue(message7.getClass()==message.StartedUpdateMessage.class);

        Message message8=Message.createObject("OnCourse,1,20,3423,127.0.0.1,12047");
        assertTrue(message8.getClass()==message.OnCourseUpdateMessage.class);

        Message message9=Message.createObject("DidNotFinish,1,20,127.0.0.1,12047");
        assertTrue(message9.getClass()==message.DidNotFinishMessage.class);

        Message message10=Message.createObject("Finished,1,90,127.0.0.1,12047");
        assertTrue(message10.getClass()==message.FinishedUpdateMessage.class);


    }


}