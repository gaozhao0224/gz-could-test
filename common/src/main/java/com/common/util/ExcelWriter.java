package com.common.util;

import com.common.context.StringPool;
import org.apache.poi.ooxml.POIXMLProperties;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * 
 * @since 3.0.0
 * @author zyl
 * @email zyl@zhangyl.com
 * @date 2019-10-18 19:46:22
 */
public class ExcelWriter {

	public static final String CHECK_INFO = "check_info";

	public static final String IMPORT_INFO = "import_info";

	public static String RESULT_FIELD = "导入结果";
	public static String REASON_FIELD = "错误原因";

	public static String RESULT_VALUE_NEW = "新增";
	public static String RESULT_VALUE_UPDATE = "更新";
	public static String RESULT_VALUE_FAILED = "失败";

	public static void write(OutputStream outputStream, String[] sheetNames,
			HashMap<String, ArrayList<HashMap<String, Object>>> excelDataMap) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();

		POIXMLProperties xmlProps = workbook.getProperties();
		POIXMLProperties.CoreProperties coreProps = xmlProps.getCoreProperties();
		coreProps.setCreator("fescotech");

		XSSFCellStyle headStyle = workbook.createCellStyle();
		XSSFFont headTitleFont = workbook.createFont();
		headTitleFont.setBold(true);
		headTitleFont.setFontName("Times New Roman");
		headStyle.setFont(headTitleFont);
		headStyle.setAlignment(HorizontalAlignment.CENTER);
		headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headStyle.setFillForegroundColor(IndexedColors.GOLD.getIndex());

		XSSFCellStyle textStyle = workbook.createCellStyle();
		XSSFDataFormat textformat = workbook.createDataFormat();
		textStyle.setDataFormat(textformat.getFormat(StringPool.AT));
		XSSFFont textFont = workbook.createFont();
		textFont.setFontName("宋体");
		textStyle.setFont(textFont);

		XSSFDataFormat format = workbook.createDataFormat();
		XSSFCellStyle dateStyle = workbook.createCellStyle();
		dateStyle.setDataFormat(format.getFormat(StringPool.DATE));

		XSSFCellStyle numberStyle = workbook.createCellStyle();
		numberStyle.setAlignment(HorizontalAlignment.RIGHT);
		numberStyle.setDataFormat(workbook.createDataFormat().getFormat("0.00"));
		XSSFFont numberFont = workbook.createFont();
		numberFont.setFontName("Times New Roman");
		numberStyle.setFont(numberFont);

		XSSFCellStyle intStyle = workbook.createCellStyle();
		intStyle.setAlignment(HorizontalAlignment.LEFT);
		intStyle.setFont(numberFont);

		XSSFCellStyle checkInfoStyle = workbook.createCellStyle();
		XSSFFont checkInfoFont = workbook.createFont();
		checkInfoFont.setColor(Font.COLOR_RED);
		checkInfoFont.setBold(true);
		checkInfoStyle.setFont(checkInfoFont);

		for (String sheetName : sheetNames) {
			ArrayList<HashMap<String, Object>> sheetMapList = excelDataMap.get(sheetName);

			Sheet sheet = workbook.createSheet(sheetName);
			sheet.createFreezePane(0, 1, 0, 1); // 冻结第一行
//			sheet.createFreezePane(1, 0, 1, 0); // 冻结第一列

			HashMap<Integer, Integer> width_map = new HashMap<Integer, Integer>();

			HashMap<String, Object> titleMap = sheetMapList.get(0);

			String[] headTitles = new String[titleMap.size()];
			for (Entry<String, Object> entry : titleMap.entrySet()) {
				headTitles[Integer.valueOf(entry.getKey())] = entry.getValue().toString();
			}

			Row row = sheet.createRow(0);
			for (int i = 0; i < headTitles.length; i++) {
				Cell cell = row.createCell(i, CellType.STRING);
				cell.setCellValue(headTitles[i]);
				cell.setCellStyle(headStyle);
				width_map.put(i, headTitles[i].getBytes().length);
			}

			for (int i = 1; i < sheetMapList.size(); i++) {
				row = sheet.createRow(i);
				HashMap<String, Object> rowData = sheetMapList.get(i);
				for (int j = 0; j < headTitles.length; j++) {
					Cell cell = row.createCell(j);
					String headTitle = headTitles[j];
					Object value = rowData.get(headTitle);

					if (value instanceof String) {
						cell = row.createCell(j, CellType.STRING);
						cell.setCellValue((String) value);
						if (IMPORT_INFO.equalsIgnoreCase(headTitle)) {
							cell.setCellStyle(checkInfoStyle);
						} else {
							cell.setCellStyle(textStyle);
						}
						if (value != null && (width_map.get(j) == null
								|| width_map.get(j) < value.toString().getBytes().length)) {
							width_map.put(j, value.toString().getBytes().length);
						}
					} else if (value instanceof Double) {
						cell = row.createCell(j, CellType.NUMERIC);
						if (IMPORT_INFO.equalsIgnoreCase(headTitle)) {
							cell.setCellStyle(checkInfoStyle);
						} else {
							cell.setCellStyle(numberStyle);
						}
						cell.setCellValue((Double) value);
					} else if (value instanceof Integer) {
						cell = row.createCell(j, CellType.NUMERIC);
						if (IMPORT_INFO.equalsIgnoreCase(headTitle)) {
							cell.setCellStyle(checkInfoStyle);
						} else {
							cell.setCellStyle(intStyle);
						}
						cell.setCellValue((Integer) value);
					} else if (value instanceof Date) {
						cell = row.createCell(j, CellType.NUMERIC);
						if (IMPORT_INFO.equalsIgnoreCase(headTitle)) {
							cell.setCellStyle(checkInfoStyle);
						} else {
							cell.setCellValue((Date) value);
						}
						cell.setCellStyle(dateStyle);
					} else {
						cell = row.createCell(j, CellType.STRING);
						cell.setCellValue(value == null ? null : value.toString());
						if (IMPORT_INFO.equalsIgnoreCase(headTitle)) {
							cell.setCellStyle(checkInfoStyle);
						} else {
							cell.setCellStyle(textStyle);
						}
						if (value != null && (width_map.get(j) == null
								|| width_map.get(j) < value.toString().getBytes().length)) {
							width_map.put(j, value.toString().getBytes().length);
						}
					}

				}
			}
			for (int i = 0; i < headTitles.length; i++) {
				if (width_map.get(i) != null) {
					int columnWidth = (width_map.get(i) + 1);
					if (columnWidth > 20) {
						columnWidth = 20;
					}
					sheet.setColumnWidth(i, columnWidth * 256);
				}
			}

		}
		workbook.write(outputStream);

		workbook.close();
	}

}
