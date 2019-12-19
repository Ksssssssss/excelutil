package com.hoolai.bi.excel.behavior;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.hoolai.bi.entiy.QueryInfo;
import com.hoolai.bi.entiy.daily.DailyStats;
import com.hoolai.bi.entiy.daily.DailyStatsDatas;
import com.hoolai.bi.entiy.duration.OnlineDuration;
import com.hoolai.bi.entiy.duration.OnlineDurationDatas;
import com.hoolai.bi.excel.ExcelDatas;
import com.hoolai.bi.excel.ExcelWriterBehavior;
import com.hoolai.bi.excel.info.ExcelStyleStrategy;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-11-28 20:14
 * 
 */

public class OnlineWriterBehavior implements ExcelWriterBehavior {

    @Override
    public void write(int index, ExcelDatas reportDatas, ExcelWriter excelWriter, ExcelStyleStrategy excelStyleStrategy, QueryInfo info) {
        WriteSheet writeSheet;
        OnlineDurationDatas onlineDurationDatas =  (OnlineDurationDatas) reportDatas;
        List<OnlineDuration> datas = onlineDurationDatas.getOnlineDurations();
        if (CollectionUtils.isEmpty(datas)){
            return;
        }
        datas = datas.stream().sorted(Comparator.comparing(OnlineDuration::getDs).reversed().thenComparing(OnlineDuration::getDuration)).collect(Collectors.toList());
        Class clazz = datas.stream().findFirst().get().getClass();
        writeSheet = EasyExcel.writerSheet(index, onlineDurationDatas.getReportType().getName()).registerWriteHandler(excelStyleStrategy.customCellStyle()).head(clazz).build();
        excelWriter.write(datas, writeSheet);
    }
}
