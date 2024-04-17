package com.SpringCar.Services;

import com.SpringCar.Models.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarServiceTest {

    Car car1;
    Car car2;
    @BeforeEach
    void setUp() {

       car1 = new Car(1000,"model S","tesla","white");
        car2 = new Car(1001,"civic","Honda","blue");

    }

    @Test
    void getAllCars() {

        List<Car> caList = new ArrayList<>();
        caList.add(car1);
        caList.add(car2);
       assertEquals(2,caList.size());

    }

    @Test
    void getCarsByUserId() {
    }
}