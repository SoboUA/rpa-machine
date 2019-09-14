package com.rpa.controltower.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class SiteData {

    private String site;
    private String simpleText;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateTo;

    public SiteData() {
    }

    public SiteData(String site, String simpleText, Date dateFrom, Date dateTo) {
        this.site = site;
        this.simpleText = simpleText;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getSimpleText() {
        return simpleText;
    }

    public void setSimpleText(String simpleText) {
        this.simpleText = simpleText;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    @Override
    public String toString() {
        return "SiteData{" +
                "site='" + site + '\'' +
                ", simpleText='" + simpleText + '\'' +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                '}';
    }
}
