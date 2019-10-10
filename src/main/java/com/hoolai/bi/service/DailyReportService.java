package com.hoolai.bi.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.hoolai.bi.entiy.ReportType;
import com.hoolai.bi.entiy.daily.DailyStats;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-10-10 16:43
 */
@Service
public abstract class DailyReportService {
    public void write(String startDs, String endDs, int gameId, ExcelWriter excelWriter, int i, ReportType type){
        WriteSheet writeSheet;
        List<DailyStats> datas =  produceData(startDs, endDs, gameId);
        datas.forEach(dailyStats -> dailyStats.init());
        Class clazz = datas.stream().findFirst().get().getClass();
        writeSheet = EasyExcel.writerSheet(i, type.getName()).head(clazz).build();
        excelWriter.write(datas, writeSheet);
    };

    public abstract List<DailyStats> produceData(String startDs, String endDs, int gameId);
}
