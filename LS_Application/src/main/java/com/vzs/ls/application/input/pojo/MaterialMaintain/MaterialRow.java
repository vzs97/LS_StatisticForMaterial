package com.vzs.ls.application.input.pojo.MaterialMaintain;

import com.vzs.common.util.poi.pojo.BCell;
import lombok.Data;

/**
 * Created by ben.yao on 12/6/2014.
 */
@Data
public class MaterialRow {
	@BCell(column = "A" , types = BCell.TYPES.INTEGER, isStop = true)
	Integer no;
	@BCell(column = "B" , types = BCell.TYPES.STRING)
	String classification;
	@BCell(column = "C" , types = BCell.TYPES.STRING)
	String productSeriesId;
	@BCell(column = "D" , types = BCell.TYPES.STRING)
	String materialName;
	@BCell(column = "E" , types = BCell.TYPES.BOOLEAN)
	boolean isReviewByWeek;
	@BCell(column = "F" , types = BCell.TYPES.BOOLEAN)
	boolean isReviewByMonth;
	@BCell(column = "G" , types = BCell.TYPES.PERCENT)
	Double targetValue;
}
