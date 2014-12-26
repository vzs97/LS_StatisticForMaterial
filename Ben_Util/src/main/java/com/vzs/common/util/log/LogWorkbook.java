package com.vzs.common.util.log;

import com.vzs.common.util.poi.pojo.BSheet;
import com.vzs.common.util.poi.pojo.BWorkbook;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by byao on 12/26/14.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@BWorkbook
public class LogWorkbook {
    @BSheet(sheetName = "log", startRow = 0)
    LogSheet logSheet;
}
