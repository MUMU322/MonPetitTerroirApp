package com.example.monpetitterroir.model;

public class Seller {
    String city;
    String name;
    String cp;
    String id;

    public Seller(String id, String name, String city, String cp) {
        this.id = id;
        this.city = city;
        this.name = name;
        this.cp = cp;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }
}
