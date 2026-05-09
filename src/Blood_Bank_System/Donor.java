package Blood_Bank_System;

public class Donor {

    private int id;
    private String name;
    private String bloodGroup;
    private String city;
    private String status;   // ADDED or DELETED

    public Donor(int id, String name, String bloodGroup, String city, String status) {
        this.id = id;
        this.name = name;
        this.bloodGroup = bloodGroup;
        this.city = city;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // for saving into file
    public String toFileString() {
        return id + "," + name + "," + bloodGroup + "," + city + "," + status;
    }

    @Override
    public String toString() {
        return id + "  " + name + "  " + bloodGroup + "  " + city + "  " + status;
    }
}
