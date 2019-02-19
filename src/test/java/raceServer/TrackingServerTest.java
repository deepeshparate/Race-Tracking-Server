package raceServer;

import message.MessageProcessor;
import message.Message;
import org.junit.Test;
import raceData.Athlete;
import raceData.Client;
import java.net.InetAddress;
import java.util.ArrayList;


import static org.junit.Assert.*;

public class TrackingServerTest {

    @Test
    public void ValidTestConstruction() throws Exception{

        TrackingServer trackingServer = new TrackingServer(12001);
        TrackingServer trackingServer1 = new TrackingServer();

        Communicator communicator = trackingServer.getCommunicator();
        assertEquals(12001,communicator.getLocalPort());
        assertTrue(communicator.getLocalPort()>0);


        MessageProcessor messageProcessor =null;
        messageProcessor =new MessageProcessor("TrackingServer",trackingServer);
        communicator.setProcessor(messageProcessor);
        assertSame(messageProcessor,communicator.getProcessor());

        communicator.close();
        assertEquals(0,communicator.getLocalPort());

    }

    @Test
    public void invalidTestConstruction() throws Exception{

    }

    @Test
    public void testAddAthlete() throws Exception {
        TrackingServer trackingServer = new TrackingServer(12002);
        Athlete athlete1 = new Athlete(1,"Joseph", "Clark", "M", 27, "Registered",1234.56, 12, 60, 95);
        Athlete athlete2 = new Athlete(2,"Christina", "Ray", "F", 26, "OnCourse",5678.20, 15, 40, 80 );
        trackingServer.addAthlete(athlete1);
        trackingServer.addAthlete(athlete2);

        assertEquals("Joseph",trackingServer.getAthleteList().get(0).getFirstName());
        assertEquals("Christina",trackingServer.getAthleteList().get(1).getFirstName());

        assertEquals("Clark",trackingServer.getAthleteList().get(0).getLastName());
        assertEquals("Ray",trackingServer.getAthleteList().get(1).getLastName());

        assertEquals(1,trackingServer.getAthleteList().get(0).getBibNumber());
        assertEquals(2,trackingServer.getAthleteList().get(1).getBibNumber());

        assertEquals(12,trackingServer.getAthleteList().get(0).getStart_Time());
        assertEquals(15,trackingServer.getAthleteList().get(1).getStart_Time());

        assertEquals("Registered",trackingServer.getAthleteByBibNumber(1).getStatus());
        assertEquals("OnCourse",trackingServer.getAthleteByBibNumber(2).getStatus());

        assertEquals(1234.56,trackingServer.getAthleteList().get(0).getDistance_Covered(),0.0);
        assertEquals(5678.20,trackingServer.getAthleteList().get(1).getDistance_Covered(),0.0);

        assertEquals(1,trackingServer.getAthleteByBibNumber(1).getBibNumber());
        assertEquals(12,trackingServer.getAthleteByBibNumber(1).getStart_Time());
        assertEquals(1234.56,trackingServer.getAthleteByBibNumber(1).getDistance_Covered(),0.0);

        assertEquals(2,trackingServer.getAthleteByBibNumber(2).getBibNumber());
        assertEquals(15,trackingServer.getAthleteByBibNumber(2).getStart_Time());
        assertEquals(5678.20,trackingServer.getAthleteByBibNumber(2).getDistance_Covered(),0.0);

        trackingServer.getCommunicator().close();
    }

    @Test
    public void testAddClients() throws Exception{
        TrackingServer trackingServer = new TrackingServer(12072);

        Client client1 = new Client(InetAddress.getLocalHost().getHostAddress(), 12004);
        Client client2 = new Client(InetAddress.getLocalHost().getHostAddress(), 12005);

        trackingServer.addClient(client1);
        trackingServer.addClient(client2);

        assertEquals(12004,trackingServer.getClientList().get(0).getPort());
        assertEquals(12005,trackingServer.getClientList().get(1).getPort());

        assertEquals(InetAddress.getByName(client1.getAddress()).getHostAddress(),trackingServer.getClientList().get(0).getAddress());
        assertEquals(InetAddress.getByName(client2.getAddress()).getHostAddress(),trackingServer.getClientList().get(1).getAddress());

        assertEquals(InetAddress.getByName(client1.getAddress()).getHostAddress(),trackingServer.getClientByPortNumber(12004).getAddress());
        assertEquals(InetAddress.getByName(client2.getAddress()).getHostAddress(),trackingServer.getClientByPortNumber(12005).getAddress());
        assertEquals(null,trackingServer.getClientByPortNumber(05));
        assertEquals(null,trackingServer.getAthleteByBibNumber(05));

        trackingServer.getCommunicator().close();
    }

    @Test
    public void testAddClientToAthlete() throws Exception {
        TrackingServer trackingServer = new TrackingServer(12071);

        Athlete athlete1 = new Athlete(1,"Joseph", "Clark", "M", 27, "Registered",1234.56, 12, 60, 95);
        Athlete athlete2 = new Athlete(2,"Christina", "Ray", "F", 26, "OnCourse",5678.20, 15, 40, 80 );

        Client client1 = new Client(InetAddress.getLocalHost().getHostAddress(), 12007);
        Client client2 = new Client(InetAddress.getLocalHost().getHostAddress(), 12008);

        trackingServer.addAthlete(athlete1);
        trackingServer.addAthlete(athlete2);

        trackingServer.addClient(client1);
        trackingServer.addClient(client2);

        trackingServer.addClientsToAthlete(1,client1);
        trackingServer.addClientsToAthlete(1,client2);

        trackingServer.addClientsToAthlete(2,client1);
        trackingServer.addClientsToAthlete(2,client2);

        assertEquals(12007,athlete1.getClients().get(0).getPort());
        assertEquals(12008,athlete1.getClients().get(1).getPort());

        assertEquals(12007,athlete2.getClients().get(0).getPort());
        assertEquals(12008,athlete2.getClients().get(1).getPort());

        assertEquals(InetAddress.getByName(client1.getAddress()).getHostAddress(),athlete1.getClients().get(0).getAddress());
        assertEquals(InetAddress.getByName(client2.getAddress()).getHostAddress(),athlete1.getClients().get(1).getAddress());

        trackingServer.getCommunicator().close();

    }

    @Test
    public void testRemoveClientFromAthlete() throws Exception {
        TrackingServer trackingServer = new TrackingServer(12009);

        Athlete athlete1 = new Athlete(1,"Joseph", "Clark", "M", 27, "Registered",1234.56, 12, 60, 95);
        Athlete athlete2 = new Athlete(2,"Christina", "Ray", "F", 26, "OnCourse",5678.20, 15, 40, 80 );
        Athlete athlete3 = new Athlete(3,"Christina", "Ray", "F", 26, "OnCourse",5678.20, 15, 40, 80 );

        Client client1 = new Client(InetAddress.getLocalHost().getHostAddress(), 12010);
        Client client2 = new Client(InetAddress.getLocalHost().getHostAddress(), 12011);

        trackingServer.addAthlete(athlete1);
        trackingServer.addAthlete(athlete2);
        trackingServer.addAthlete(athlete3);

        trackingServer.addClient(client1);
        trackingServer.addClient(client2);

        trackingServer.addClientsToAthlete(1,client1);
        trackingServer.addClientsToAthlete(1,client2);

        trackingServer.removeClientFromAthlete(1, client1);

        trackingServer.addClientsToAthlete(2,client1);
        trackingServer.addClientsToAthlete(2,client2);


        assertEquals(-1,athlete1.getClients().indexOf(client1));
        assertEquals(12011,athlete1.getClients().get(0).getPort());

        trackingServer.removeClientFromAthlete(5, client2);


        trackingServer.getCommunicator().close();

    }

    @Test
    public void testSentToClient() throws Exception {

        TrackingServer trackingServer = new TrackingServer(12012);
        Athlete athlete1 = new Athlete(1,"Joseph", "Clark", "M", 27, "Registered",1234.56, 12, 60, 95);

        Client client1 = new Client(InetAddress.getLocalHost().getHostAddress(), 12013);
        Client client2 = new Client(InetAddress.getLocalHost().getHostAddress(), 12014);

        trackingServer.addClientsToAthlete(1,client1);
        trackingServer.addClientsToAthlete(1,client2);

        Communicator comm1 = new Communicator(12025);
        Communicator comm2 = new Communicator(12026);

        trackingServer.addClient(client1);
        trackingServer.addClient(client2);

        assertEquals(12013, trackingServer.getClientList().get(0).getPort());
        assertEquals(12014, trackingServer.getClientList().get(1).getPort());

        assertEquals(InetAddress.getByName(client1.getAddress()).getHostAddress(), trackingServer.getClientList().get(0).getAddress());
        assertEquals(InetAddress.getByName(client2.getAddress()).getHostAddress(), trackingServer.getClientList().get(1).getAddress());

        assertEquals(12013, trackingServer.getClientByPortNumber(12013).getPort());
        assertEquals(12014, trackingServer.getClientByPortNumber(12014).getPort());

        assertEquals(InetAddress.getByName(client1.getAddress()).getHostAddress(), trackingServer.getClientByPortNumber(12013).getAddress());
        assertEquals(InetAddress.getByName(client2.getAddress()).getHostAddress(), trackingServer.getClientByPortNumber(12014).getAddress());

        Message message = Message.createObject("Registered,1,0,Valentine,Zamora,M,30");
        trackingServer.sendToClients(message,trackingServer.getClientList());

        try {
            Message message1 = Message.createObject(null);
            ArrayList<Client> clients= athlete1.getClients();
            trackingServer.sendToClients(message1,clients);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            assertEquals(null, e.getMessage());
        }

        trackingServer.getCommunicator().close();

    }

    @Test
    public void testSentToLateClient() throws Exception {

        TrackingServer trackingServer = new TrackingServer(12080);
        Athlete athlete1 = new Athlete(1,"Joseph", "Clark", "M", 27, "Registered",1234.56, 12, 60, 95);

        Client client1 = new Client(InetAddress.getLocalHost().getHostAddress(), 12081);
        Client client2 = new Client(InetAddress.getLocalHost().getHostAddress(), 12082);

        trackingServer.addClientsToAthlete(1,client1);
        trackingServer.addClientsToAthlete(1,client2);

        Communicator comm1 = new Communicator(12083);
        Communicator comm2 = new Communicator(12084);

        trackingServer.addClient(client1);
        trackingServer.addClient(client2);

        assertEquals(12081, trackingServer.getClientList().get(0).getPort());
        assertEquals(12082, trackingServer.getClientList().get(1).getPort());

        assertEquals(InetAddress.getByName(client1.getAddress()).getHostAddress(), trackingServer.getClientList().get(0).getAddress());
        assertEquals(InetAddress.getByName(client2.getAddress()).getHostAddress(), trackingServer.getClientList().get(1).getAddress());

        assertEquals(12081, trackingServer.getClientByPortNumber(12081).getPort());
        assertEquals(12082, trackingServer.getClientByPortNumber(12082).getPort());

        assertEquals(InetAddress.getByName(client1.getAddress()).getHostAddress(), trackingServer.getClientByPortNumber(12081).getAddress());
        assertEquals(InetAddress.getByName(client2.getAddress()).getHostAddress(), trackingServer.getClientByPortNumber(12082).getAddress());

        Message message = Message.createObject("Registered,1,0,Valentine,Zamora,M,30");
        trackingServer.sendToLateClients(message,client1);

        try {
            Message message1 = Message.createObject(null);
            ArrayList<Client> clients= athlete1.getClients();
            trackingServer.sendToClients(message1,clients);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            assertEquals(null, e.getMessage());
        }

        trackingServer.getCommunicator().close();

    }


    @Test
    public void testSentToAllClient() throws Exception {
            TrackingServer trackingServer = new TrackingServer(12015);

            Communicator comm1 = new Communicator(12016);
            Communicator comm2 = new Communicator(12017);

            Client client1 = new Client(InetAddress.getLocalHost().getHostAddress(), 12018);
            Client client2 = new Client(InetAddress.getLocalHost().getHostAddress(), 12019);

            trackingServer.addClient(client1);
            trackingServer.addClient(client2);

            assertEquals(12018, trackingServer.getClientList().get(0).getPort());
            assertEquals(12019, trackingServer.getClientList().get(1).getPort());

            assertEquals(InetAddress.getByName(client1.getAddress()).getHostAddress(), trackingServer.getClientList().get(0).getAddress());
            assertEquals(InetAddress.getByName(client2.getAddress()).getHostAddress(), trackingServer.getClientList().get(1).getAddress());

            assertEquals(12018, trackingServer.getClientByPortNumber(12018).getPort());
            assertEquals(12019, trackingServer.getClientByPortNumber(12019).getPort());

            assertEquals(InetAddress.getByName(client1.getAddress()).getHostAddress(), trackingServer.getClientByPortNumber(12018).getAddress());
            assertEquals(InetAddress.getByName(client2.getAddress()).getHostAddress(), trackingServer.getClientByPortNumber(12019).getAddress());

            Message message = Message.createObject("Registered,1,0,Valentine,Zamora,M,30");
            trackingServer.sendToAllClients(message);

            try {
                Message message1 = Message.createObject(null);
                trackingServer.sendToAllClients(message1);
                fail("Expected exception not thrown");
            } catch (Exception e) {
                assertEquals(null, e.getMessage());
            }

            trackingServer.getCommunicator().close();

    }

    @Test
    public void testSendAthleteStatus() throws Exception {

        TrackingServer trackingServer = new TrackingServer(12020);
        Athlete athlete1 = new Athlete(1,"Joseph", "Clark", "M", 27, "Registered",1234.56, 12, 60, 95);


        Client client1 = new Client(InetAddress.getLocalHost().getHostAddress(), 12021);
        Client client2 = new Client(InetAddress.getLocalHost().getHostAddress(), 12022);

        trackingServer.addClientsToAthlete(1,client1);
        trackingServer.addClientsToAthlete(1,client2);

        trackingServer.sendAthleteStatus(athlete1);

        try {
            Message message1 = Message.createObject(null);
            trackingServer.sendAthleteStatus(athlete1);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            assertEquals(null, e.getMessage());
        }

        trackingServer.getCommunicator().close();
    }

    @Test
    public void testStartOfServer() {
        try{
            Thread t=new Thread(new TrackingServer(12023));
            t.start();
        }
        catch(Exception e){

        }
        try{
            Thread t=new Thread(new TrackingServer(-12));
            t.start();
            fail("Expected exception not thrown");
        }
        catch(Exception e){
            assertEquals("port out of range:-12", e.getMessage());
        }
        try{
            Thread t=new Thread(new TrackingServer(12024));
            t.start();
            //fail("Expected exception not thrown");
        }
        catch(Exception e){
            assertEquals("Address already in use: Cannot bind", e.getMessage());
        }

    }

}