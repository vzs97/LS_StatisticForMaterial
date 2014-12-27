package com.vzs.ls.application.input.pojo.InventoryRecipeTransfer;

import com.google.common.collect.Maps;
import com.vzs.common.util.poi.pojo.BSheet;
import com.vzs.common.util.poi.pojo.BWorkbook;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Created by ben.yao on 12/6/2014.
 */
@Data
@NoArgsConstructor
@BWorkbook(description = "盘点与配方单位转换表.xls")
public class InventoryRecipeTransferWorkbook {
	@BSheet(sheetIndex = 0,startRow = 3)
	InventoryRecipeTransferSheet inventoryRecipeTransferSheet;
	@Getter
	Map<String,InventoryRecipeTransferRow> idToRow = Maps.newHashMap();
	public void init(){
		for (InventoryRecipeTransferRow inventoryRecipeTransferRow : inventoryRecipeTransferSheet.getInventoryRecipeTransferRowList()) {
			idToRow.put(inventoryRecipeTransferRow.getJdeCode(),inventoryRecipeTransferRow);
		}
	}

}
