package com.vzs.ls.application.input.pojo.ResturantMaintain;

import com.vzs.common.util.poi.pojo.BRow;
import lombok.Data;

import java.util.List;

/**
 * Created by byao on 12/13/14.
 */
@Data
public class ResturantMaintainSheet {
    @BRow(clazz = ResturantMaintainRow.class)
    List<ResturantMaintainRow> resturantMaintainRowList;
}
