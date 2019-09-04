package com.rpa.controltower.controller;

import com.rpa.controltower.datastore.TempDatastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyTestController {

    @Autowired
    private TempDatastore datastore;

    @RequestMapping(value = "/search/api/getSearchResult")
    public TempDatastore getSearchResultViaAjax() {

        return datastore;

    }

}
