package com.hoolai.bi.entiy.duration;

import com.hoolai.bi.entiy.ReportType;
import com.hoolai.bi.excel.ExcelDatas;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-11-28 19:50
 */

public class OnlineDurationDatas implements ExcelDatas {
    private ReportType reportType;
    private List<OnlineDuration> onlineDurations;

    public OnlineDurationDatas(ReportType reportType, List<OnlineDuration> onlineDurations) {
        this.reportType = reportType;
        this.onlineDurations = onlineDurations;
    }

    public ReportType getReportType() {
        return reportType;
    }

    public void setReportType(ReportType reportType) {
        this.reportType = reportType;
    }

    public List<OnlineDuration> getOnlineDurations() {
        return onlineDurations;
    }

    public void setOnlineDurations(List<OnlineDuration> onlineDurations) {
        this.onlineDurations = onlineDurations;
    }
}
