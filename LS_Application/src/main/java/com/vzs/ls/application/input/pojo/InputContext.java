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
	}
	String materialMaintian = "物料维护表.xlsx";
	String inventoryRecipTransfer="盘点与配方单位转换表.xls";
}
