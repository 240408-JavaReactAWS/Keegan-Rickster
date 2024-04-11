package com.SpringCar.Controller;
import com.SpringCar.Model.Car;
import com.SpringCar.Service.CarService;
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

}
