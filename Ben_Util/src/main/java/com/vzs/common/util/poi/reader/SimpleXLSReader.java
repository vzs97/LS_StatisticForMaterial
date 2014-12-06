package com.vzs.common.util.poi.reader;

import com.vzs.common.util.poi.pojo.BCell;
import com.vzs.common.util.poi.pojo.BSheet;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by ben.yao on 12/6/2014.
 */
public class SimpleXLSReader extends AbstractPoiReader{
	HSSFWorkbook hssfWorkbook;
	HSSFSheet currentSheet;
	BSheet bSheet;
	HSSFRow currnetRow;
	public SimpleXLSReader(String filePath) {
		super(filePath);
	}



	@Override protected boolean nextRow() {
		if(currentRowNumber == null){
			currentRowNumber = bSheet.startRow();
		}else{
			currentRowNumber ++;
		}
		int lastrowNum = currentSheet.getLastRowNum();
		if(currentRowNumber > lastrowNum){
			return false;
		}
		currnetRow = currentSheet.getRow(currentRowNumber);
		return true;
	}

	private Object getCellValue(HSSFCell cell){
		try {
			int cellType = cell.getCellType();
			switch (cellType) {
				case HSSFCell.CELL_TYPE_STRING:
					return cell.getStringCellValue();
				case HSSFCell.CELL_TYPE_BOOLEAN:
					return cell.getBooleanCellValue();
				case HSSFCell.CELL_TYPE_NUMERIC:
					return cell.getNumericCellValue();
				case HSSFCell.CELL_TYPE_FORMULA:
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					return cell.getRichStringCellValue();
				case HSSFCell.CELL_TYPE_ERROR:
					return null;
				default:
					return cell.getRichStringCellValue();
			}
		}catch (Exception e){
			System.out.println("Can't read cell [" + cell.getRowIndex() +":" + cell.getColumnIndex() +"]") ;
			e.printStackTrace();
		}

		return null;
	}

	@Override protected Object readCellValue(BCell bCell) {
		HSSFCell cell = currnetRow.getCell(ToIndex(bCell.column()));
		Object cellValue = getCellValue(cell);
		return formatValue(bCell, cellValue);
	}

	@Override
	public void initWorkbook() throws IOException {
		hssfWorkbook = new HSSFWorkbook(new FileInputStream(filePath));
	}

	@Override
	public void prepareSheet(BSheet bSheet) {
		this.bSheet = bSheet;
		int sheetIndex = bSheet.sheetIndex();
		String sheetName = bSheet.sheetName();
		if(sheetIndex >= 0){
			currentSheet = hssfWorkbook.getSheetAt(sheetIndex);
		}else if(!StringUtils.isEmpty(sheetName)){
			currentSheet = hssfWorkbook.getSheet(sheetName);
		}
		if(currentSheet == null){
			throw new IllegalArgumentException("Can't find sheet by index : " + sheetIndex + " Or sheet name : + " + sheetName);
		}
	}

	@Override public void close() {
		try {
			hssfWorkbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
