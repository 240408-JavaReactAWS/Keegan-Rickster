package com.SpringCar.Repos;

import com.SpringCar.Models.Car;
import com.SpringCar.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDAO extends JpaRepository<User,Integer>{

    boolean existsByEmailAndPassword(String username, String password);


}
