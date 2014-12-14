package com.vzs.ls.application.input.pojo.DishesSellerStatistic;

import com.google.common.collect.Maps;
import com.vzs.common.util.poi.pojo.BRow;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created by ben.yao on 12/6/2014.
 */
@Data
public class DishesSellerStatisticSheet {
	@BRow(clazz = DishesSellerStatisticRow.class)
	List<DishesSellerStatisticRow> dishesSellerStatisticRowList;
    Map<String,DishesSellerStatisticRow> dishesSellerStatisticRowMap = Maps.newHashMap();
    public void initDishesMap(){
        for (DishesSellerStatisticRow dishesSellerStatisticRow : dishesSellerStatisticRowList) {

            dishesSellerStatisticRowMap.put(dishesSellerStatisticRow.getMaterialNo(),dishesSellerStatisticRow);
        }
    }
}
