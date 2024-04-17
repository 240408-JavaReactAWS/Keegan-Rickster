package com.SpringCar.Controllers;
import com.SpringCar.Exceptions.CarNotFoundException;
import com.SpringCar.Models.Car;
import com.SpringCar.Models.User;
import com.SpringCar.Models.UserRole;
import com.SpringCar.Services.CarService;
import com.SpringCar.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("api/v1/cars")
public class CarController {
    private CarService carService;
    private UserService userService;

    @Autowired
    public CarController(CarService carService, UserService userService) {
        this.carService = carService;
        this.userService = userService;
    }





   @GetMapping
   public ResponseEntity<List<Car>> getAllCars(){

        List<Car> cars;

        try{
            cars = carService.getAllCars();
        }catch(CarNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
       return new ResponseEntity<>(cars, HttpStatus.OK); // Returns a 200
   }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Car>> getCarsByUserId(@PathVariable int id){

        User user = userService.getById(id);


        if(user == null ||user.getRole() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(user.getRole().equals(UserRole.ADMIN)){
            List<Car> cars = carService.getAllCars();
            return new ResponseEntity<>(cars, HttpStatus.OK);
        }
        if(user.getRole().equals(UserRole.USER)){
            List<Car> cars = carService.getCarsByUserId(id);
            return new ResponseEntity<>(cars, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable int id) {
        if (carService.existsById(id)) {
            carService.deleteById(id);
            return ResponseEntity.ok("Car with id " + id + " deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car with ID " + id + " could not found");
        }
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
   @PostMapping("/register/{userId}")
    public Car createNewCar(@RequestBody Car car, @PathVariable int userId) {
        User user = userService.getById(userId);
        user.setId(userId);
        car.setUser(user);
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
