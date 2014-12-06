package com.vzs.common.util.poi.pojo;

import lombok.Data;

/**
 * Created by ben.yao on 12/2/2014.
 */
@Data
public class LSMaterialRow {
	@BCell(column = "A" , types = BCell.TYPES.INTEGER)
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
