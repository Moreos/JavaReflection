package com.moreos.models;

public class Phone {
    private String model;
    private Integer price;
    private String owner;

    public Phone(String model, Integer price, String owner) {
        this.model = model;
        this.price = price;
        this.owner = owner;
    }

    public Phone() {
        this.model = "smartphone";
        this.price = 0;
        this.owner = "lost";
    }


    public void findPhone(String owner, String model) {
        this.owner = owner;
        this.model = model;
    }

    @Override
    public String toString() {
        return "{ model = '" + model +
                "', price = '" + price +
                "', owner = '" + owner + "' }";
    }
}
