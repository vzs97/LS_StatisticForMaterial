package com.vzs.ls.application.output.pojo.SingleRestaruant;

import com.google.common.collect.Lists;
import com.vzs.common.util.poi.pojo.BRow;

import java.util.List;

/**
 * Created by ben.yao on 12/6/2014.
 */
public class SingleRestaurantSheet {
	@BRow(clazz = SingleRestaurantRow.class)
	List<SingleRestaurantRow> singleRestaurantRowList = Lists.newArrayList();
}
