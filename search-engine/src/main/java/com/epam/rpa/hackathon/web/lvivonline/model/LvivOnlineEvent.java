package com.epam.rpa.hackathon.web.lvivonline.model;

import com.epam.rpa.hackathon.web.IEvent;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LvivOnlineEvent implements IEvent {

    private String category;
    private String title;
    private String startDate;
    private String place;
    private String description;
    private String imageLink;

    public LvivOnlineEvent(String category, String title, String startDate, String place, String description, String imageLink) {
        this.category = category;
        this.title = title;
        this.startDate = startDate;
        this.place = place;
        this.description = description;
        this.imageLink = imageLink;
    }

    @JsonProperty(value = "category")
    public String getCategory() {
        return category;
    }

    @JsonProperty(value = "category")
    public void setCategory(String category) {
        this.category = category;
    }

    @JsonProperty(value = "title")
    public String getTitle() {
        return title;
    }

    @JsonProperty(value = "title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty(value = "startDate")
    public String getStartDate() {
        return startDate;
    }

    @JsonProperty(value = "startDate")
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @JsonProperty(value = "place")
    public String getPlace() {
        return place;
    }

    @JsonProperty(value = "place")
    public void setPlace(String place) {
        this.place = place;
    }

    @JsonProperty(value = "description")
    public String getDescription() {
        return description;
    }

    @JsonProperty(value = "description")
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getImageLink() {
        return imageLink;
    }

    @Override
    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

}
