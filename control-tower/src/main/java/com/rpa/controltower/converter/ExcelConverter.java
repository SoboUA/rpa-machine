package com.rpa.controltower.converter;

import com.rpa.controltower.model.IEvent;
import com.rpa.controltower.model.ResultObject;
import com.rpa.controltower.util.ExcelUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ExcelConverter {
    public Workbook fillWorkbook(Workbook workbook, List<ResultObject> orders) {
        for (int i = 0; i < orders.size(); i++) {
            ResultObject resultObject = orders.get(i);
            Sheet workingSheet = workbook.createSheet(resultObject.getSiteData().getSiteId());

            //Hardcoded headers
            List<String> headers = Arrays.asList("Назва", "Опис", "Дата івенту", "Категорії", "Місце проведення", "Посилання на картинку");

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
