package com.common.util;

import com.common.context.StringPool;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Excel读取器
 * 
 * @since 1.0.0
 * @author zyl
 * @email zyl@zhangyl.com
 * @date 2019-12-11 14:18:16
 */
public class ExcelReader {

    private ExcelReader() {
        throw new IllegalStateException(StringPool.UTILITY_CLASS);
    }

	/**
	 * 读取Excel，返回sheet名为key，行数据ArrayList为value的HashMap<br>
	 * 行数据ArrayList第1行:列号=表头标题，之后的行:标题=值
	 * 
	 * @author zyl
	 * @date 2019-12-11 14:18:16
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static Map<String, List<Map<String, Object>>> readExcel(InputStream inputStream)	throws IOException {
		Map<String, List<Map<String, Object>>> dataMap = new HashMap<>();
		Workbook workbook = WorkbookFactory.create(inputStream);
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {// 获取每个Sheet表
			Sheet sheet = workbook.getSheetAt(i);
			String sheetName = sheet.getSheetName().trim();
			List<Map<String, Object>> dataList = new ArrayList<>();

			// 获取第一行数据
			int firstRowNum = sheet.getFirstRowNum();
			Row firstRow = sheet.getRow(firstRowNum);
			if (firstRow == null) {
				throw new IOException("“" + sheetName + "”页签的第1行没有读取到任何数据！");
			}

			Map<String, Object> titleMap = new HashMap<>();
			int firstRowLastColunm = firstRow.getLastCellNum();
			String[] cols = new String[firstRowLastColunm];
			for (int j = 0; j < firstRowLastColunm; j++) {
				Cell cell = firstRow.getCell(j);
				cols[j] = cell.getStringCellValue();

				titleMap.put(j + "", cols[j]);
			}
			dataList.add(titleMap);

			for (int k = 1; k < sheet.getPhysicalNumberOfRows(); k++) {// 获取每行
				Row row = sheet.getRow(k);
				if (row != null) { // 不是空行
					Map<String, Object> map = new HashMap<>();
					Object cellValue = null;
					boolean isNullRow = true;
					for (int l = 0; l < firstRowLastColunm; l++) {// 获取每个单元格
						Cell cell = row.getCell(l);
						cellValue = null;
						if (cell != null) {
							cellValue = getCellValue(cell);
							// 有一列有值即认为不是空行
							if (isNullRow && cellValue != null && cellValue.toString().trim().length() > 0) {
								isNullRow = false;
							}
						}
						map.put(cols[l], cellValue);
					}
					// 有空行则不再往下
					if (isNullRow) {
						break;
					}
					dataList.add(map);
				}
			}

			dataMap.put(sheetName, dataList);
		}

		return dataMap;
	}

	private static Object getCellValue(Cell cell) {
		Object cellValue = null;

		if (cell == null) {
			return cellValue;
		}

		switch (cell.getCellType()) {
		case NUMERIC: // 数字
			if (DateUtil.isCellDateFormatted(cell)) {
				Date date = DateUtil.getJavaDate(cell.getNumericCellValue());
				SimpleDateFormat sdf = new SimpleDateFormat(StringPool.DATE);
				sdf.setTimeZone(TimeZone.getDefault());
				cellValue = sdf.format(date);
			} else {
				cellValue = cell.getNumericCellValue();
			}
			break;
		case STRING: // 字符串
			cellValue = cell.getStringCellValue().replaceAll("[\\s\\u00A0]+", "").trim();
			break;
		case BOOLEAN: // 布尔
			Boolean booleanValue = cell.getBooleanCellValue();
			cellValue = booleanValue.toString();
			break;
		case BLANK: // 空值
			cellValue = null;
			break;
		case FORMULA: // 公式
			cellValue = cell.getCellFormula();
			break;
		case ERROR: // 故障
			break;
		default:
			break;
		}
		return cellValue;
	}

}