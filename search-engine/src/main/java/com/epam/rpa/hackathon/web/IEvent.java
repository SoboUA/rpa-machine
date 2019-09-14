package com.epam.rpa.hackathon.web;

public interface IEvent {

    String getCategory();

    void setCategory(String category);

    String getTitle();

    void setTitle(String title);

    String getStartDate();

    void setStartDate(String startDate);

    String getPlace();

    void setPlace(String place);

    String getDescription();

    void setDescription(String description);

    void setImageLink(String imageLink);

    String getImageLink();

}
