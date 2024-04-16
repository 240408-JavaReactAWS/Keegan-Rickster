package com.SpringCar.Services;

import com.SpringCar.Exceptions.UserNotFoundException;
import com.SpringCar.Models.User;
import com.SpringCar.Repos.UserDAO;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserDAO userDao;

    public UserService(UserDAO userDao) {
        this.userDao = userDao;
    }

    public boolean isAuthenticated(User user){
        return userDao.existsByEmailAndPassword(user.getEmail(), user.getPassword());
    }

    public User getById(int id){

        return userDao.findById(id).orElseThrow(() -> new UserNotFoundException("User with id "+id+" could not be found"));
    }
}
