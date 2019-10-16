package com.hoolai.bi.entiy.daily;

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

public class DailyStatsDatas {
    private ReportType type;
    private List<DailyStats> dailyStatsList;

    public List<DailyStats> initAndGetDailyStatsDatas(){
        dailyStatsList.stream().sorted(Comparator.comparing(DailyStats::getDs).reversed()).collect(Collectors.toList());
        dailyStatsList.forEach(dailyStats -> dailyStats.init());
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
