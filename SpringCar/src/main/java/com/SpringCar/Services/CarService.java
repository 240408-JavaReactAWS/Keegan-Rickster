package com.SpringCar.Services;

import com.SpringCar.Exceptions.CarNotFoundException;
import com.SpringCar.Models.Car;
import com.SpringCar.Repos.CarDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private CarDAO carDao;

    @Autowired
    public CarService(CarDAO carDao){
        this.carDao = carDao;
    }

    public List<Car> getAllCars() {
        return carDao.findAll();
    }
}
