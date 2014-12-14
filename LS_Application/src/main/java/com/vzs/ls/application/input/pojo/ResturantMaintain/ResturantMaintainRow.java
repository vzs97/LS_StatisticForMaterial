package com.vzs.ls.application.input.pojo.ResturantMaintain;

import com.vzs.common.util.poi.pojo.BCell;
import lombok.Data;

/**
 * Created by byao on 12/13/14.
 */
@Data
public class ResturantMaintainRow {
    @BCell(column = "A" , types = BCell.TYPES.STRING, isStop = true, description = "餐厅代码")
    String resturantNo;
    @BCell(column = "B" , types = BCell.TYPES.STRING,  description = "餐厅名称")
    String resturantName;
    @BCell(column = "C" , types = BCell.TYPES.STRING, description = "DM")
    String DM;
    @BCell(column = "D" , types = BCell.TYPES.STRING,  description = "锅底配方版本")
    String potVersion;
}
