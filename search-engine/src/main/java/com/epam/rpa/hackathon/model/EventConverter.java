package com.epam.rpa.hackathon.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EventConverter {
    public EventConverter() {
    }

    public List<Event> convertToEventList(String eventJson){
        List<Event> eventList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = null;
        try {
            node = mapper.readTree(eventJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int numberOfEvents  = node.size();

        for (int i = 0; i < numberOfEvents; i++){
            String category = node.findValuesAsText("category").get(i);
            String title = node.findValuesAsText("title").get(i);
            String startDate = node.findValuesAsText("startDate").get(i);
            String place = node.findValuesAsText("place").get(i);
            String description = node.findValuesAsText("description").get(i);
            String imageLink = node.findValuesAsText("imageLink").get(i);

            Event event = new Event(category, title, startDate,  place, description, imageLink);
            eventList.add(event);
        }

        return eventList;
    }
}
