package com.example.railwayticket.model;

public class PaymentMethod {
    private int img;
    private String Name;


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public PaymentMethod(String name, int img) {
        Name = name;
        this.img = img;
    }
}
