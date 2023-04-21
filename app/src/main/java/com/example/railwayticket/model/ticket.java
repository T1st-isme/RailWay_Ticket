package com.example.railwayticket.model;

public class ticket {
    public int id;
    public String TicketId;
    public String timego;
    public String timeend;


    public String stateGo;
    public String stateEnd;
    public int state_id;
    public String price;


    public ticket() {
    }

    public ticket(int id, String ticketId, String timego, String timeend, String stateGo, String stateEnd, String price) {
        this.id = id;
        TicketId = ticketId;
        this.timego = timego;
        this.timeend = timeend;
        this.stateGo = stateGo;
        this.stateEnd = stateEnd;
        this.price = price;
    }

    public ticket(int id, String tckId, String timego, String timeend, String price) {
        this.id = id;
        this.TicketId = tckId;
        this.timego = timego;
        this.timeend = timeend;
        this.price = price;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimego() {
        return timego;
    }

    public void setTimego(String timego) {
        this.timego = timego;
    }

    public String getTimeend() {
        return timeend;
    }

    public void setTimeend(String timeend) {
        this.timeend = timeend;
    }

    public String getTicketId() {
        return TicketId;
    }

    public void setTicketId(String ticketId) {
        TicketId = ticketId;
    }

    public String getStateGo() {
        return stateGo;
    }

    public void setStateGo(String stateGo) {
        this.stateGo = stateGo;
    }

    public String getStateEnd() {
        return stateEnd;
    }

    public void setStateEnd(String stateEnd) {
        this.stateEnd = stateEnd;
    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public String getTrainID() {
        return TicketId;
    }

    public void setTrainID(String trainID) {
        TicketId = trainID;
    }


}
