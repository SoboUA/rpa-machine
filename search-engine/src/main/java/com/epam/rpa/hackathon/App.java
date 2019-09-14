package com.epam.rpa.hackathon;

import com.epam.rpa.hackathon.web.gastroli.GetGastroliUaEventsAction;

public class App {

    public static void main(String[] args) {
        System.out.println(new GetGastroliUaEventsAction().getEvents());
    }

}
