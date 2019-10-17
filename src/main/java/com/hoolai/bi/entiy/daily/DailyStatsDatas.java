package com.hoolai.bi.entiy.daily;

import com.hoolai.bi.context.ReportEnvConfig;
import com.hoolai.bi.entiy.ExcelDatas;
import com.hoolai.bi.entiy.ExtraType;
import com.hoolai.bi.entiy.ReportType;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-10-16 10:48
 */

public class DailyStatsDatas implements ExcelDatas {
    private ReportType type;
    private List<DailyStats> dailyStatsList;

    public DailyStatsDatas( List<DailyStats> dailyStatsList,ReportType type) {
        this.type = type;
        this.dailyStatsList = dailyStatsList;
    }

    public List<DailyStats> initAndGetDailyStatsDatas(ReportEnvConfig config){
        dailyStatsList = dailyStatsList.stream().sorted(Comparator.comparing(DailyStats::getDs).reversed()).collect(Collectors.toList());
        dailyStatsList.forEach(dailyStats -> dailyStats.init(config));
        return dailyStatsList;
    }

    public ReportType getType() {
        return type;
    }

    public void setType(ReportType type) {
        this.type = type;
    }

    public List<DailyStats> getDailyStatsList() {
        return dailyStatsList;
    }

    public void setDailyStatsList(List<DailyStats> dailyStatsList) {
        this.dailyStatsList = dailyStatsList;
    }
}
