package com.hoolai.bi.entiy.excel.behavior;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.hoolai.bi.context.ReportEnvConfig;
import com.hoolai.bi.entiy.daily.DailyStats;
import com.hoolai.bi.entiy.daily.DailyStatsDatas;
import com.hoolai.bi.entiy.excel.ExcelStyleStrategy;
import com.hoolai.bi.entiy.excel.ExcelWriterBehavior;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-10-16 10:43
 * 
 */
@Component
public class DailyOsWriterBehavior extends DailyWriterBehavior {
    @Autowired
    private ReportEnvConfig config;
}
