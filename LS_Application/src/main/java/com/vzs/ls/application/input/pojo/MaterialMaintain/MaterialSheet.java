package com.vzs.ls.application.input.pojo.MaterialMaintain;

import com.vzs.common.util.poi.pojo.BRow;
import lombok.Data;

import java.util.List;

/**
 * Created by ben.yao on 12/6/2014.
 */
@Data
public class MaterialSheet {
	@BRow(clazz = MaterialRow.class)
	List<MaterialRow> lsMaterialRowList;
}
