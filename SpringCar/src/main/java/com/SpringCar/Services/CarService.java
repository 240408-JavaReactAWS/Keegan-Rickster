package com.SpringCar.Services;

import com.SpringCar.Exceptions.CarNotFoundException;
import com.SpringCar.Models.Car;
import com.SpringCar.Repos.CarDAO;
import com.SpringCar.Repos.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private CarDAO carDao;

    @Autowired
    public CarService(CarDAO carDao){
        this.carDao = carDao;
    }

    public List<Car> getAllCars() {
        Optional<List<Car>> carList = Optional.of(carDao.findAll());
        return carList.orElseThrow(() -> new CarNotFoundException("No Car could be found"));
    }


    public List<Car> getCarsByUserId(int id) {
        List<Car> userCars = carDao.findByUserId(id);

        if(userCars.isEmpty()){
            throw new CarNotFoundException("No car found with user id " + id);
        }
        return userCars;
    }



    //find car by its ID or return an exception
    public Car findCarByID(int id) {
        return carDao.findById(id).orElseThrow(() -> new CarNotFoundException("No car by this ID found: " +id));
    }

}
