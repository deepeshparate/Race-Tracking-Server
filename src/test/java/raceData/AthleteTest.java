package raceData;

import org.junit.Test;

import java.net.InetAddress;

import static org.junit.Assert.*;

public class AthleteTest {
    @Test
    public void validTestConstruction() throws Exception {

        Athlete athlete1 = new Athlete(1,"Joseph", "Clark", "M", 27, "Registered",1234.56, 12, 60, 95);
        Athlete athlete2 = new Athlete(2,"Christina", "Ray", "F", 26, 15 );

        Client client1 = new Client(InetAddress.getLocalHost().getHostAddress(), 12031);
        Client client2 = new Client(InetAddress.getLocalHost().getHostAddress(), 12032);


        assertEquals("Joseph",athlete1.getFirstName());
        assertEquals("Christina",athlete2.getFirstName());

        assertEquals("Clark",athlete1.getLastName());
        assertEquals("Ray",athlete2.getLastName());

        assertEquals(1,athlete1.getBibNumber());
        assertEquals(2,athlete2.getBibNumber());

        assertEquals(12,athlete1.getStart_Time());
        assertEquals(15,athlete2.getStart_Time());

        assertEquals("Registered",athlete1.getStatus());


        assertEquals(1234.56,athlete1.getDistance_Covered(),0.0);
        assertEquals(60,athlete1.getFinish_Time());

        assertEquals(95,athlete1.getLast_Updated_Time());

        athlete1.setStatus("Finished");
        assertEquals("Finished",athlete1.getStatus());

        athlete1.setDistance_Covered(1432.44);
        assertEquals(1432.44,athlete1.getDistance_Covered(),0.0);

        athlete1.setStart_Time(19);
        assertEquals(19,athlete1.getStart_Time());

        athlete1.setFinish_Time(90);
        assertEquals(90,athlete1.getFinish_Time());

        athlete1.setLast_Updated_Time(100);
        assertEquals(100,athlete1.getLast_Updated_Time());

        athlete2.setStatus("Registered");
        assertEquals("Registered",athlete2.getStatus());

        athlete1.addClient(client1);
        athlete1.addClient(client2);

        assertEquals(12031,athlete1.getClients().get(0).getPort());
        assertEquals(12032,athlete1.getClients().get(1).getPort());


        athlete1.removeClient(client1);
        assertEquals(12032,athlete1.getClients().get(0).getPort());






    }

}