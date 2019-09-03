package com.rpa.controltower.controller;

import com.rpa.controltower.model.ui.Site;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("main")
public class MainPageController {

    @GetMapping("/search")
    public String getInfo(Model model){

        //TODO Sobo make flexy
        List<Site> allSites = Arrays.asList(
                new Site(1,"lvivInfo", "lviv.com", "nice Site"),
                new Site(2,"touristLviv", "tourists.com", "norm"),
                new Site(3,"livandovkaLife", "gopnik.com", "bad site")
        );


        model.addAttribute("allSites", allSites);

        return "main";
    }
}
