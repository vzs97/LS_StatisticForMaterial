package com.vzs.ls.application.output.pojo.SingleRestaruant;

import com.vzs.common.util.poi.pojo.BCell;
import com.vzs.common.util.poi.pojo.BStyle;
import com.vzs.common.util.poi.pojo.BStyleMethod;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ben.yao on 12/6/2014.
 */
@Data
@NoArgsConstructor
public class SingleRestaurantRow {
	@BCell(column = "A" , types = BCell.TYPES.STRING, description = "分类")
	String classification;
	@BCell(column = "B" , types = BCell.TYPES.STRING, description = "产品组ID")
	String materialNo;
	@BCell(column = "C" , types = BCell.TYPES.STRING, description = "物料名称")
	String name;
	@BCell(column = "D" , types = BCell.TYPES.STRING, description = "盘点单位")
	String inventoryUnit;
	@BCell(column = "E" , types = BCell.TYPES.NUMERIC, description = "理论用量")
	Double theoryCousumption;
	@BCell(column = "F" , types = BCell.TYPES.NUMERIC, description = "实际用量")
	Double actualyCoumption;
	@BCell(column = "G" , types = BCell.TYPES.NUMERIC, description = "差异")
	Double diff;
	@BCell(column = "H" , types = BCell.TYPES.NUMERIC, description = "差异金额")
	Double diffCount;
	@BCell(column = "I" , types = BCell.TYPES.PERCENT, description = "得率")
    @BStyle(styleHanlde = RateStyle.class, method = BStyleMethod.COLOR, refereceObj = "targetValue")
	Double rate;
	@BCell(column = "J" , types = BCell.TYPES.PERCENT, description = "目标值")
	Double targetValue;
}
