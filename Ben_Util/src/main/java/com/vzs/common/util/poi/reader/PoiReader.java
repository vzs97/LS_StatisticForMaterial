package com.vzs.common.util.poi.reader;

import com.vzs.common.util.poi.pojo.BSheet;

import java.io.IOException;

/**
 * Created by vzs on 2014/12/3.
 */
public interface PoiReader {
	public void initWorkbook() throws IOException;
	public void prepareSheet(BSheet bSheet);
	public Object readSheet(Class sheetClazz);
	public void close();
}
