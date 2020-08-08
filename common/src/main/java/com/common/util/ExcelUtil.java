package com.common.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.exception.BusinessException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtil {

    private static final String DEFAULT_SHEET_NAME = "sheet";


    public static Workbook createSheet(Workbook workbook, Map<String, String> data, JSONArray array)
            throws BusinessException {

        if (workbook == null) {
            throw new BusinessException("workbook is null");
        }
        String sheetName = DEFAULT_SHEET_NAME + (workbook.getNumberOfSheets() + 1);

        Sheet sheet = workbook.createSheet(sheetName);
        int columnSize = data.size();
        fillTableData(workbook, data, sheet, columnSize);

        outPutBody(workbook, sheet, data, array);

        return workbook;
    }


    private static void outPutBody(Workbook wb, Sheet sheet, Map<String, String> data, JSONArray array)
            throws BusinessException {

        sheet.setDefaultColumnWidth((short) 12);
        // 超过四千条 style会报错
        CellStyle titleCellStyle = wb.createCellStyle();

        List<HashMap> map = JSONArray.parseArray(array.toString(), HashMap.class);
        String[] key = data.keySet().toArray(new String[0]);
        for (int index = 0; index < array.size(); index++) {
            Map<String, Object> hm = map.get(index);
            final Row row = sheet.createRow(index + 1);
            int nLineNo = 0;
            int errorPos = 99;
            for (int temp = 0; temp < key.length; temp++) {
                String value = "";
                Cell cell1 = null;
                if(hm.get(key[temp]) != null){
                    value = hm.get(key[temp]).toString();
                }
                // 其他的按字符类型创建
                cell1 = row.createCell((short) (temp + nLineNo));
                cell1.setCellValue(String.valueOf(value));
                setCellStyle4Body(titleCellStyle, cell1, wb, errorPos, nLineNo, HorizontalAlignment.LEFT);
            }
        }
        sheet.autoSizeColumn((short) 25);
    }

    private static Cell setCellStyle4Body(CellStyle titleCellStyle, final Cell cell, final Workbook wb,
                                          final int intErrorPos, final int curentLineNO, HorizontalAlignment alignment) {
        DataFormat dataFormat = wb.createDataFormat();
        titleCellStyle.setDataFormat(dataFormat.getFormat("@"));
        titleCellStyle.setBorderBottom(BorderStyle.THIN);
        titleCellStyle.setBorderLeft(BorderStyle.THIN);
        titleCellStyle.setBorderRight(BorderStyle.THIN);
        titleCellStyle.setBorderTop(BorderStyle.THIN);
        titleCellStyle.setAlignment(alignment);
        if (curentLineNO == intErrorPos) {
            final Font font = wb.createFont();
            font.setColor(Font.COLOR_RED);
            titleCellStyle.setFont(font);
        }
        cell.setCellStyle(titleCellStyle);
        return cell;
    }

    private static void fillTableData(Workbook workbook, Map<String, String> data, Sheet sheet, int columnSize)
            throws BusinessException {
        Row row = sheet.createRow(0);
        Cell cell = null;
        String[] rowdata = getData(data);

        for (int j = 0; j < columnSize; j++) {
            cell = row.createCell(j);
            String celldata = (j < rowdata.length && rowdata[j] != null) ? rowdata[j] : null;
            setCellValue(cell, celldata);
            setCellStyle4Head(cell, workbook);
            int columnwidth = celldata.getBytes().length;
            columnwidth = columnwidth > 50 ? 50 : columnSize;
            sheet.setColumnWidth(j, columnwidth * 2 * 256);
        }

    }

    private static String[] getData(Map<String, String> data) {
        String[] key = data.keySet().toArray(new String[0]);
        String[] str = new String[key.length];
        for (int i = 0; i < key.length; i++) {
            str[i] = data.get(key[i]);
        }
        return str;
    }


    private static void setCellValue(Cell cell, Object value) {
        if (value == null)
            return;

        if (value instanceof Boolean) {
            cell.setCellValue(((Boolean) value).booleanValue());
        }

        else if (value instanceof Double) {
            cell.setCellValue(((Double) value).doubleValue());
        } else if (value instanceof Number) {
            cell.setCellValue(((Number) value).doubleValue());
        }

        else if (value instanceof Date) {
            cell.setCellValue((Date) value);
        }

        else {
            cell.setCellValue(value.toString());
        }
    }

    private static Cell setCellStyle4Head(Cell cell, Workbook wb) {
        CellStyle titleCellStyle = wb.createCellStyle();
        DataFormat dataFormat = wb.createDataFormat();
        titleCellStyle.setDataFormat(dataFormat.getFormat("@"));
        titleCellStyle.setBorderBottom(BorderStyle.THIN);
        titleCellStyle.setBorderLeft(BorderStyle.THIN);
        titleCellStyle.setBorderRight(BorderStyle.THIN);
        titleCellStyle.setBorderTop(BorderStyle.THIN);
        cell.setCellStyle(titleCellStyle);
        return cell;
    }

    /**
     * 读取excel
     * @param file
     * @throws BusinessException
     */
    public static JSONArray readExcel(Map<String, String> columns, CommonsMultipartFile file) throws BusinessException {

        JSONArray array = new JSONArray();
        try {
            InputStream inputStream = file.getInputStream();
            Workbook workbook = WorkbookFactory.create(inputStream);
            for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
                Sheet sheet = workbook.getSheetAt(numSheet);// 得到工作表
                if (sheet == null) {
                    continue;
                }
                // 循环行 因为表头行是第0行，所以从0开始循环
                Row rowHead = sheet.getRow(0);
                for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                    JSONObject bean = new JSONObject();
                    Row row = sheet.getRow(rowNum);// 得到行
                    short s = row.getLastCellNum();// 得到此行有多少列
                    for (int i = 0; i < s; i++) {
                        Cell cell = row.getCell(i);// 得到单元格（每行）
                        if(cell == null){
                            continue;
                        }
                        Cell cellHead = rowHead.getCell(i);
                        String feild = "";// 得到表头属性名称
                        if(cellHead.getCellTypeEnum() == CellType.STRING) {
                            feild = cellHead.getRichStringCellValue().getString();
                        }
                        String value = getCellVal(cell);
                        bean.put(columns.get(feild), value);
                    }
                    // 把所有属性值添加完成后装入list中
                    array.add(bean);
                }
            }
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        return array;
    }

    //获取表数据
    public static String getCellVal(Cell cell) {
        String value;
        DecimalFormat df = new DecimalFormat("0");
        switch (cell.getCellTypeEnum()) {
            case STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case NUMERIC:
                value = df.format(cell.getNumericCellValue());
                break;
            case BOOLEAN:
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            case BLANK:
                value = "";
                break;
            default:
                value = cell.toString();
                break;
        }
        return value;
    }
}
