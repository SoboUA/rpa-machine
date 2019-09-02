package com.rpa.controltower.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("main")
public class MainPageController {

    @GetMapping("/info")
    public String getInfo(Model model){

        model.addAttribute("variable", "hi");

        return "main";
    }
}
