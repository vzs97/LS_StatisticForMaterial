package com.vzs.ls.application.output.pojo.log;

import com.vzs.common.util.poi.pojo.BCell;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by byao on 12/26/14.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogRow {
    @BCell(column = "A" , types = BCell.TYPES.STRING)
    String log;

}
