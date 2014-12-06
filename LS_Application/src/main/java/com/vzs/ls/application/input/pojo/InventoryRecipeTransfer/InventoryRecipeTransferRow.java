package com.vzs.ls.application.input.pojo.InventoryRecipeTransfer;

import com.vzs.common.util.poi.pojo.BCell;
import lombok.Data;

/**
 * Created by ben.yao on 12/6/2014.
 */
@Data
public class InventoryRecipeTransferRow {
	@BCell(column = "A" , types = BCell.TYPES.STRING, isStop = true, description = "产品组ID")
	String materialId;
	@BCell(column = "B" , types = BCell.TYPES.STRING , description = "(Material)")
	String material;
	@BCell(column = "C" , types = BCell.TYPES.STRING , description = "配方单位")
	String RecipeUnit;
	@BCell(column = "D" , types = BCell.TYPES.NUMERIC , description = "转换关系")
	Double transferUnit;
	@BCell(column = "E" , types = BCell.TYPES.STRING , description = "盘点单位")
	String inventoryUnit;
	@BCell(column = "F" , types = BCell.TYPES.NUMERIC , description = "目标值")
	Double target;
}
