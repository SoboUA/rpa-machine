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
            String name = node.findValuesAsText("title").get(i);
            String description = node.findValuesAsText("description").get(i);
            String startDate = node.findValuesAsText("startDate").get(i);

            Event event = new Event(name, description, startDate);
            eventList.add(event);
        }

        return eventList;
    }
}
