package org.grp2.gms.domain;

public class Greenhouse {
    private int id;
    private String ipAddress;
    private String name;
    private String location;


    public Greenhouse(int id, String ipAddress, String name, String location) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.name = name;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
