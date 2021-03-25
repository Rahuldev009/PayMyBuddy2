package com.example.PayMyBuddy.controller;

import com.example.PayMyBuddy.dto.UserDto;
import com.example.PayMyBuddy.model.User;
import com.example.PayMyBuddy.service.ConnectionService;
import com.example.PayMyBuddy.service.CustomUserDetails;
import com.example.PayMyBuddy.service.TransactionService;
import com.example.PayMyBuddy.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {
    private static final Logger logger = LogManager.getLogger(LoginController.class);

    private TransactionService transactionService;
    private ConnectionService connectionService;
    private UserService userService;

    @Autowired
    public void LoginController(TransactionService transactionService, ConnectionService connectionService,
                                UserService userService) {
        this.transactionService = transactionService;
        this.connectionService = connectionService;
        this.userService = userService;
    }

    @RequestMapping(value = "/")
    public ModelAndView viewHomePage(ModelAndView modelAndView) {
        logger.info("login page ");
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/login-error")
    public ModelAndView viewLoginError(ModelAndView modelAndView) {
        logger.info("login error page");
        modelAndView.setViewName("login-error");
        return modelAndView;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "signup_form";
    }

    @GetMapping("/home")
    public ModelAndView listUsers(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.findByEmail(customUserDetails.getUsername());
        modelAndView.addObject("loggedInUser", user);
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @GetMapping("/contact")
    public ModelAndView contact(ModelAndView modelAndView) {
        modelAndView.setViewName("contact");
        return modelAndView;
    }

}
