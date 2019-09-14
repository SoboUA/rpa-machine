package com.rpa.controltower.converter;

import com.rpa.controltower.model.Event;
import com.rpa.controltower.model.ResultObject;
import com.rpa.controltower.util.ExcelUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExcelConverter {
    public Workbook fillWorkbook(Workbook workbook, List<ResultObject> orders) {
        for (int i = 0; i < orders.size(); i++) {
            ResultObject resultObject = orders.get(i);
            Sheet workingSheet = workbook.createSheet(resultObject.getSiteData().getSite());


            List<Map<Integer, String>> mapOrder = resultObject.getEventList().stream()
                    .map(Event::convertToMap)
                    .collect(Collectors.toList());

            ExcelUtils.setDataRowMap(workingSheet, mapOrder, 1);

        }



        return workbook;
    }
}
