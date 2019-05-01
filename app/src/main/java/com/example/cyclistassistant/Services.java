package com.example.cyclistassistant;

public class Services {
    private int datePicture;
    private String date, distance, propulsionService, suspensionService, brakingService,
        wheelsService, otherRepairs, cost;

    public Services(int facePicture, String date, String distance, String propulsionService, String suspensionService,
        String brakingService, String wheelsService, String otherRepairs, String cost) {
        this.datePicture = facePicture;
        this.date = date;
        this.distance = distance;
        this.propulsionService = propulsionService;
        this.suspensionService = suspensionService;
        this.brakingService = brakingService;
        this.wheelsService = wheelsService;
        this.otherRepairs = otherRepairs;
        this.cost = cost;
    }

//    public int getFacePicture() {
//        return facePicture;
//    }

    public int getFacePicture() {
        return datePicture;
    }

    public String getDate() {
        return date;
    }

    public String getDistance() {
        return distance;
    }

    public String getPropulsionService() {
        return propulsionService;
    }

    public String getSuspensionService() {return suspensionService; }

    public String getBrakingService() {return brakingService; }

    public String getWheelsService() {return wheelsService; }

    public String getOtherRepairs() {return otherRepairs; }

    public String getCost() {return cost; }
}
