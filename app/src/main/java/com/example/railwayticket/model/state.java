package com.example.railwayticket.model;

public class state {
    int state_id;
    String state_go;
    String state_end;

    public state(int state_id, String state_go, String state_end) {
        this.state_id = state_id;
        this.state_go = state_go;
        this.state_end = state_end;
    }

    public int getState_id() {
        return state_id;
    }

    public void setState_id(int state_id) {
        this.state_id = state_id;
    }

    public String getState_go() {
        return state_go;
    }

    public void setState_go(String state_go) {
        this.state_go = state_go;
    }

    public String getState_end() {
        return state_end;
    }

    public void setState_end(String state_end) {
        this.state_end = state_end;
    }
}
