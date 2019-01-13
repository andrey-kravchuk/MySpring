package com.models.transport;

public abstract class Transport {
    private String factory;
    private String model;
    private int speed;

    public Transport(String factory, String model, int speed) {
        this.factory = factory;
        this.model = model;
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "{" +
                "factory='" + factory + '\'' +
                ", model='" + model + '\'' +
                ", speed=" + speed +
                '}';
    }



}


