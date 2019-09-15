package com.epam.rpa.hackathon;

import com.epam.rpa.hackathon.web.gastroli.GetGastroliUaEventsAction;
import com.epam.rpa.hackathon.web.gastroli.data.DataFilter;
import com.epam.rpa.hackathon.web.ticketclub.GetTicketClubEventsAction;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class App {

    public static void main(String[] args) {

        //System.out.println( DataFilter.convert("21 Березня 2019, четвер 10:00"));

        System.out.println(new GetTicketClubEventsAction().getEventsForPeriod(
                LocalDate.parse("2019-08-02", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalDate.parse("2019-10-09", DateTimeFormatter.ofPattern("yyyy-MM-dd"))));

    }

}
