package com.example.railwayticket.model;

public class ticketGO {
    public ticketGO(int id, String date, String stateName, int price) {
        this.id = id;
        this.date = date;
        this.stateName = stateName;
        this.price = price;
    }

    public ticketGO(String date, String stateName, int price) {
        this.date = date;
        this.stateName = stateName;
        this.price = price;
    }

    public ticketGO(String state) {
        this.stateName = state;
    }

    public ticketGO() {

    }

    public ticketGO(int id, String state) {
        this.id = id;
        this.stateName = state;
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

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    int id;
    String date;
    public String stateName;
    int price;





}
