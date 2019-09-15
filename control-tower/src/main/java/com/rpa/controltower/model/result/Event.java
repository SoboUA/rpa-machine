package com.rpa.controltower.model.result;

public class Event implements IEvent {

    private String category;
    private String title;
    private String startDate;
    private String place;
    private String description;
    private String imageLink;

    public Event(String category, String title, String startDate, String place, String description, String imageLink) {
        this.category = category;
        this.title = title;
        this.startDate = startDate;
        this.place = place;
        this.description = description;
        this.imageLink = imageLink;
    }

    public Event() {

    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getStartDate() {
        return startDate;
    }

    @Override
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @Override
    public String getPlace() {
        return place;
    }

    @Override
    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Event{");
        sb.append("category='").append(category).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", startDate='").append(startDate).append('\'');
        sb.append(", place='").append(place).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", imageLink='").append(imageLink).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
