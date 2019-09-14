package com.rpa.controltower.model.ui;

import com.rpa.controltower.model.Category;

import java.util.List;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    private List<Category> category;



    private Integer id;
    private String name;
    private String url;
    private String description;



    public Site() {
    }

    public Site(Integer id,String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public Site(Integer id, String name, String url, List<Category> categories) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.category = categories;
    }

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
