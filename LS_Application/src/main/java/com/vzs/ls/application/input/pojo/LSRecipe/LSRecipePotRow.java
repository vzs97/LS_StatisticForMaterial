package com.vzs.ls.application.input.pojo.LSRecipe;

import com.vzs.common.util.poi.pojo.BCell;
import lombok.Data;

/**
 * Created by ben.yao on 12/9/2014.
 */
@Data
public class LSRecipePotRow {
	@BCell(column = "A" , types = BCell.TYPES.STRING, isSkipWhenEmpty = true, description = "天子星系统代码")
	String sunOfGodSystemId;
	@BCell(column = "B" , types = BCell.TYPES.STRING, description = "产品名称")
	String productName;
	@BCell(column = "C" , types = BCell.TYPES.STRING, description = "产品组ID")
	String materialId;
	@BCell(column = "D" , types = BCell.TYPES.STRING, description = "物料名称")
	String materialName;
	@BCell(column = "E" , types = BCell.TYPES.NUMERIC, description = "配方克数")
	Double recipeCount;
	@BCell(column = "F" , types = BCell.TYPES.STRING, description = "配方单位")
	String recipeUnit;

}
