package com.models.car;

import com.models.transport.Transport;
import com.myAnnotation.MyService;

@MyService("car")
public class Car extends Transport{
    public Car(String factory, String model, int speed) {
        super(factory, model, speed);
    }
}

