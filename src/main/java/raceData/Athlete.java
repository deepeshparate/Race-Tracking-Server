package raceData;

import java.util.HashMap;
import java.util.Observable;
import java.util.ArrayList;
import java.util.List;

public class Athlete extends Observable {

    private ArrayList<Client> clients=new ArrayList<Client>();

    private int bibNumber;
    private String firstName;
    private String lastName;
    private String gender;
    private int age;
    private String status;
    private double distance_Covered = 0;
    private int start_Time;
    private int finish_Time = 0;
    private int last_Updated_Time = 0;

    public Athlete (int bibNumber,String firstName, String lastName, String gender,int age,int start_Time)
    {
        this.bibNumber=bibNumber;
        this.start_Time=start_Time;
        this.firstName=firstName;
        this.lastName=lastName;
        this.gender=gender;
        this.age=age;

    }

    public Athlete(int bibNumber, String firstName, String lastName, String gender, int age, String status, double distance_Covered, int start_Time, int finish_Time, int last_Updated_Time) {

        this.bibNumber = bibNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.status = status;
        this.distance_Covered = distance_Covered;
        this.start_Time = start_Time;
        this.finish_Time = finish_Time;
        this.last_Updated_Time = last_Updated_Time;

    }

    public int getBibNumber()
    {
        return bibNumber;
    }

    public void addClient(Client c) {
        clients.add(c);
    }

    public void removeClient(Client c) {
        clients.remove(c);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public void setStatus(String status) { this.status = status; }

    public String getStatus() {
        return status;
    }

    public void setDistance_Covered(double distance_Covered) {
        this.distance_Covered = distance_Covered;
    }

    public double getDistance_Covered() {
        return distance_Covered;
    }

    public void setStart_Time(int start_Time) {
        this.start_Time = start_Time;
    }

    public int getStart_Time() {
        return start_Time;
    }

    public void setFinish_Time(int finish_Time) {
        this.finish_Time = finish_Time;
    }

    public int getFinish_Time() {
        return finish_Time;
    }

    public void setLast_Updated_Time(int last_Updated_Time) {
        this.last_Updated_Time = last_Updated_Time;
    }

    public int getLast_Updated_Time() {
        return last_Updated_Time;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }


}
