package com.rpa.controltower.model.ui;

import com.rpa.controltower.model.Category;

import java.util.List;
import java.util.Map;

public class Site {

//    public Integer getNumber() {
//        return number;
//    }
//
//    public void setNumber(Integer number) {
//        this.number = number;
//    }
//
//    private  Integer number;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<Category, String> getCategory() {
        return category;
    }

    public void setCategory(Map<Category, String> category) {
        this.category = category;
    }

    private Map<Category, String> category;


    private Integer id;
    private String name;
    private String url;
    private String description;


    public Site() {
    }

    public Site(Integer id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public Site(Integer id, String name, String url, Map<Category, String> categories) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.category = categories;
    }
//    public Site(Integer id, String name, String url, Map<Category,String> categories) {
//        this.number = id;
//        this.name = name;
//        this.url = url;
//        this.category = categories;
//    }

    @Override
    public String toString() {
        return "Site{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
