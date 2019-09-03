package com.rpa.controltower.model;

import com.rpa.controltower.model.ui.Site;

import java.util.List;

public class ResultObject {

    private Site site;
    private List<Event> eventList;


    public ResultObject() {
    }

    public ResultObject(Site site, List<Event> eventList) {
        this.site = site;
        this.eventList = eventList;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    @Override
    public String toString() {
        return "ResultObject{" +
                "site=" + site +
                ", eventList=" + eventList +
                '}';
    }
}
