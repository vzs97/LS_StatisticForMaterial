package com.vzs.ls.application.input.pojo.InventoryRecipeTransfer;

import com.vzs.common.util.poi.pojo.BSheet;
import com.vzs.common.util.poi.pojo.BWorkbook;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ben.yao on 12/6/2014.
 */
@Data
@NoArgsConstructor
@BWorkbook(description = "盘点与配方单位转换表.xls")
public class InventoryRecipeTransferWorkbook {
	@BSheet(sheetIndex = 0,startRow = 3)
	InventoryRecipeTransferSheet inventoryRecipeTransferSheet;
}
