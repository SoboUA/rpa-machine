package com.rpa.controltower.model;

import com.rpa.controltower.model.ui.Site;

import java.util.List;

public class SearchData {

    private Site site;
    private List<String> filters;
    private boolean sendEmail;

    public SearchData() {
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public List<String> getFilters() {
        return filters;
    }

    public void setFilters(List<String> filters) {
        this.filters = filters;
    }

    public boolean isSendEmail() {
        return sendEmail;
    }

    public void setSendEmail(boolean sendEmail) {
        this.sendEmail = sendEmail;
    }

    public SearchData(Site site, List<String> filters, boolean sendEmail) {
        this.site = site;
        this.filters = filters;
        this.sendEmail = sendEmail;
    }
}
