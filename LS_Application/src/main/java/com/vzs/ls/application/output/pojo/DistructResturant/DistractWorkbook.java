package com.vzs.ls.application.output.pojo.DistructResturant;

import com.vzs.common.util.poi.pojo.BSheet;
import com.vzs.common.util.poi.pojo.BWorkbook;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by byao on 1/4/15.
 */
@Data
@NoArgsConstructor
@BWorkbook
public class DistractWorkbook {
    @BSheet(sheetName = "报告", startRow = 3)
    DistractSheet distractSheet;

}
