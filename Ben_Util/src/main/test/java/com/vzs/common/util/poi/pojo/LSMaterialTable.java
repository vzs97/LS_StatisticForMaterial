package com.vzs.common.util.poi.pojo;

import lombok.Data;

/**
 * Created by ben.yao on 12/1/2014.
 */

@Data
public class LSMaterialTable {
	@BWorkbook
	String workbookName;
	@BSheet(sheetIndex=0)
	LSMaterialSheet sheet1;
}
