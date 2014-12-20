package com.vzs.ls.application.input.pojo.WeeklyInventory;

import com.vzs.common.util.poi.pojo.BCell;
import lombok.Data;

/**
 * Created by ben.yao on 12/6/2014.
 */
@Data
public class WeeklyInventoryRow {
	@BCell(column = "C" , types = BCell.TYPES.STRING, isSkipWhenEmpty = true,description = "编号(产品组ID)")
	String materiaId;
	@BCell(column = "D" , types = BCell.TYPES.STRING, description = "品名")
	String name;
	@BCell(column = "E" , types = BCell.TYPES.STRING, description = "单位")
	String unit;
    @BCell(column = "G" , types = BCell.TYPES.NUMERIC, description = "参考价值")
    Double unitPrice;
	@BCell(column = "V" , types = BCell.TYPES.NUMERIC, description = "本月使用    ")
	Double monthlySale;
}
