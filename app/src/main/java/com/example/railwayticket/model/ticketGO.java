package com.example.railwayticket.model;

public class ticketGO {


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


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public String getDateGo() {
        return dateGo;
    }

    public void setDateGo(String dateGo) {
        this.dateGo = dateGo;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    String date;


    int id;
    String tickID;
    String dateGo;
    String dateEnd;
    String timeGO;
    String timeEnd;
    String seat;
    public String stateGO;
    public String stateEnd;
    String price;

    public ticketGO() {
    }

    public ticketGO(String tickID, String stateGO, String stateEnd, String dateGo, String dateEnd, String price) {
        this.tickID = tickID;
        this.stateGO = stateGO;
        this.stateEnd = stateEnd;
        this.dateGo = dateGo;
        this.dateEnd = dateEnd;
        this.price = price+".000đ";
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getTimeGO() {
        return timeGO;
    }

    public void setTimeGO(String timeGO) {
        this.timeGO = timeGO;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getSeat() {
        return seat;
    }

    public String getTickID() {
        return tickID;
    }

    public void setTickID(String tickID) {
        this.tickID = tickID;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public ticketGO(String tickID, String seat, String dateGo, String timeGO, String timeEnd, String stateGO, String stateEnd, String price) {
        this.tickID = tickID;
        this.seat = seat;
        this.dateGo = dateGo;
        this.timeGO = timeGO;
        this.timeEnd = timeEnd;
        this.stateGO = stateGO;
        this.stateEnd = stateEnd;
        this.price = price;
    }

//    public ticketGO(String seat, String stateGO, String stateEnd) {
//        this.seat = seat;
//        this.stateGO = stateGO;
//        this.stateEnd = stateEnd;
//    }

}
