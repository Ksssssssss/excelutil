package com.hoolai.bi.entiy.excel.customwriter;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.hoolai.bi.entiy.daily.DailyStats;
import com.hoolai.bi.entiy.daily.DailyStatsDatas;
import com.hoolai.bi.entiy.excel.ExcelStyleStrategy;

import java.util.List;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-10-16 10:43
 * 
 */
 
public class CustomDailyWriter implements CustomExcelWriter {

    @Override
    public void write(int index, Object reportDatas, ExcelWriter excelWriter, ExcelStyleStrategy excelStyleStrategy) {
        WriteSheet writeSheet;
        DailyStatsDatas dailyStatsDatas =  (DailyStatsDatas)reportDatas;
        List<DailyStats> datas = dailyStatsDatas.initAndGetDailyStatsDatas();
        Class clazz = datas.stream().findFirst().get().getClass();
        writeSheet = EasyExcel.writerSheet(index, dailyStatsDatas.getType().getName()).registerWriteHandler(excelStyleStrategy.customCellStyle()).head(clazz).build();
        excelWriter.write(datas, writeSheet);
    }
}
