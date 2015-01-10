package com.vzs.ls.application.output.pojo.DistructResturant;

import com.vzs.common.util.poi.pojo.BCell;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by byao on 1/4/15.
 */
@Data
@NoArgsConstructor
public class DistractRow {
    @BCell(column = "A" , types = BCell.TYPES.STRING, description = "产品组ID")
    String materialNo;
    @BCell(column = "B" , types = BCell.TYPES.STRING, description = "产品名字")
    String name;
    @BCell(column = "C" , types = BCell.TYPES.NUMERIC, description = "产品组ID")
    Double distractCount;
    @BCell(column = "AUTO" , types = BCell.TYPES.NUMERIC, description = "餐厅Valudes")
    List<Double> resValues;

}
