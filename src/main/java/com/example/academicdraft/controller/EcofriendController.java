package com.example.academicdraft.controller;

import com.example.academicdraft.entity.Ecofriend;
import com.example.academicdraft.repository.EcofriendRepository;
import com.example.academicdraft.repository.LogRepository;
import com.example.academicdraft.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Slf4j
@Controller
public class EcofriendController {

    private EcofriendRepository ecofriendRepository;

    private LogService logService;

    public EcofriendController(@NotNull EcofriendRepository ecofriendRepository,
                               @NotNull LogService logService){
        this.ecofriendRepository = ecofriendRepository;
        this.logService = logService;
    }

    @GetMapping("/list")
    public ModelAndView getAllEcofriends(){
        log.info("/list -> connection");
        ModelAndView mav = new ModelAndView("list-ecofriends");
        mav.addObject("ecofriends", ecofriendRepository.findAll());
        return mav;
    }

    @GetMapping("/addEcofriendForm")
    public ModelAndView addEcofriendForm(){

        ModelAndView mav = new ModelAndView("add-ecofriend-form");
        Ecofriend ecofriend = new Ecofriend();
        logService.info("User " + currentUserName() + " added ecofriend " + ecofriend.getName());
        mav.addObject("ecofriend", ecofriend);
        return mav;
    }

    @PostMapping("/saveEcofriend")
    public String saveEcofriend(@ModelAttribute Ecofriend ecofriend){
        ecofriendRepository.save(ecofriend);
        logService.info("User " + currentUserName() + " saved ecofriend " + ecofriend.getName());
        return "redirect:/list";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long ecofriendId){

        ModelAndView mav = new ModelAndView("add-ecofriend-form");
        Optional<Ecofriend> optionalEcofriend = ecofriendRepository.findById(ecofriendId);
        Ecofriend ecofriend = new Ecofriend();
        if(optionalEcofriend.isPresent()){
            ecofriend = optionalEcofriend.get();
        }
        mav.addObject("ecofriend", ecofriend);

        logService.info("User " + currentUserName() + " changed ecofriend " + ecofriend.getName() + " or went out before changing...");

        return mav;
    }

    @GetMapping("/deleteEcofriend")
    public String deleteEcofriend(@RequestParam Long ecofriendId){
        Optional<Ecofriend> ecofriends = ecofriendRepository.findById(ecofriendId);
        logService.info("User " + currentUserName() + " deleted ecofriend " + ecofriends.get().getName());

        ecofriendRepository.deleteById(ecofriendId);
        return "redirect:/list";
    }
    private String currentUserName(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userDetails.getUsername();
        return userDetails.getUsername();
    }

}
