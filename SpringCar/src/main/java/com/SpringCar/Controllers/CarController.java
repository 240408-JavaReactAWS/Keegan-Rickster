package com.SpringCar.Controllers;
import com.SpringCar.Exceptions.CarNotFoundException;
import com.SpringCar.Models.Car;
import com.SpringCar.Services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            return new ResponseEntity<>(NOT_FOUND);
       }

        return new ResponseEntity<>(car, OK);
   }

   //Add a new car to the database
   @PostMapping
    public Car createNewCar(@RequestBody Car car) {
        return carService.addCar(car);
   }

   //update an existing car in the database
   @PutMapping("{id}")
    public ResponseEntity<Car> updateCar(@PathVariable int id, @RequestBody Car carInfo) {
        try {
            return new ResponseEntity<>(carService.updateCar(id, carInfo), OK);
        } catch (CarNotFoundException e) {
            return new ResponseEntity<>(NOT_FOUND);
        }
   }

}
