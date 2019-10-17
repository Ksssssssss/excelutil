package com.hoolai.bi.entiy.excel.behavior;

import com.hoolai.bi.context.ReportEnvConfig;
import com.hoolai.bi.entiy.excel.ExcelWriterBehavior;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-10-16 17:17
 * 
 */
 
@Component
public class DailyCreativeBehavior extends DailyWriterBehavior {
    @Autowired
    private ReportEnvConfig config;
}
