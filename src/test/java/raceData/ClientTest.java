package raceData;

import message.Message;
import org.junit.Test;
import raceServer.Communicator;
import raceServer.TrackingServer;

import java.net.DatagramPacket;
import java.net.InetAddress;

import static java.nio.charset.StandardCharsets.UTF_16BE;
import static org.junit.Assert.*;

public class ClientTest {
    @Test
    public void validTestConstruction() throws Exception {

        Client client1 = new Client(InetAddress.getLocalHost().getHostAddress(), 12028);
        Client client2 = new Client(InetAddress.getLocalHost().getHostAddress(), 12029);

        assertEquals(InetAddress.getByName(client1.getAddress()).getHostAddress(),client1.getAddress());
        assertEquals(12028,client1.getPort());
        assertEquals(InetAddress.getByName(client2.getAddress()).getHostAddress(),client2.getAddress());
        assertEquals(12029,client2.getPort());
        assertTrue(client1.getPort()>0);
        assertTrue(client2.getPort()>0);


    }

    @Test
    public void testUpdate() throws Exception {

        TrackingServer trackingServer = new TrackingServer(12027);
        Communicator comm1 = trackingServer.getCommunicator();
        Communicator comm2 = new Communicator(12030);
        Client client1 = new Client(InetAddress.getLocalHost().getHostAddress(), 12030);




       // client1.update(trackingServer,message);
        DatagramPacket packet = comm2.getMessage();
//        assertNotNull(packet);

    //    String message1 = new String( packet.getData(), 0, packet.getLength(), UTF_16BE);

        try
        {
            client1.update(null,null);
            fail("This must throw an Exception");
        }
        catch (Exception e) {
            assertEquals(null,e.getMessage());
        }


    }


}