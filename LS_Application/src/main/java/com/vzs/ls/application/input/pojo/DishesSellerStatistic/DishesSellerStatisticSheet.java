package com.vzs.ls.application.input.pojo.DishesSellerStatistic;

import com.vzs.common.util.poi.pojo.BRow;
import lombok.Data;

import java.util.List;

/**
 * Created by ben.yao on 12/6/2014.
 */
@Data
public class DishesSellerStatisticSheet {
	@BRow(clazz = DishesSellerStatisticRow.class)
	List<DishesSellerStatisticRow> dishesSellerStatisticRowList;
}
