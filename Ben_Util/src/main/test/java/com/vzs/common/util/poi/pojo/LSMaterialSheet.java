package com.vzs.common.util.poi.pojo;

import lombok.Data;

/**
 * Created by ben.yao on 12/1/2014.
 */
@Data
@BSheet(sheetIndex = 0)
public class LSMaterialSheet {
	@BCell(row=7,column = 2,types = BCell.TYPES.STRING)
	String m7;

}
