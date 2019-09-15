package com.epam.rpa.hackathon.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class SiteData {

    private String siteId;
    private String sendUrl;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateTo;

    public SiteData() {
    }

    public SiteData(String siteId, String sendUrl, LocalDate dateFrom, LocalDate dateTo) {
        this.siteId = siteId;
        this.sendUrl = sendUrl;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getSendUrl() {
        return sendUrl;
    }

    public void setSendUrl(String sendUrl) {
        this.sendUrl = sendUrl;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    @Override
    public String toString() {
        return "SiteData{" +
                "siteId='" + siteId + '\'' +
                ", sendUrl='" + sendUrl + '\'' +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                '}';
    }
}
