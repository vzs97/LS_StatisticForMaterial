package com.vzs.ls.application.input.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ben.yao on 12/6/2014.
 */
@Data
public class InputContext {
	String mainPath;
	public InputContext(String mainPath){
		this.mainPath=mainPath;
		materialMaintian = mainPath + materialMaintian;
		inventoryRecipTransfer = mainPath + inventoryRecipTransfer;
		dishesSaleStatistic = mainPath + dishesSaleStatistic;
		//TODO: need change
		weeklyInventory = mainPath + weeklyInventory;
	}
	String materialMaintian = "物料维护表.xlsx";
	String inventoryRecipTransfer="盘点与配方单位转换表.xls";
	String dishesSaleStatistic="菜品销售统计表.xls";
	String weeklyInventory="周盘点表PIIT76227001201410.xls";
}
