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

    //find car by its ID or return an exception
    public Car findCarByID(int id) {
        return carDao.findById(id).orElseThrow(() -> new CarNotFoundException("No car by this ID found: " +id));
    }

    //Save a car to the database
    public Car addCar(Car car) {
        return carDao.save(car);
    }

    public Car updateCar(int id, Car car) {
        Car update = carDao.findById(id).orElseThrow(() -> new CarNotFoundException("No car by this ID found: " +id));
        update.setMake(car.getMake());
        update.setModel(car.getModel());
        update.setColor(car.getColor());

        carDao.save(update);

        return update;
    }

}
