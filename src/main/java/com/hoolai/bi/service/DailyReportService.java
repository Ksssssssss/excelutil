package com.hoolai.bi.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.annotation.TableField;
import com.hoolai.bi.context.ReportEnvConfig;
import com.hoolai.bi.entiy.ReportType;
import com.hoolai.bi.entiy.daily.DailyStats;
import com.hoolai.bi.entiy.excel.ExcelStyleStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-10-10 16:43
 */
@Service
public abstract class DailyReportService {

    @Autowired
    private ExcelStyleStrategy excelStyleStrategy;

    public void write(String startDs, String endDs, int gameId, ExcelWriter excelWriter, int i, ReportType type){
        WriteSheet writeSheet;
        List<DailyStats> datas =  produceData(startDs, endDs, gameId);
        datas = datas.stream().sorted(Comparator.comparing(DailyStats::getDs).reversed()).collect(Collectors.toList());
        datas.forEach(dailyStats -> dailyStats.init());
        Class clazz = datas.stream().findFirst().get().getClass();
        writeSheet = EasyExcel.writerSheet(i, type.getName()).registerWriteHandler(excelStyleStrategy.customCellStyle()).head(clazz).build();
        excelWriter.write(datas, writeSheet);
    };

    public abstract List<DailyStats> produceData(String startDs, String endDs, int gameId);
}
