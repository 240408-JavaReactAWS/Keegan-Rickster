package com.SpringCar.Controllers;
import com.SpringCar.Exceptions.CarNotFoundException;
import com.SpringCar.Models.Car;
import com.SpringCar.Services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("api/v1/cars")
public class CarController {
    private CarService carService;
    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }


   @GetMapping
   public List<Car> getAllCars(){

        return carService.getAllCars();
   }

   //Get car by its ID
   @GetMapping("{id}")
    public ResponseEntity<Car> getCarByID(@PathVariable int id) {
        Car car;

        try {
            car = carService.findCarByID(id);
        } catch (CarNotFoundException e) {
            return  new ResponseEntity<>(NOT_FOUND);
       }

        return new ResponseEntity<>(car, OK);
   }

}
