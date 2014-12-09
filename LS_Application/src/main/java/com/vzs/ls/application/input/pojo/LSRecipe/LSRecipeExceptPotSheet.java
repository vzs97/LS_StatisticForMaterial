package com.vzs.ls.application.input.pojo.LSRecipe;

import com.vzs.common.util.poi.pojo.BRow;
import lombok.Data;

import java.util.List;

/**
 * Created by ben.yao on 12/9/2014.
 */
@Data
public class LSRecipeExceptPotSheet {
	@BRow(clazz = LSRecipeExceptPotRow.class)
	List<LSRecipeExceptPotRow> lsRecipeExceptPotRowList;
}
