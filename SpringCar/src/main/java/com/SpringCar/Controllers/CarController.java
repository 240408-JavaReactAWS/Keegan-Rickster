package com.SpringCar.Controllers;
import com.SpringCar.Models.Car;
import com.SpringCar.Services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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

   //Add a new car to the database
   @PostMapping
    public Car createNewCar(@RequestBody Car car) {
        return carService.addCar(car);
   }

}
