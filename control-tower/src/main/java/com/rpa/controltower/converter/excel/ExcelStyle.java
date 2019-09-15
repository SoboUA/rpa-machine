package com.rpa.controltower.converter.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import java.util.Objects;

public class ExcelStyle {

    public static void setBorder(CellStyle style) {
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
    }

    public static CellStyle getBoldStyle(Workbook workbook) {
        CellStyle style = getSimpleStyle(workbook);
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);

        return style;
    }

    public static CellStyle getSimpleStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        setBorder(style);
        style.setAlignment(HorizontalAlignment.CENTER);

        return style;
    }

    public static CellStyle getLeftAlignStyle(Workbook workbook) {
        CellStyle style = getSimpleStyle(workbook);
        style.setAlignment(HorizontalAlignment.LEFT);

        return style;
    }

    public static CellStyle getHeaderStyle(Workbook workbook) {
        XSSFCellStyle headerStyle = (XSSFCellStyle) workbook.createCellStyle();
        headerStyle.cloneStyleFrom(getSimpleStyle(workbook));
        Font font = workbook.createFont();
        font.setBold(true);
        headerStyle.setFont(font);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        return headerStyle;
    }

    public Workbook setStyle(Workbook workbook) {

        int allSheets = workbook.getNumberOfSheets();

        for (int k = 0; k < allSheets; k++) {

            Sheet sheet = workbook.getSheetAt(k);


            int lastRowNum = sheet.getLastRowNum();
            //Hardcoded column numbers
            int columnNumber = 5;


            DataFormatter dataFormat = new DataFormatter();

//        sheet.createFreezePane(0, 1);
            sheet.setAutoFilter(CellRangeAddress.valueOf("A1:F1"));

            CellStyle leftAlignLStyle = ExcelStyle.getLeftAlignStyle(workbook);
            CellStyle simpleStyle = ExcelStyle.getSimpleStyle(workbook);
            CellStyle headerStyle = ExcelStyle.getHeaderStyle(workbook);

            for (int i = 0; i <= lastRowNum; i++) {
                Row currentRow = Objects.nonNull(sheet.getRow(i)) ? sheet.getRow(i) : sheet.createRow(i);


                for (int j = 0; j <= columnNumber; j++) {

                    sheet.autoSizeColumn(j);


                    Cell currentCell = Objects.nonNull(currentRow.getCell(j)) ? currentRow.getCell(j) : currentRow.createCell(j);
                    CellStyle style = workbook.createCellStyle();
                    if (i == 0) {
                        currentRow.setHeight((short) 800);
                        currentCell.setCellStyle(headerStyle);
                    } else {
                        style.cloneStyleFrom(leftAlignLStyle);
                            style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
                        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                        currentCell.setCellStyle(style);
                    }

                }

            }
        }

        return workbook;
    }

}
