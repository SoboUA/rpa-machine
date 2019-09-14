package com.rpa.controltower.model.ui;

import com.rpa.controltower.model.ExportType;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class ScrappingFormData {
    private List<Site> outputSites;
    private String email;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateTo;


    private List<ExportType> exports;

    public List<Site> getOutputSites() {
        return outputSites;
    }

    public void setOutputSites(List<Site> outputSites) {
        this.outputSites = outputSites;
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

    public List<ExportType> getExports() {
        return exports;
    }

    public void setExports(List<ExportType> exports) {
        this.exports = exports;
    }

    @Override
    public String toString() {
        return "ScrappingFormData{" +
                "outputSites=" + outputSites +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", exports=" + exports +
                '}';
    }
}
