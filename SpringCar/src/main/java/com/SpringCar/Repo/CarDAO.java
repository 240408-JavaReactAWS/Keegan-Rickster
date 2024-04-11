package com.SpringCar.Repo;

import com.SpringCar.Model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDAO extends JpaRepository<Car,Integer> {

}
