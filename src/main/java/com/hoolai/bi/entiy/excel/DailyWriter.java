package com.hoolai.bi.entiy.excel;

import com.alibaba.excel.ExcelWriter;
import com.hoolai.bi.entiy.excel.customwriter.CustomExcelWriter;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-10-16 09:53
 */

public class DailyWriter extends Writer {

    public DailyWriter(ExcelWriter excelWriter, ExcelStyleStrategy excelStyleStrategy, CustomExcelWriter customWriter) {
        this.excelWriter = excelWriter;
        this.excelStyleStrategy = excelStyleStrategy;
        this.customExcelWriter = customWriter;
    }

    @Override
    public void write(int index, Object reportDatas) {
        customExcelWriter.write(index,reportDatas,excelWriter,excelStyleStrategy);
    }

    @Override
    public Object produceDatas(String startDs, String endDs, int gameId) {
        return new Object();
    }
}
