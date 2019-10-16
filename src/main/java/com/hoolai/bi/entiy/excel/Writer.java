package com.hoolai.bi.entiy.excel;

import com.alibaba.excel.ExcelWriter;
import com.hoolai.bi.entiy.excel.customwriter.CustomExcelWriter;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-10-16 10:10
 * 
 */
 
public abstract class Writer {
    protected ExcelWriter excelWriter;
    protected ExcelStyleStrategy excelStyleStrategy;
    protected CustomExcelWriter customExcelWriter;

    public abstract void write(int index,Object reportDatas);
    public abstract Object produceDatas(String startDs, String endDs, int gameId);

    public ExcelWriter getExcelWriter() {
        return excelWriter;
    }

    public void setExcelWriter(ExcelWriter excelWriter) {
        this.excelWriter = excelWriter;
    }

    public ExcelStyleStrategy getExcelStyleStrategy() {
        return excelStyleStrategy;
    }

    public void setExcelStyleStrategy(ExcelStyleStrategy excelStyleStrategy) {
        this.excelStyleStrategy = excelStyleStrategy;
    }

    public CustomExcelWriter getCustomExcelWriter() {
        return customExcelWriter;
    }

    public void setCustomExcelWriter(CustomExcelWriter customExcelWriter) {
        this.customExcelWriter = customExcelWriter;
    }
}
