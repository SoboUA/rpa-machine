package com.rpa.controltower.datastore;

import com.rpa.controltower.model.ResultObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TempDatastore {

    private List<ResultObject> resultObjects;

    public TempDatastore() {
        resultObjects = new ArrayList<>();
    }

    public void append(ResultObject resultObject){
        System.out.println("Appending : " + resultObject.toString());
        resultObjects.add(resultObject);
    }

    public List<ResultObject> getResultObjects() {
        return resultObjects;
    }
}
