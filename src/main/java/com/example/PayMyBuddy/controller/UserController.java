package com.example.PayMyBuddy.controller;

import com.example.PayMyBuddy.dto.UserDto;
import com.example.PayMyBuddy.model.Account;
import com.example.PayMyBuddy.model.User;
import com.example.PayMyBuddy.service.AccountService;
import com.example.PayMyBuddy.service.CustomUserDetails;
import com.example.PayMyBuddy.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class);

    private AccountService accountService;
    private UserService userService;

    @Autowired
    public void LoginController(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable int id) {
        User user = userService.getUser(id);
        logger.info("User info by Id " + user.toString());
        return user;
    }

    @RequestMapping(value = "/getUserEmail", method = RequestMethod.GET)
    public User getUserByEmail(@RequestParam("email") String email) {
        User user = userService.findByEmail(email);
        logger.info("User info by Id " + user.toString());
        return user;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        List<User> listOfUsers = userService.getAllUsers();
        logger.info("All user info " + listOfUsers.toString());
        return listOfUsers;
    }

    @PostMapping("/users")// Add a user into DB
    public ModelAndView processRegister(UserDto userDto) {
        logger.info("user info " + userDto.toString());
        User user = userService.mapUserDtotoUser(userDto);
        logger.info("User info for user table" + user.toString());
        user = userService.addUser(user);
        logger.debug("adding user info in the DB");
        Account account = accountService.mapUserDtotoAccount(userDto);
        logger.info("Account info for account table" + account.toString());
        account.setUser(user);
        accountService.addAccount(account);
        logger.debug("adding account info in the DB");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register_success");
        logger.info("user is registered successfully");
        return modelAndView;
    }

    @RequestMapping("/updateBalance")
    public ModelAndView updateUser(@AuthenticationPrincipal CustomUserDetails customUserDetails, UserDto userDto) {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.findByEmail(customUserDetails.getUsername());
        user.setBalance(user.getBalance() + userDto.getBalance());
        logger.info("updated user info " + user.toString());
        userService.updateUser(user);
        modelAndView.setViewName("add_money_success");
        logger.info("money is added to the account");
        return modelAndView;
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public User deleteUser(@PathVariable int id) {
        User user = userService.deleteUser(id);
        logger.info("user info to be deleted" + user.toString());
        return user;
    }

}
