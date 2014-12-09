package com.vzs.common.util.poi.reader;

import com.google.common.collect.Lists;
import com.vzs.common.util.poi.pojo.BCell;
import com.vzs.common.util.poi.pojo.BRow;
import com.vzs.common.util.poi.pojo.BSheet;
import com.vzs.common.util.poi.pojo.BWorkbook;
import lombok.Data;
import org.springframework.util.StringUtils;
import utils.BAnnotationHelper;
import utils.BReflectHelper;
import utils.BStringUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Created by vzs on 2014/12/3.
 */
public abstract class AbstractPoiReader implements PoiReader{
    String filePath;
    Integer currentRowNumber;
    public AbstractPoiReader(String filePath) {
        this.filePath=filePath;
        try {
            initWorkbook();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object readSheet(Class sheetClazz){
        Object sheetObj = BReflectHelper.newInstance(sheetClazz);
        Field[] fields = sheetObj.getClass().getDeclaredFields();
        for (Field field : fields){
            BRow bRow = field.getAnnotation(BRow.class);
            if(bRow != null){
                List row = readRow(bRow.clazz());
                BReflectHelper.setValues(sheetObj, field, row);
            }
        }
        return sheetObj;
    }

    protected boolean startRow() {
        currentRowNumber = null;
        return true;

    }
    protected Object formatValue(BCell bCell, Object value){
        if(value == null){
            return null;
        }
        String strValue = value.toString();
        try {
            switch (bCell.types()) {
                case STRING:
                    return BStringUtils.toString(value);
                case DATE:
                    return value;
                case INTEGER:
                    if(StringUtils.isEmpty(strValue)){
                        return null;
                    }
                    return new Double(value.toString()).intValue();
                case NUMERIC:
                    if(StringUtils.isEmpty(strValue)){
                        return null;
                    }
                    return Double.parseDouble(value.toString());
                case BOOLEAN:
                    return BStringUtils.toBoolean(value);
                case PERCENT:
                    return value;
            }
        }catch (Exception e){
            System.out.println("Can't format cell [" + currentRowNumber +":" + bCell.column() +"]") ;
            e.printStackTrace();
        }
        return null;

    }
    protected abstract boolean nextRow();
    protected abstract Object readCellValue(BCell bCell);


    protected  <T> List<T> readRow(Class<T> clazz){
        List<T> rows = Lists.newArrayList();
        startRow();
        while(nextRow()) {
            T rowObj = BReflectHelper.newInstance(clazz);
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                BCell bCell = field.getAnnotation(BCell.class);
                if (bCell != null) {
                    Object value = readCellValue(bCell);
                    if(bCell.isStop() && StringUtils.isEmpty(value)){
                        return rows;
                    }else if(bCell.isSkipWhenEmpty() && StringUtils.isEmpty(value)){
                        rowObj = null;
                        break;
                    }
                    BReflectHelper.setValues(rowObj, field, value);
                }
            }
            if(rowObj != null) {
                rows.add(rowObj);
            }
        }
        return rows;
    }

    public static int ToIndex(String columnName)
    {
        columnName = columnName.trim();
        if(StringUtils.isEmpty(columnName)){
            return -1;
        }
        Pattern pattern = Pattern.compile("[A-Z]+");
        if (!pattern.matcher(columnName.toUpperCase()).matches())
            throw new RuntimeException("invalid parameter");
        int index = 0;
        char[] chars = columnName.toUpperCase().toCharArray();
        for (int i = 0; i < chars.length; i++)
        {
            index += ((int)chars[i] - (int)'A' + 1) * (int)Math.pow(26, chars.length - i - 1);
        }
        return index - 1;
    }

}
