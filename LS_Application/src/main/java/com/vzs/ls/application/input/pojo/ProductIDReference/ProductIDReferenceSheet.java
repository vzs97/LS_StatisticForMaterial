package com.vzs.ls.application.input.pojo.ProductIDReference;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.vzs.common.util.poi.pojo.BRow;
import com.vzs.ls.application.input.pojo.LSRecipe.LSRecipeExceptPotRow;
import lombok.Data;

import java.util.List;

/**
 * Created by byao on 12/20/14.
 */
@Data
public class ProductIDReferenceSheet {
    @BRow(clazz = ProductIDReferenceRow.class)
    List<ProductIDReferenceRow> productIDReferenceRowList;

    Multimap<String,ProductIDReferenceRow> multimap = HashMultimap.create();
    public void initRowList(){
        for(ProductIDReferenceRow row: productIDReferenceRowList){
            if("86910034Q".equalsIgnoreCase(row.getMaterId())){
                System.out.println(row);
            }
            multimap.put(row.getMaterId().trim(),row);
        }
    }
}
