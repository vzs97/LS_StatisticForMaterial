package com.vzs.ls.application.output.pojo.DistructResturant;

import com.vzs.common.util.poi.pojo.BCell;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by byao on 1/4/15.
 */
@Data
@NoArgsConstructor
public class DistractTitleRow {
    @BCell(column = "AUTO" , startColumn = 0,types = BCell.TYPES.STRING, description = "Title")
    List<String> titles;
}
