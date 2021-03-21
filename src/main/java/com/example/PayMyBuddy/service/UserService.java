package com.example.PayMyBuddy.service;

import com.example.PayMyBuddy.dao.UserDao;
import com.example.PayMyBuddy.dto.UserDto;
import com.example.PayMyBuddy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Transactional
    public User getUser(int id) {
        return userDao.getUser(id);
    }

    @Transactional
    public User addUser(User user) {
        return userDao.addUser(user);
    }

    @Transactional
    public User updateUser(User user) {
       return userDao.updateUser(user);
    }

    @Transactional
    public User deleteUser(int id) {
       return userDao.deleteUser(id);
    }

    @Transactional
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }


    public User mapUserDtotoUser(UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(encodedPassword);
        user.setBalance(userDto.getBalance());
        user.setCurrency(userDto.getCurrency());
        return user;
    }

}
