package com.hoolai.bi.entiy.device;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.hoolai.bi.context.ReportEnvConfig;
import com.hoolai.bi.entiy.QueryInfo;
import com.hoolai.bi.entiy.daily.DailyStats;
import com.hoolai.bi.entiy.daily.DailyStatsDatas;
import com.hoolai.bi.entiy.device.DeviceDatas;
import com.hoolai.bi.entiy.device.DeviceDistribution;
import com.hoolai.bi.excel.ExcelDatas;
import com.hoolai.bi.excel.ExcelWriterBehavior;
import com.hoolai.bi.excel.info.ExcelStyleStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-11-28 17:09
 * 
 */
public class DeviceWriterBehavior implements ExcelWriterBehavior {
    @Override
    public void write(int index, ExcelDatas reportDatas, ExcelWriter excelWriter, ExcelStyleStrategy excelStyleStrategy, QueryInfo info) {
        WriteSheet writeSheet;
        DeviceDatas deviceDatas =  (DeviceDatas)reportDatas;
        List<DeviceDistribution> datas = deviceDatas.getDeviceDistributions();
        if (CollectionUtils.isEmpty(datas)){
            return;
        }
        Class clazz = datas.stream().findFirst().get().getClass();
        writeSheet = EasyExcel.writerSheet(index, deviceDatas.getType().getName()).registerWriteHandler(excelStyleStrategy.customCellStyle()).head(clazz).build();
        excelWriter.write(datas, writeSheet);
    }
}