package com.vzs.ls.application.input.pojo.MaterialMaintain;

import com.vzs.common.util.poi.pojo.BSheet;
import com.vzs.common.util.poi.pojo.BWorkbook;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ben.yao on 12/6/2014.
 */
@Data
@NoArgsConstructor
@BWorkbook(description = "物料维护表.xlsx")
public class MaterialMaintainWorkbook {
	@BSheet(sheetIndex = 0,startRow = 6)
	MaterialSheet materialSheet;
}
