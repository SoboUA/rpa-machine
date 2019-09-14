package com.rpa.controltower.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;

import java.util.*;

public class ExcelUtils {
    private static DataFormatter formatter = new DataFormatter();
    public static final int SAFETY_COUNTER = 32000;


    private static int calculateFirstEmptyRowIndex(Sheet sheet) {
        int numberOfRows = Math.min(sheet.getLastRowNum(), SAFETY_COUNTER);

        for (int i = numberOfRows; i > -1; i--) {
            Row row = sheet.getRow(i);
            if (!isRowBlank(row)) {
                return i;
            }
        }
        return 0;
    }


    public static void setDataRowMap(Sheet sheet, List<Map<Integer, String>> listData, int startRowIndex) {
//        Preconditions.checkArgument(startRowIndex > 0);

        for (Map<Integer, String> dataMap : listData) {
            Row currentRow = sheet.createRow(startRowIndex++);
            dataMap.entrySet().stream()
                    .filter(entry -> StringUtils.isNotBlank(entry.getValue()))
                    .forEach(data -> currentRow.createCell(data.getKey()).setCellValue(data.getValue()));

        }

    }

    public static List<Map<Integer, String>> getAllDataRowsMap(Sheet sheet, int startRowIndex, int lastColumn) {
        List<Map<Integer, String>> result = new ArrayList<>();

        int rowIndex = startRowIndex;
        int numberOfRows = Math.min(calculateFirstEmptyRowIndex(sheet), SAFETY_COUNTER);

        while (rowIndex <= numberOfRows) {
            Row currentRow = sheet.getRow(rowIndex++);

            if (Objects.isNull(currentRow)) {
                continue;
            }

            result.add(readRowValues(currentRow, lastColumn));


        }

        return result;
    }

    public static Map<Integer, String> readRowValues(Row row, int lastColumn) {
        Map<Integer, String> result = new HashMap<>();
        if (row == null || row.getLastCellNum() == -1) {
            return result;
        }

        for (int currentColumn = 0; currentColumn <= lastColumn; currentColumn++) {
            Cell cell = row.getCell(currentColumn);
            String value = formatter.formatCellValue(cell);
            if (!StringUtils.isBlank(value) && !value.startsWith("=")) {
                result.put(currentColumn, value);
            }

        }
        return result;
    }

    private static boolean isRowBlank(Row r) {
        boolean ret = true;

        /*
         * If a row is null, it must be blank.
         */
        if (r != null) {
            Iterator<Cell> cellIterator = r.cellIterator();
            /*
             * Iterate through all cells in a row.
             */
            while (cellIterator.hasNext()) {
                /*
                 * If one of the cells in given row contains data, the row is
                 * considered not blank.
                 */
                if (!isCellBlank(cellIterator.next())) {
                    ret = false;
                    break;
                }
            }
        }

        return ret;
    }

    private static boolean isCellBlank(Cell c) {
        return (c == null || c.getCellType() == CellType.BLANK);
    }


}
