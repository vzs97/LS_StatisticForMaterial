package com.vzs.ls.application.output.pojo.DistructResturant;

import com.vzs.common.util.poi.pojo.BRow;
import com.vzs.ls.application.output.pojo.SingleRestaruant.SingleRestaurantRow;
import lombok.Data;

import java.util.List;

/**
 * Created by byao on 1/4/15.
 */
@Data
public class DistractSheet {
    @BRow(clazz = DistractTitleRow.class)
    DistractTitleRow distractTitleRow;
    @BRow(clazz = DistractRow.class)
    List<DistractRow> distractRowList;
    @BRow(clazz = DistractRowPercent.class)
    DistractRowPercent distractRowPercent;
}
