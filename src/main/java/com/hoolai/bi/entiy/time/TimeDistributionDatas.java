package com.hoolai.bi.entiy.time;

import com.hoolai.bi.entiy.ReportType;
import com.hoolai.bi.entiy.retention.ShareRetention;
import com.hoolai.bi.excel.ExcelDatas;
import com.hoolai.bi.excel.info.ExtraType;

import java.util.List;
import java.util.Map;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2020-03-14 10:22
 * 
 */
 
public class TimeDistributionDatas implements ExcelDatas {

    private List<TimeDistribution> timeDistributions;
    private ReportType type;
    private ExtraType extraType;

    public TimeDistributionDatas(List<TimeDistribution> timeDistributions, ReportType type, ExtraType extraType) {
        this.timeDistributions = timeDistributions;
        this.type = type;
        this.extraType = extraType;
    }

    public List<TimeDistribution> getTimeDistributions() {
        return timeDistributions;
    }

    public void setTimeDistributions(List<TimeDistribution> timeDistributions) {
        this.timeDistributions = timeDistributions;
    }

    public ReportType getType() {
        return type;
    }

    public void setType(ReportType type) {
        this.type = type;
    }

    public ExtraType getExtraType() {
        return extraType;
    }

    public void setExtraType(ExtraType extraType) {
        this.extraType = extraType;
    }
}
