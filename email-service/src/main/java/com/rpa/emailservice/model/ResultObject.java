package com.rpa.emailservice.model;

import java.util.List;

public class ResultObject {

    private SiteData siteData;
    private List<Event> eventList;


    public ResultObject() {
    }

    public SiteData getSiteData() {
        return siteData;
    }

    public void setSiteData(SiteData siteData) {
        this.siteData = siteData;
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
                "siteData=" + siteData +
                ", eventList=" + eventList +
                '}';
    }
}
