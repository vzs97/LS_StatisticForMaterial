package com.vzs.common.util.log;

import com.google.common.collect.Lists;
import com.vzs.common.util.dao.BWorkbookDaoImpl;
import utils.DateUtil;

import java.util.List;

/**
 * Created by byao on 12/26/14.
 */
public class SingleThreadLogUtil {
    private static BWorkbookDaoImpl inputDao = new BWorkbookDaoImpl();
    private static SingleThreadLogUtil _instance;
    private SingleThreadLogUtil(){

    }
    public static SingleThreadLogUtil getInstance(){
        if(_instance == null) {
            _instance = new SingleThreadLogUtil();
        }
        return _instance;
    }
    public static void log(Object log){
        String logmsg = log == null ? "":log.toString();
        System.out.println(logmsg);
        getInstance().logMsg(logmsg);
    }
    private List<LogRow> errorLogs = Lists.newArrayList();
    public void logMsg(String log){
        errorLogs.add(new LogRow(log));
    }
    public void flush(String outputFolder){
        LogSheet logSheet = new LogSheet(errorLogs);
        LogWorkbook workbook = new LogWorkbook(logSheet);
        inputDao.writeWorkbook(outputFolder, DateUtil.getCurrentSSString()+".xls",null,workbook);
        _instance = null;
    }
}
