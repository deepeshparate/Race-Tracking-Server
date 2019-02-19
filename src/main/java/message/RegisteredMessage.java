package message;

import raceData.Athlete;
import raceServer.TrackingServer;

public class RegisteredMessage extends Message {

    private int bibNumber;
    private int time;
    private String firstName;
    private String lastName;
    private String gender;
    private int age;


    public RegisteredMessage (int bibNumber,int time,String firstName, String lastName, String gender,int age)
    {
        this.bibNumber=bibNumber;
        this.time=time;
        this.firstName=firstName;
        this.lastName=lastName;
        this.gender=gender;
        this.age=age;
    }
    @Override
    public void execute(TrackingServer trackingServer) {
        Athlete athlete= new Athlete(bibNumber,firstName, lastName, gender, age, time);
        trackingServer.addAthlete(athlete);
        trackingServer.sendToAllClients(this);
    }

    public String toString() {
        return "Athlete,"+String.valueOf(bibNumber)+","+firstName+","+lastName+","+gender+","+String.valueOf(age);
    }
}
