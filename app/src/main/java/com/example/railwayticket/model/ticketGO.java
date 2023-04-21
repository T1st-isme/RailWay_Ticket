package com.example.railwayticket.model;

public class ticketGO {
    public ticketGO() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    int id;
    String date;

    public ticketGO(String stateGO, String stateEnd) {
        this.stateGO = stateGO;
        this.stateEnd = stateEnd;
    }

    public String getStateGO() {
        return stateGO;
    }

    public void setStateGO(String stateGO) {
        this.stateGO = stateGO;
    }

    public String getStateEnd() {
        return stateEnd;
    }

    public void setStateEnd(String stateEnd) {
        this.stateEnd = stateEnd;
    }

    public String stateGO;
    public String stateEnd;
    int price;


}
