package com.example.railwayticket.model;

public class ticketGO {
    int id;
    String stateName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public ticketGO(int id, String stateName) {
        this.id = id;
        this.stateName = stateName;
    }


}
