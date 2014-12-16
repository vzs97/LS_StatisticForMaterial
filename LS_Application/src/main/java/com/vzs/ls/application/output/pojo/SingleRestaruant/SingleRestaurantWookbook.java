package com.vzs.ls.application.output.pojo.SingleRestaruant;

import com.vzs.common.util.poi.pojo.BSheet;
import com.vzs.common.util.poi.pojo.BWorkbook;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ben.yao on 12/6/2014.
 */
@Data
@NoArgsConstructor
@BWorkbook
public class SingleRestaurantWookbook {
	@BSheet(sheetName = "单店报告", startRow = 3)
	SingleRestaurantSheet singleRestaurantSheet;

}
