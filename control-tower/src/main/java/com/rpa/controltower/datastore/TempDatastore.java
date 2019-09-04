package com.rpa.controltower.datastore;

import com.rpa.controltower.model.ResultObject;

import java.util.ArrayList;
import java.util.List;

public class TempDatastore {

    private List<ResultObject> resultObjects;

    public TempDatastore() {
        resultObjects = new ArrayList<>();
    }

    public void append(ResultObject resultObject) {
        System.out.println("Appending : " + resultObject.toString());
        resultObjects.add(resultObject);
    }

    public List<ResultObject> getResultObjects() {
        return resultObjects;
    }

    public void clear(){
        int size = resultObjects.size();
        System.out.println("Cleaned " + size + " records");
        resultObjects.clear();
    }
}
