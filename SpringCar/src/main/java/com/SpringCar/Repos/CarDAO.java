package com.SpringCar.Repos;

import com.SpringCar.Models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarDAO extends JpaRepository<Car,Integer> {

    List<Car> findByUserId(int id);

}
