package com.epam.rpa.hackathon.web.ticketclub;

public enum TicketClubCategory {
    MUSIC("Музика"),
    THEATRE("Театр"),
    MOVIE("Кіно"),
    FOR_CHILDREN("Дітям"),
    OTHER("Інше");

    private String value;

    TicketClubCategory(String category) {
        this.value = category;
    }

    @Override
    public String toString() {
        return value;
    }
}
