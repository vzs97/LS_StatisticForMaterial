package com.vzs.ls.application.input.pojo.WeeklyInventory;

import com.google.common.collect.Maps;
import com.vzs.common.util.poi.pojo.BRow;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created by ben.yao on 12/6/2014.
 */
@Data
public class WeeklyInventorySheet {
	@BRow(clazz = WeeklyInventoryRow.class)
	List<WeeklyInventoryRow> weeklyInventoryRowList;
    Map<String, WeeklyInventoryRow> materialNoToRow = Maps.newHashMap();
    public void initInventoryMap() {
        for(WeeklyInventoryRow row : weeklyInventoryRowList){
            materialNoToRow.put(row.getJdeId(),row);
        }
    }
}
