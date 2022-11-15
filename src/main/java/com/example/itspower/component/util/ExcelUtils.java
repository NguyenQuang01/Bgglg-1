package com.example.itspower.component.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.math.BigDecimal;

public class ExcelUtils {

    public static Workbook getWorkbook(InputStream inputStream, String fileName)
            throws IOException {
        ZipSecureFile.setMinInflateRatio(0);//bỏ giới hạn khi imp file excel quá lớn
        Workbook workbook;

        if (fileName.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (fileName.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    /**
     * Get value of cell, convert to String
     */
    public static Object getCellValue(Workbook workbook, Cell cell, Type type) {
        CellType cellType = cell.getCellType();
        switch (cellType) {
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case _NONE:
            case BLANK:
                return null;
            case FORMULA:
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                try {
                    return getCellValue(workbook, evaluator.evaluateInCell(cell), type);
                } catch (Exception e) {
                    //Bỏ qua lỗi file excel do sai công thức trên excel
                    return null;
                }
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                    String content = BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString();
                    if (StringUtils.isNotEmpty(content)) {
                        String key = content.substring(content.length() - 2);
                        if (key.equals(".0")) {
                            content = content.replace(".0", "");
                        }
                    }

                    return TextUtils.getValue(type, content);

                }
            case STRING:
                return cell.getStringCellValue();
            case ERROR:
                return null;
            default:
                return null;
        }
    }

    public static String getCellStringValue(Workbook workbook, Cell cell) {
        CellType cellType = cell.getCellType();
        String content = null;
        switch (cellType) {
            case BOOLEAN:
                content = String.valueOf(cell.getBooleanCellValue());
                break;
            case _NONE:
            case BLANK:
                break;
            case FORMULA:
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                try {
                    content = String.valueOf(evaluator.evaluate(cell).getNumberValue());
                } catch (Exception e) {
                    //Bỏ qua lỗi file excel do sai công thức trên excel
                    break;
                }
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    content = DateUtils.formatDateTime(cell.getDateCellValue());
                } else {
                    content = new BigDecimal(String.valueOf(cell.getNumericCellValue())).toPlainString();
                    if (StringUtils.isNotEmpty(content)) {
                        String key = content.substring(content.length() - 2);
                        if (key.equals(".0")) {
                            content = content.replace(".0", "");
                        }
                    }
                }
                break;
            case STRING:
                content = cell.getStringCellValue();
                break;
            case ERROR:
                break;
        }
        return content;
    }
}
