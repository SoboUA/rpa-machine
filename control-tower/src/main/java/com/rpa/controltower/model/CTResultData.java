package com.rpa.controltower.model;

import java.util.List;

public class CTResultData {

    private List<ResultObject> resultObjectList;

    public CTResultData() {
    }

    public CTResultData(List<ResultObject> resultObjectList) {
        this.resultObjectList = resultObjectList;
    }

    public List<ResultObject> getResultObjectList() {
        return resultObjectList;
    }

    public void setResultObjectList(List<ResultObject> resultObjectList) {
        this.resultObjectList = resultObjectList;
    }
}
