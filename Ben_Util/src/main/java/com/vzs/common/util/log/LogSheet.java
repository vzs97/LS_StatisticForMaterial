package com.vzs.common.util.log;

import com.google.common.collect.Lists;
import com.vzs.common.util.poi.pojo.BRow;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by byao on 12/26/14.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogSheet {
    @BRow(clazz = LogRow.class)
    List<LogRow> logRows = Lists.newArrayList();
}
