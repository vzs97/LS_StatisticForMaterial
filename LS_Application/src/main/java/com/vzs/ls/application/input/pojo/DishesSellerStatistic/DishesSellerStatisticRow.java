package com.vzs.ls.application.input.pojo.DishesSellerStatistic;

import com.vzs.common.util.poi.pojo.BCell;
import lombok.Data;

/**
 * Created by ben.yao on 12/6/2014.
 */
@Data
public class DishesSellerStatisticRow {
	@BCell(column = "G" , types = BCell.TYPES.STRING, isSkipWhenEmpty = true, description = "菜品编码")
	String materialNo;
	@BCell(column = "H" , types = BCell.TYPES.STRING, description = "菜品编码")
	String dishesName;
	@BCell(column = "I" , types = BCell.TYPES.STRING, description = "菜品单位")
	String dishesUnit;
	@BCell(column = "J" , types = BCell.TYPES.NUMERIC, description = "菜品销售数量")
	Double sales;
}
