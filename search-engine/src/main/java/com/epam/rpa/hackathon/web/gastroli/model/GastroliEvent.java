package com.epam.rpa.hackathon.web.gastroli.model;

import com.epam.rpa.hackathon.web.IEvent;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GastroliEvent implements IEvent {

    private String category;
    private String title;
    private String startDate;
    private String place;
    private String description;
    private String imageLink;

    public GastroliEvent(String category, String title, String startDate, String place, String description, String image) {
        this.category = category;
        this.title = title;
        this.startDate = startDate;
        this.place = place;
        this.description = description;
        this.imageLink = image;
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

    @JsonProperty(value = "image")
    public String getImageLink() {
        return imageLink;
    }

    @JsonProperty(value = "image")
    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public static class UniqueEvent {

        private List<GastroliEvent> unique;

        public UniqueEvent(List<GastroliEvent> unique) {
            this.unique = unique;
        }

        public List<GastroliEvent> simplify() {

            return unique.stream().collect(Collectors
                    .collectingAndThen(Collectors
                            .toMap(GastroliEvent::getTitle, Function.identity(), (left, right) -> {
                                left.setCategory(left.getCategory() + ", " + right.getCategory());
                                return left;
                            }), m -> new ArrayList<>(m.values())));
        }
    }

    @Override
    public String toString() {
        return "GastroliEvent{" +
                "category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", startDate='" + startDate + '\'' +
                ", place='" + place + '\'' +
                ", description='" + description + '\'' +
                ", imageLink='" + imageLink + '\'' +
                '}';
    }
}
