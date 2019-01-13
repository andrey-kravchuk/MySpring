package com.test;

import com.models.airplane.Airplane;
import com.models.car.Car;
import com.models.ship.Ship;
import com.models.transport.Transport;
import com.mycontext.MyContext;

import java.util.Arrays;
import java.util.List;

public class MainTest {
    public static void main(String[] args) {




        MyContext myContext = new MyContext();

        myContext.analyze();
        System.out.println(myContext.getBeans());


    }

}
