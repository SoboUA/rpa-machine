package com.rpa.controltower.model;

import java.util.List;

public class CTRequestData {

    private List<SearchData> data;

    public CTRequestData() {

    }

    public CTRequestData(List<SearchData> data) {
        this.data = data;
    }

    public List<SearchData> getData() {
        return data;
    }

    public void setData(List<SearchData> data) {
        this.data = data;
    }
}
