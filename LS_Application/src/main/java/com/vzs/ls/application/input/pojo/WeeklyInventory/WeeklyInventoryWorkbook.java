package com.vzs.ls.application.input.pojo.WeeklyInventory;

import com.vzs.common.util.poi.pojo.BSheet;
import com.vzs.common.util.poi.pojo.BWorkbook;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ben.yao on 12/6/2014.
 */
@Data
@NoArgsConstructor
@BWorkbook(description = "周盘点表.xls")
public class WeeklyInventoryWorkbook {
	@BSheet(sheetIndex = 0,startRow = 6)
	WeeklyInventorySheet weeklyInventorySheet;
}
