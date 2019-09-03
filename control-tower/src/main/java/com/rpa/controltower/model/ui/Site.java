package com.rpa.controltower.model.ui;

public class Site {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesctription() {
        return desctription;
    }

    public void setDesctription(String desctription) {
        this.desctription = desctription;
    }

    private Integer id;
    private String name;
    private String url;
    private String desctription;

    public Site() {
    }

    public Site(Integer id,String name, String url, String desctription) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.desctription = desctription;
    }
}
