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

    private UserDAO userDao;

    @Autowired
    public CarService(CarDAO carDao, UserDAO userDao){
        this.carDao = carDao;
        this.userDao = userDao;
    }

    public List<Car> getAllCars() {
        Optional<List<Car>> carList = Optional.of(carDao.findAll());
        return carList.orElseThrow(() -> new CarNotFoundException("No Car could be found"));
    }


    public List<Car> getCarsByUserId(int id) {
        List<Car> userCars = carDao.findByUserId(id);

        boolean userExist = userDao.existsById(id);

        if(!userExist){
            throw new CarNotFoundException("No car found with user id " + id);
        }
        return userCars;
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

    public void deleteById(int id) {
        Optional<Car> car = carDao.findById(id);
        if(!car.isPresent()){
            throw new CarNotFoundException("Car with id " + id + " could not be found");
        }
        carDao.deleteById(id);
    }

    public boolean existsById(int id) {

        return carDao.existsById(id);
    }
}
