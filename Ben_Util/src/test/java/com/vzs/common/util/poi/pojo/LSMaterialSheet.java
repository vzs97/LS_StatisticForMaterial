package com.vzs.common.util.poi.pojo;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * Created by ben.yao on 12/1/2014.
 */
@Data
@BSheet(sheetIndex = 0,startColumn = 0,startRow = 2)
public class LSMaterialSheet {
	@BCell(row=7,column = "A",types = BCell.TYPES.STRING)
	String m7;

    List<LSMaterialRow> lsMaterialRowList;
}
