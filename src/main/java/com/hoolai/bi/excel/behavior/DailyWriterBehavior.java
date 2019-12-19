package com.hoolai.bi.excel.behavior;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.hoolai.bi.context.ReportEnvConfig;
import com.hoolai.bi.excel.ExcelDatas;
import com.hoolai.bi.entiy.QueryInfo;
import com.hoolai.bi.entiy.daily.DailyStats;
import com.hoolai.bi.entiy.daily.DailyStatsDatas;
import com.hoolai.bi.excel.info.ExcelStyleStrategy;
import com.hoolai.bi.excel.ExcelWriterBehavior;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-10-16 10:43
 * 
 */
public class DailyWriterBehavior implements ExcelWriterBehavior {
    private ReportEnvConfig config;

    public DailyWriterBehavior(ReportEnvConfig config) {
        this.config = config;
    }

    @Override
    public void write(int index, ExcelDatas reportDatas, ExcelWriter excelWriter, ExcelStyleStrategy excelStyleStrategy, QueryInfo info) {
        WriteSheet writeSheet;
        DailyStatsDatas dailyStatsDatas =  (DailyStatsDatas)reportDatas;
        List<DailyStats> datas = dailyStatsDatas.initAndGetDailyStatsDatas(config);
        if (CollectionUtils.isEmpty(datas)){
            return;
        }
        Class clazz = datas.stream().findFirst().get().getClass();
        writeSheet = EasyExcel.writerSheet(index, dailyStatsDatas.getType().getName()).registerWriteHandler(excelStyleStrategy.customCellStyle()).head(clazz).build();
        excelWriter.write(datas, writeSheet);
    }
}
