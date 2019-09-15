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

        System.out.println("Number : " + numberOfEvents);
        System.out.println("Node : " + node.toString());

       /* for (JsonNode nod1 : node) {
            if(nod1!=null){
                String category = nod1.get("category").textValue();
                String title = nod1.get("title").textValue();
                String startDate = nod1.get("startDate").textValue();
                String place = nod1.get("place").textValue();
                String description = nod1.get("description").textValue();
                String imageLink = nod1.get("imageLink").textValue();

                Event event = new Event(category, title, startDate,  place, description, imageLink);
                eventList.add(event);
            }
        }*/
        for (int i = 0; i < numberOfEvents ; i++){
            String category = node.findValuesAsText("category").get(i);
            String title = node.findValuesAsText("title").get(i);
            String startDate = node.findValuesAsText("startDate").get(i);
            String place = node.findValuesAsText("place").get(i);
            String description = node.findValuesAsText("description").get(i);

            System.out.println("Before imageLink");

            String imageLink = node.findValuesAsText("imageLink").get(i);

            Event event = new Event(category, title, startDate,  place, description, imageLink);
            eventList.add(event);
        }

        return eventList;
    }
}
