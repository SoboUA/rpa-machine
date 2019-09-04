package com.epam.rpa.hackathon;

import com.epam.rpa.hackathon.web.lvivonline.GetLvivOnlineEventsAction;

public class App {
    public static void main(String[] args) {

        System.out.println(new GetLvivOnlineEventsAction().getEventsJson());

    }
}
