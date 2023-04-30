package com.example.railwayticket.model;

import java.io.Serializable;

public class ticketGO implements Serializable {

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    String phone;

    public ticketGO(String tickID, String stateG, String stateEnd, String dateGo, String timeGO, String price, int seat) {
        this.tickID = tickID;
        this.stateGO = stateG;
        this.stateEnd = stateEnd;
        this.dateGo = dateGo;
        this.timeGO = timeGO;
        this.price = price + ".000đ";
        this.seat = seat;
    }

    public ticketGO(int id, String tckid, String name, String phone, String stateG, String stateE, String dateGo, String dateEnd, String timeGO, String price, int seat) {
        this.id = id;
        this.tickID = tckid;
        this.name = name;
        this.phone = phone;
        this.stateGO = stateG;
        this.stateEnd = stateE;
        this.dateGo = dateGo;
        this.dateEnd = dateEnd;
        this.timeGO = timeGO;
        this.price = price + ".000đ";
        this.seat = seat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int id;
    public String tickID;
    String dateGo;
    String dateEnd;
    public String timeGO;
    public String timeEnd;
    int seat;
    public String stateGO;
    public String stateEnd;
    public String price;

    public ticketGO() {
    }

//    public ticketGO(String tickID, String stateGO, String stateEnd, String dateGo, String dateEnd, String price) {
//        this.tickID = tickID;
//        this.stateGO = stateGO;
//        this.stateEnd = stateEnd;
//        this.dateGo = dateGo;
//        this.dateEnd = dateEnd;
//        this.price = price+".000đ";
//    }

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

    public int getSeat() {
        return seat;
    }

    public String getTickID() {
        return tickID;
    }

    public void setTickID(String tickID) {
        this.tickID = tickID;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public ticketGO(int id, String tickID, String dateEnd, String dateGo, String timeGO, String timeEnd, String stateGO, String stateEnd, String price) {
        this.id = id;
        this.tickID = tickID;
        this.dateEnd = dateEnd;
        this.dateGo = dateGo;
        this.timeGO = timeGO;
        this.timeEnd = timeEnd;
        this.stateGO = stateGO;
        this.stateEnd = stateEnd;
        this.price = price + ".000đ";
    }

//    public ticketGO(String seat, String stateGO, String stateEnd) {
//        this.seat = seat;
//        this.stateGO = stateGO;
//        this.stateEnd = stateEnd;
//    }

}
