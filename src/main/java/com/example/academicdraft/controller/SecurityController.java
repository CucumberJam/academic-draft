package com.example.academicdraft.controller;

import com.example.academicdraft.dto.UserDto;
import com.example.academicdraft.entity.User;
import com.example.academicdraft.service.LogService;
import com.example.academicdraft.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.naming.Binding;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
public class SecurityController {

    private UserService userService;
    private LogService logService;

    public SecurityController(@NotNull UserService userService,
                              @NotNull LogService logService){
        this.userService = userService;
        this.logService = logService;
    }

    @GetMapping("/index")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        User existingUser = userService.findUserByEmail(userDto.getEmail());
        if(existingUser != null && existingUser.getEmail() != null && existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "На этот адрес электронной почты уже зарегистрирована учетная запись.");
        }
        if (result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }
        logService.info(userDto.getType() + " " + userDto.getName() + " was saved.");

        userService.saveUser(userDto);

        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String users(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

}