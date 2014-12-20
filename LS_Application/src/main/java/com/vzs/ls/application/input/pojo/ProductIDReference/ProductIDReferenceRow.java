package com.vzs.ls.application.input.pojo.ProductIDReference;

import com.vzs.common.util.poi.pojo.BCell;
import lombok.Data;

/**
 * Created by byao on 12/20/14.
 */
@Data
public class ProductIDReferenceRow {
    @BCell(column = "A" , types = BCell.TYPES.STRING, isSkipWhenEmpty = true, description = "JDE Code")
    String jdeCode;
    @BCell(column = "B" , types = BCell.TYPES.STRING, description = "产品名称")
    String productName;
    @BCell(column = "C" , types = BCell.TYPES.STRING, description = "产品组ID")
    String materId;
    @BCell(column = "D" , types = BCell.TYPES.STRING, description = "产品组ID 名称")
    String materialName;
}
