package com.rpa.controltower.converter.excel;

import com.rpa.controltower.model.result.IEvent;
import com.rpa.controltower.model.ResultObject;
import com.rpa.controltower.util.ExcelUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ExcelConverter {
    public Workbook fillWorkbook(Workbook workbook, List<ResultObject> orders) {
        for (int i = 0; i < orders.size(); i++) {
            ResultObject resultObject = orders.get(i);
            HashMap<String, String> sheetNames = new HashMap<>();
            sheetNames.put("1", "Lviv Online");
            sheetNames.put("2", "Gastroli");
            sheetNames.put("4", "Ticket Club");
            Sheet workingSheet = workbook.createSheet(sheetNames.get(resultObject.getSiteData().getSiteId()));


            ((XSSFSheet) workingSheet).setTabColor(Integer.valueOf(resultObject.getSiteData().getSiteId()));
            //Hardcoded headers
            List<String> headers = Arrays.asList("Назва",  "Дата івенту", "Категорії", "Місце проведення", "Посилання на картинку","Опис");

            Map<Integer, String> headersMap = IntStream.range(0, headers.size())
                    .boxed()
                    .filter(o -> StringUtils.isNotBlank(headers.get(o)))
                    .collect(Collectors.toMap(y -> y, headers::get));

            List<Map<Integer, String>> mapOrder = resultObject.getEventList().stream()
                    .map(IEvent::convertToMap)
                    .collect(Collectors.toList());
            mapOrder.add(0, headersMap);
            ExcelUtils.setDataRowMap(workingSheet, mapOrder, 0);

        }


        return workbook;
    }
}
