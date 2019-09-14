package com.epam.rpa.hackathon;

import com.epam.rpa.hackathon.web.gastroli.GetGastroliUaEventsAction;
import com.epam.rpa.hackathon.web.gastroli.data.DataFilter;

public class App {

    public static void main(String[] args) {

        System.out.println( DataFilter.convert("10 Липня 2019"));

        //System.out.println(new GetGastroliUaEventsAction().getEvents("2017-12-02", "2019-12-09"));
    }

}
