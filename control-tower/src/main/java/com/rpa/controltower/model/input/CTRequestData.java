package com.rpa.controltower.model.input;

import java.util.List;

public class CTRequestData {

    private List<SiteData> data;

    public CTRequestData() {

    }

    public CTRequestData(List<SiteData> data) {
        this.data = data;
    }

    public List<SiteData> getData() {
        return data;
    }

    public void setData(List<SiteData> data) {
        this.data = data;
    }
}
