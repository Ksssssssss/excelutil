package com.hoolai.bi.entiy;

import com.hoolai.bi.entiy.GameInfo;
import com.hoolai.bi.entiy.ReportType;
import com.hoolai.bi.entiy.ad.AdTracking;
import com.hoolai.bi.excel.ExcelDatas;

import java.util.List;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-12-19 10:56
 * 
 */
 
public class Datas implements ExcelDatas {
    private ReportType reportType;
    private List<? extends GameInfo> infos;

    public Datas(ReportType reportType, List<? extends GameInfo> infos) {
        this.reportType = reportType;
        this.infos = infos;
    }

    public ReportType getReportType() {
        return reportType;
    }

    public void setReportType(ReportType reportType) {
        this.reportType = reportType;
    }

    public List<? extends GameInfo> getInfos() {
        return infos;
    }

    public void setInfos(List<? extends GameInfo> infos) {
        this.infos = infos;
    }
}
