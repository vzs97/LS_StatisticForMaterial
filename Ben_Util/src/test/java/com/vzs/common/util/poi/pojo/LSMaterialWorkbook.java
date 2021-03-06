package com.vzs.common.util.poi.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ben.yao on 12/1/2014.
 */

@Data
@NoArgsConstructor
@BWorkbook(description = "物料维护表.xlsx")
public class LSMaterialWorkbook {
	@BSheet(sheetIndex = 0,startRow = 2)
	LSMaterialSheet lsMaterialSheet;
}
