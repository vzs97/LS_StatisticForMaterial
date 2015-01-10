package com.vzs.ls.application.input.pojo;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.NoArgsConstructor;
import utils.BReflectHelper;
import utils.FileUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

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

        dmFolder = singleRestuarntFolder + dmFolder + File.separator;

	}
	String materialMaintian = "物料维护表.xlsx";
	String inventoryRecipTransfer="盘点与配方单位转换表.xls";
	String dishesSaleStatistic="菜品销售统计表";
	String weeklyInventory="PIIT";//周盘点表PIIT76227001201410.xls
	String recipe="LS 产品配方及物料对应表.xlsx";
    String resturantMaintain="餐厅信息维护表.xls";

    String productIdRefrence = "产品组ID表.xlsx";

    String singleRestuarntFolder="output";

    String dmFolder = "dm";

    String singleResturantTemplate = "EOWTemplate.xls";

    String logFolder = "log";

    public List<String> preValidate(){
        List<String> missingList = Lists.newArrayList();
        for (Field field : this.getClass().getDeclaredFields()) {
            Object value = BReflectHelper.getValue(this, field);
            if(value instanceof String && !(((String) value).equals(logFolder) || ((String)value).equals(singleRestuarntFolder)|| ((String)value).equals(mainPath))){
                if(!FileUtils.isExists(value.toString())){
                    missingList.add(value.toString());
                }
            }
        }

        return missingList;
    }
}
