package com.epam.rpa.hackathon.model;

public class Event implements IEvent{

    private String name;
    private String description;
    private String date;

    public Event(String name, String description, String date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

//    public Map<Integer, String> convertToMap() {
//        List<String> listFields = Arrays.asList(name,description,date);
//
//
//        return IntStream.range(0, listFields.size())
//                .boxed()
//                .filter(i -> StringUtils.isNotBlank(listFields.get(i)))
//                .collect(Collectors.toMap(i -> i, listFields::get));
//    }

    public Event() {
    }

    public String getName() {
        return name;
    }

    public void setTitle(String name) {
        this.name = name;
    }

    @Override
    public String getCategory() {
        return null;
    }

    @Override
    public void setCategory(String category) {

    }

    @Override
    public String getTitle() {
        return null;
    }


    @Override
    public String getStartDate() {
        return null;
    }

    @Override
    public void setStartDate(String startDate) {

    }

    @Override
    public String getPlace() {
        return null;
    }

    @Override
    public void setPlace(String place) {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setImageLink(String imageLink) {

    }

    @Override
    public String getImageLink() {
        return null;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
