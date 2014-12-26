package com.vzs.ls.application.input.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

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
		recipe = mainPath+recipe;
        resturantMaintain = mainPath + resturantMaintain;

		weeklyInventory = mainPath + weeklyInventory;

        singleRestuarntFolder = mainPath + singleRestuarntFolder + File.separator;

        productIdRefrence = mainPath + productIdRefrence;
        singleResturantTemplate = mainPath + singleResturantTemplate;
        logFolder = mainPath + logFolder + File.separator;;

	}
	String materialMaintian = "物料维护表.xlsx";
	String inventoryRecipTransfer="盘点与配方单位转换表.xls";
	String dishesSaleStatistic="菜品销售统计表";
	String weeklyInventory="PIIT";//周盘点表PIIT76227001201410.xls
	String recipe="LS 产品配方及物料对应表.xlsx";
    String resturantMaintain="餐厅信息维护表.xls";

    String productIdRefrence = "产品组ID表.xlsx";

    String singleRestuarntFolder="output";

    String singleResturantTemplate = "EOWTemplate.xls";

    String logFolder = "log";
}
