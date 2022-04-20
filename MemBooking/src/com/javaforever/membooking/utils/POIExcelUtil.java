package com.javaforever.membooking.utils;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

public final class POIExcelUtil {
	public static void exportExcelSheet(OutputStream out,String sheetName,List<String> headers, List<List<String>> contents) throws Exception{
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(sheetName);
		HSSFRow row;
		HSSFCell cell;
		
		short colorIndex = 10;
		HSSFPalette palette = wb.getCustomPalette();
		Color rgb = Color.YELLOW;
		short bgIndex = colorIndex ++;
		palette.setColorAtIndex(bgIndex, (byte) rgb.getRed(), (byte) rgb.getGreen(), (byte) rgb.getBlue());
		short bdIndex = colorIndex ++;
		rgb = Color.BLACK;
		palette.setColorAtIndex(bdIndex, (byte) rgb.getRed(), (byte) rgb.getGreen(), (byte) rgb.getBlue());
		
		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		//bdIndex 边框颜色下标值
		cellStyle.setBottomBorderColor(bdIndex);
		cellStyle.setLeftBorderColor(bdIndex);
		cellStyle.setRightBorderColor(bdIndex);
		cellStyle.setTopBorderColor(bdIndex);
		
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		
		HSSFCellStyle cellHeaderStyle = wb.createCellStyle();
		cellHeaderStyle.cloneStyleFrom(cellStyle);
		
		cellHeaderStyle.setFillForegroundColor(bgIndex); //bgIndex 背景颜色下标值
		cellHeaderStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		writeRow(wb,sheet,bgIndex,bdIndex,1,cellHeaderStyle,headers);

		for(int i = 0; i < contents.size(); i++) {
			writeRow(wb,sheet,bgIndex,bdIndex,i+2,cellStyle,contents.get(i));
		}
		
		//创建表格之后设置行高与列宽
		for(int i = 1; i < contents.size()+2; i++) {
			row = sheet.getRow(i);
			row.setHeightInPoints(30);
		}
		for(int j = 1; j < headers.size()+1; j++) {
			sheet.setColumnWidth(j, MSExcelUtil.pixel2WidthUnits(120));
		}
		wb.write(out);
	}
	
	protected static void writeRow(HSSFWorkbook wb,HSSFSheet sheet,short bgIndex,short bdIndex,int rowIndex,HSSFCellStyle cellStyle,List<String> data) {
		HSSFRow row = sheet.createRow(rowIndex);//创建表格行
		for(int j = 0; j < data.size(); j++) {
			Cell cell = row.createCell(j+1);//根据表格行创建单元格
			cell.setCellStyle(cellStyle);
			cell.setCellValue(String.valueOf(StringUtil.nullTrim(data.get(j))));
		}
	}

}
