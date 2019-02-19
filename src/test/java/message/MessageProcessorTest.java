package message;
import org.junit.Test;
import raceServer.Communicator;
import raceServer.TrackingServer;

import java.net.InetAddress;

import static org.junit.Assert.*;

public class MessageProcessorTest {

    @Test
    public void testConstruction() throws Exception{
        TrackingServer trackingServer = new TrackingServer(12000);
        Communicator communicator = trackingServer.getCommunicator();
        String name = "A";
        MessageProcessor messageProcessor = new MessageProcessor(name,trackingServer);

        communicator.setProcessor(messageProcessor);
        assertSame(messageProcessor, communicator.getProcessor());

        assertTrue(communicator.getLocalPort()>0);

        communicator.close();
        assertEquals(0, communicator.getLocalPort());
    }

    @Test
    public void testProcess() throws Exception{
        String message1 = "Registered,1,0,Valentine,Zamora,M,30";
        String message2 = "Registered,2,0,Nimbu,Bilal,M,30";
        String message3 = "Registered,3,0,Pranay,Summons,F,30";
        String message4 = "Registered,4,0,Aparna,Setwet,F,30";
        TrackingServer trackingServer = new TrackingServer(12000);
        Communicator communicator = trackingServer.getCommunicator();
        MessageProcessor messageProcessor = new MessageProcessor("A",trackingServer);

        communicator.setProcessor(messageProcessor);
        assertSame(messageProcessor, communicator.getProcessor());

        assertTrue(communicator.getLocalPort()>0);

        messageProcessor.process(message1, InetAddress.getLocalHost(), communicator.getLocalPort());
        messageProcessor.process(message2, InetAddress.getLocalHost(), communicator.getLocalPort());
        messageProcessor.process(message3, InetAddress.getLocalHost(), communicator.getLocalPort());
        messageProcessor.process(message4, InetAddress.getLocalHost(), communicator.getLocalPort());

        int receiveCount = messageProcessor.ReceiveCount();

        assertTrue(messageProcessor.ReceiveCount()>0);

        assertEquals(4, messageProcessor.ReceiveCount());

        messageProcessor.process(null, InetAddress.getLocalHost(), communicator.getLocalPort());
        messageProcessor.process(message2, null, communicator.getLocalPort());

    }
}