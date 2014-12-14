package com.vzs.ls.application.input.pojo.LSRecipe;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.vzs.common.util.poi.pojo.BRow;
import lombok.Data;

import java.util.List;

/**
 * Created by ben.yao on 12/9/2014.
 */
@Data
public class LSRecipePotSheet {
	@BRow(clazz = LSRecipePotRow.class)
	List<LSRecipePotRow> lsRecipePotRowList;

    Multimap<String,LSRecipePotRow> multimap = HashMultimap.create();
    public void initSort(){
        for (LSRecipePotRow lsRecipePotRow : lsRecipePotRowList) {
            multimap.put(lsRecipePotRow.getMaterialId(),lsRecipePotRow);
        }

    }
}
