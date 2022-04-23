package com.khoaluantotnghiep.ultis;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;

/**
 * Export Excel common methods
 * 
 * @version 1.0
 * 
 * @author wangcp
 *
 */
@SuppressWarnings("deprecation")
public class ExportExcel {

	// Title of the displayed export table
	private String title;
	// Column name of export table
	private String[] rowName;

	private List<Object[]> dataList = new ArrayList<Object[]>();

	HttpServletResponse response;

	// Constructor, passing in data to export
	public ExportExcel(String title, String[] rowName, List<Object[]> dataList, HttpServletResponse response) {
		this.dataList = dataList;
		this.rowName = rowName;
		this.title = title;
		this.response = response;
	}

	/*
	 * Export data
	 */
	public void export() throws Exception {
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(); // Create workbook object
			HSSFSheet sheet = workbook.createSheet(title); // Create sheet

			// Generate table header row
			HSSFRow rowm = sheet.createRow(0);
			HSSFCell cellTiltle = rowm.createCell(0);

			// sheet style definition [getColumnTopStyle()/getStyle() are all user-defined
			// methods - below - extensible]
			HSSFCellStyle columnTopStyle = this.getColumnTopStyle(workbook);// Get column header style object
			HSSFCellStyle style = this.getStyle(workbook); // Cell style object

			sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (rowName.length - 1)));
			cellTiltle.setCellStyle(columnTopStyle);
			cellTiltle.setCellValue(title);

			// Define the number of columns required
			int columnNum = rowName.length;
			HSSFRow rowRowName = sheet.createRow(2); // Create row at index 2 (second row from top row)

			// Set column headers to cells in sheet
			for (int n = 0; n < columnNum; n++) {
				HSSFCell cellRowName = rowRowName.createCell(n); // Create cells corresponding to the number of column
																	// headers
				cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING); // Set the data type of the column header cell
				HSSFRichTextString text = new HSSFRichTextString(rowName[n]);
				cellRowName.setCellValue(text); // Set the value of the column header cell
				cellRowName.setCellStyle(columnTopStyle); // Set column header cell style
			}

			// Set the queried data to the cell corresponding to the sheet
			for (int i = 0; i < dataList.size(); i++) {

				Object[] obj = dataList.get(i);// Traverse each object
				HSSFRow row = sheet.createRow(i + 3);// Number of rows required to create

				for (int j = 0; j < obj.length; j++) {
					HSSFCell cell = null; // Set cell data type
					if (j == 0) {
						cell = row.createCell(j, HSSFCell.CELL_TYPE_NUMERIC);
						cell.setCellValue(i + 1);
					} else {
						cell = row.createCell(j, HSSFCell.CELL_TYPE_STRING);
						if (!"".equals(obj[j]) && obj[j] != null) {
							cell.setCellValue(obj[j].toString()); // Set cell value
						}
					}
					cell.setCellStyle(style); // Set cell style
				}
			}
			// Let the column width automatically adapt to the exported column length
			for (int colNum = 0; colNum < columnNum; colNum++) {
				int columnWidth = sheet.getColumnWidth(colNum) / 256;
				for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
					HSSFRow currentRow;
					// The current row has not been used
					if (sheet.getRow(rowNum) == null) {
						currentRow = sheet.createRow(rowNum);
					} else {
						currentRow = sheet.getRow(rowNum);
					}
					if (currentRow.getCell(colNum) != null) {
						HSSFCell currentCell = currentRow.getCell(colNum);
						if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
							int length = currentCell.getStringCellValue().getBytes().length;
							if (columnWidth < length) {
								columnWidth = length;
							}
						}
					}
				}
				if (colNum == 0) {
					sheet.setColumnWidth(colNum, (columnWidth - 2) * 256);
				} else {
					sheet.setColumnWidth(colNum, (columnWidth + 10) * 256);
				}
			}

			if (workbook != null) {
				try {
					String fileName = "Excel-" + String.valueOf(System.currentTimeMillis()).substring(4, 13) + ".xls";
					String headStr = "attachment; filename=\"" + fileName + "\"";
					response.setContentType("APPLICATION/OCTET-STREAM");
					response.setHeader("Content-Disposition", headStr);
					OutputStream out = response.getOutputStream();
					workbook.write(out);
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * Column header cell style
	 */
	public HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {

		// Set font
		HSSFFont font = workbook.createFont();
		// Set font size
		font.setFontHeightInPoints((short) 11);
		// Bold font
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// Set font name
		font.setFontName("Courier New");
		// Set the style;
		HSSFCellStyle style = workbook.createCellStyle();
		// Set the bottom border;
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		// Set the bottom border color;
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		// Set the left border;
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		// Set the left border color;
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		// Set the right border;
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		// Set the right border color;
		style.setRightBorderColor(HSSFColor.BLACK.index);
		// Set the top border;
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		// Set the top border color;
		style.setTopBorderColor(HSSFColor.BLACK.index);
		// Use the font set by the application in the style;
		style.setFont(font);
		// Set auto wrap;
		style.setWrapText(false);
		// Set the style of horizontal alignment to center alignment;
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// Set the vertical alignment style to center alignment;
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		return style;

	}

	/*
	 * Column data information cell style
	 */
	public HSSFCellStyle getStyle(HSSFWorkbook workbook) {
		// Set font
		HSSFFont font = workbook.createFont();
		// Set font size
		// font.setFontHeightInPoints((short)10);
		// Bold font
		// font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// Set font name
		font.setFontName("Courier New");
		// Set the style;
		HSSFCellStyle style = workbook.createCellStyle();
		// Set the bottom border;
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		// Set the bottom border color;
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		// Set the left border;
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		// Set the left border color;
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		// Set the right border;
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		// Set the right border color;
		style.setRightBorderColor(HSSFColor.BLACK.index);
		// Set the top border;
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		// Set the top border color;
		style.setTopBorderColor(HSSFColor.BLACK.index);
		// Use the font set by the application in the style;
		style.setFont(font);
		// Set auto wrap;
		style.setWrapText(false);
		// Set the style of horizontal alignment to center alignment;
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// Set the vertical alignment style to center alignment;
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		return style;

	}
}
