package com.vzs.ls.application.input.pojo.MaterialMaintain;

import com.vzs.common.util.poi.pojo.BCell;
import lombok.Data;

/**
 * Created by ben.yao on 12/6/2014.
 */
@Data
public class MaterialRow {
	@BCell(column = "A" , types = BCell.TYPES.INTEGER, isStop = true, description = "序号")
	Integer no;
	@BCell(column = "B" , types = BCell.TYPES.STRING, description = "分类")
	String classification;
	@BCell(column = "C" , types = BCell.TYPES.STRING, description = "产品组ID")
	String productSeriesId;
	@BCell(column = "D" , types = BCell.TYPES.STRING, description = "物料名称")
	String materialName;
	@BCell(column = "E" , types = BCell.TYPES.BOOLEAN, description = "周Review")
	boolean isReviewByWeek;
	@BCell(column = "F" , types = BCell.TYPES.BOOLEAN, description = "月Review")
	boolean isReviewByMonth;
	@BCell(column = "G" , types = BCell.TYPES.PERCENT, description = "目标值")
	Double targetValue;
}
