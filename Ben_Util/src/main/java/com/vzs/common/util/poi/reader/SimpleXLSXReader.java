package com.vzs.common.util.poi.reader;

import com.vzs.common.util.log.SingleThreadLogUtil;
import com.vzs.common.util.poi.pojo.BCell;
import com.vzs.common.util.poi.pojo.BSheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;
import utils.BStringUtils;
import utils.BWorkbookUtil;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by vzs on 2014/12/3.
 */
public class SimpleXLSXReader extends AbstractPoiReader{
    XSSFWorkbook xssfWorkbook;
    XSSFSheet currentSheet;
    BSheet bSheet;
    XSSFRow currnetRow;
    public SimpleXLSXReader(String filePath) {
        super(filePath);
    }



    @Override protected boolean nextRow() {
        do {
            if (currentRowNumber == null) {
                currentRowNumber = bSheet.startRow();
            } else {
                currentRowNumber++;
            }
            int lastrowNum = currentSheet.getLastRowNum();
            if (currentRowNumber > lastrowNum) {
                return false;
            }
            currnetRow = currentSheet.getRow(currentRowNumber);
        }while(currnetRow == null) ;
        return true;
    }

    private Object getCellValue(XSSFCell cell){
        try {
            int cellType = cell.getCellType();
            switch (cellType) {
                case XSSFCell.CELL_TYPE_STRING:
                    return cell.getStringCellValue();
                case XSSFCell.CELL_TYPE_BOOLEAN:
                    return cell.getBooleanCellValue();
                case XSSFCell.CELL_TYPE_NUMERIC:
                    return cell.getNumericCellValue();
                case XSSFCell.CELL_TYPE_FORMULA:
                    cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                    return cell.getRichStringCellValue();
                case XSSFCell.CELL_TYPE_ERROR:
                    return null;
                default:
                    return cell.getRichStringCellValue();
            }
        }catch (Exception e){
            SingleThreadLogUtil.log("Can't read cell [" + cell.getRowIndex() + ":" + cell.getColumnIndex() + "]") ;
            e.printStackTrace();
        }

        return null;
    }

    @Override protected Object readCellValue(BCell bCell) {
        XSSFCell cell = currnetRow.getCell(BWorkbookUtil.ToIndex(bCell.column()));
        if(cell == null){
            return null;
        }
        Object cellValue = getCellValue(cell);
        return formatValue(bCell, cellValue);
    }

    @Override
    public void initWorkbook() throws IOException {
        xssfWorkbook = new XSSFWorkbook(new FileInputStream(filePath));
    }

    @Override
    public void prepareSheet(BSheet bSheet) {
        this.bSheet = bSheet;
        int sheetIndex = bSheet.sheetIndex();
        String sheetName = bSheet.sheetName();
        if(sheetIndex >= 0){
            currentSheet = xssfWorkbook.getSheetAt(sheetIndex);
        }else if(!StringUtils.isEmpty(sheetName)){
            currentSheet = xssfWorkbook.getSheet(sheetName);
        }
        if(currentSheet == null){
            throw new IllegalArgumentException("Can't find sheet by index : " + sheetIndex + " Or sheet name : + " + sheetName);
        }
    }

    @Override public void close() {
        try {
            xssfWorkbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
