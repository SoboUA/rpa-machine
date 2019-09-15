package com.rpa.controltower.model;

import com.rpa.controltower.model.input.SiteData;
import com.rpa.controltower.model.result.Event;

import java.util.List;

public class ResultObject {

    private SiteData siteData;
    private List<Event> eventList;


    public ResultObject() {
    }


//    public Map<Integer, String> convertToMap() {
//        List<String> listFields = Arrays.asList(item, quantity, skunumber, location, toWarehouse, fromWarehouse, order, status, failureReason);
//
//
//        return IntStream.range(0, listFields.size())
//                .boxed()
//                .filter(i -> StringUtils.isNotBlank(listFields.get(i)))
//                .collect(Collectors.toMap(i -> i, listFields::get));
//    }

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
