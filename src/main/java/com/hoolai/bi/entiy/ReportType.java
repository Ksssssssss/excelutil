package com.hoolai.bi.entiy;

import com.alibaba.excel.ExcelWriter;
import com.hoolai.bi.context.SpringUtils;
import com.hoolai.bi.service.impl.*;
import com.hoolai.bi.service.RetentionReportService;

/**
 *
 *@description: 表名
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-09-29 15:41
 * 
 */
 
public enum ReportType {
    ALL("总览") {
        @Override
        public void write(String startDs, String endDs, int gameId, ExcelWriter excelWriter, int i) {
            SpringUtils.getBean(DailyStatsServiceImpl.class).write(startDs, endDs, gameId, excelWriter, i,this);
        }
    },
    RETWNTION("留存"){
        @Override
        public void write(String startDs, String endDs, int gameId, ExcelWriter excelWriter, int i) {
            SpringUtils.getBean(RetentionServiceImpl.class).writeRetention(startDs, endDs, gameId, excelWriter, i,this);
        }
    },
    RETWNTION_OS("留存(按操作系统)"){
        @Override
        public void write(String startDs, String endDs, int gameId, ExcelWriter excelWriter, int i) {
            SpringUtils.getBean(RetentionOsServiceImpl.class).writeRetention(startDs, endDs, gameId, excelWriter, i,this);
        }
    },
    OS("操作系统"){
        @Override
        public void write(String startDs, String endDs, int gameId, ExcelWriter excelWriter, int i) {
            SpringUtils.getBean(DailyOsStatsServiceImpl.class).write(startDs, endDs, gameId, excelWriter, i,this);
        }
    },
    CREATIVE("渠道"){
        @Override
        public void write(String startDs, String endDs, int gameId, ExcelWriter excelWriter, int i) {
            SpringUtils.getBean(DailyCreativeStatsServiceImpl.class).write(startDs, endDs, gameId, excelWriter, i,this);
        }
    },
//    RETENTION_OS("留存(操作系统)")
    ;

    private String name;
    private ReportType(String name){
        this.name = name;
    }

    public abstract void write(String startDs, String endDs, int gameId, ExcelWriter excelWriter, int i);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }}
