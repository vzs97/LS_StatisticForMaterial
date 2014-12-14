package com.vzs.ls.application.input.pojo.ResturantMaintain;

import com.vzs.common.util.poi.pojo.BSheet;
import com.vzs.common.util.poi.pojo.BWorkbook;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by byao on 12/13/14.
 */
@Data
@NoArgsConstructor
@BWorkbook(description = "餐厅信息维护表.xlsx")
public class ResturantMaintainWorkbook {
    @BSheet(sheetIndex = 0,startRow = 2)
    ResturantMaintainSheet resturantMaintainSheet;
}
