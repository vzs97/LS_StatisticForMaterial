package com.vzs.ls.application.input.pojo.ProductIDReference;

import com.vzs.common.util.poi.pojo.BSheet;
import com.vzs.common.util.poi.pojo.BWorkbook;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by byao on 12/20/14.
 */
@Data
@NoArgsConstructor
@BWorkbook(description = "产品组ID.xls")
public class ProductIDReferenceWorkbook {
    @BSheet(sheetIndex = 0,startRow = 6)
    ProductIDReferenceSheet productIDReferenceSheet;
}
