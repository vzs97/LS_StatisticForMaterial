package com.vzs.ls.application.input.pojo.InventoryRecipeTransfer;

import com.vzs.common.util.poi.pojo.BRow;
import com.vzs.ls.application.input.pojo.MaterialMaintain.MaterialRow;
import lombok.Data;

import java.util.List;

/**
 * Created by ben.yao on 12/6/2014.
 */
@Data
public class InventoryRecipeTransferSheet {
	@BRow(clazz = InventoryRecipeTransferRow.class)
	List<InventoryRecipeTransferRow> inventoryRecipeTransferRowList;
}
