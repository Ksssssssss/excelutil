package com.hoolai.bi.entiy;

import com.hoolai.bi.entiy.GameInfo;
import com.hoolai.bi.entiy.ReportType;
import com.hoolai.bi.entiy.ad.AdTracking;
import com.hoolai.bi.excel.ExcelDatas;
import lombok.Data;

import java.util.List;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-12-19 10:56
 * 
 */

@Data
public class Datas implements ExcelDatas {
    private ReportType reportType;
    private List<? extends GameInfo> infos;

    public Datas(ReportType reportType, List<? extends GameInfo> infos) {
        this.reportType = reportType;
        this.infos = infos;
    }
}