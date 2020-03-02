package com.pnytrainings.pnyjt1.model;

public class Contact {

    private String name;
    private String number;
    private int image;

    public Contact(String name, String number, int image) {
        this.name = name;
        this.number = number;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public int getImage() {
        return image;
    }
}
