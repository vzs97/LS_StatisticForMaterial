package com.vzs.ls.application.input.pojo.DishesSellerStatistic;

import com.vzs.common.util.poi.pojo.BSheet;
import com.vzs.common.util.poi.pojo.BWorkbook;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ben.yao on 12/6/2014.
 */
@Data
@NoArgsConstructor
@BWorkbook(description = "菜品销售统计表.xls")
public class DishesSellerStatisticWorkbook {
	@BSheet(sheetIndex = 0,startRow = 5)
	DishesSellerStatisticSheet dishesSellerStatisticSheet;
}
