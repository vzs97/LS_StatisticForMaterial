package com.vzs.ls.application.input.pojo.LSRecipe;

import com.vzs.common.util.poi.pojo.BSheet;
import com.vzs.common.util.poi.pojo.BWorkbook;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

/**
 * Created by ben.yao on 12/9/2014.
 */
@Data
@NoArgsConstructor
@BWorkbook(description = "LS 产品配方及物料对应表.xlsx")
public class LSRecipeWorkbook {
	@BSheet(sheetName = "配方锅底除外",startRow = 3)
	LSRecipeExceptPotSheet exceptPotRecipe;
	@BSheet(sheetName = "全国新",startRow = 3)
	LSRecipePotSheet nationalNew;
	@BSheet(sheetName = "全国老",startRow = 3)
	LSRecipePotSheet nationalOld;
	@BSheet(sheetName = "孜然新",startRow = 3)
	LSRecipePotSheet cuminNew;
	@BSheet(sheetName = "孜然老",startRow = 3)
	LSRecipePotSheet cuminOld;
	@BSheet(sheetName = "当归新",startRow = 3)
	LSRecipePotSheet angeclicaNew;
	@BSheet(sheetName = "当归老",startRow = 3)
	LSRecipePotSheet angeclicalOld;
	@BSheet(sheetName = "广东新",startRow = 3)
	LSRecipePotSheet guangDongnew;
	@BSheet(sheetName = "广东老",startRow = 3)
	LSRecipePotSheet guangDongOld;


	public void init(){
		String lastNo = null;
		String lastProductName=null;
		for (LSRecipeExceptPotRow lsRecipeExceptPotRow : exceptPotRecipe.getLsRecipeExceptPotRowList()) {
			String currentNo =  lsRecipeExceptPotRow.getSunOfGodSystemId();
			if(StringUtils.isEmpty(currentNo) && lastNo != null){
				lsRecipeExceptPotRow.setSunOfGodSystemId(lastNo);
				lsRecipeExceptPotRow.setProductName(lastProductName);
			}
			lastNo = lsRecipeExceptPotRow.getSunOfGodSystemId();
			lastProductName = lsRecipeExceptPotRow.getProductName();


		}
	}



}
