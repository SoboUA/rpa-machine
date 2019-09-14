package com.rpa.controltower.model.input;

import com.rpa.controltower.model.ui.Site;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class SiteData {

    private String siteId;
    private String siteUrl;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateTo;

    public SiteData() {
    }

    public SiteData(String site, String simpleText, Date dateFrom, Date dateTo) {
        this.siteId = site;
        this.siteUrl = simpleText;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }
    public SiteData(Site info,Date dateFrom,Date dateTo) {
        this.siteId = info.getId().toString();
        this.siteUrl = info.getUrl();
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
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
                "siteId='" + siteId + '\'' +
                ", siteUrl='" + siteUrl + '\'' +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                '}';
    }
}
