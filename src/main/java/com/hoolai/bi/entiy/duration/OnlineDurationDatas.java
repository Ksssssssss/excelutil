package com.hoolai.bi.entiy.duration;

import com.hoolai.bi.entiy.ReportType;
import com.hoolai.bi.entiy.daily.DailyAllStats;
import com.hoolai.bi.excel.ExcelDatas;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-11-28 19:50
 */
@Data
public class OnlineDurationDatas implements ExcelDatas {
    private ReportType reportType;
    private List<OnlineDuration> onlineDurations;
    private Map<String, DailyAllStats> dailyAllStatMap;

    public OnlineDurationDatas(ReportType reportType, List<OnlineDuration> onlineDurations,Map<String, DailyAllStats> dailyAllStatMap) {
        this.reportType = reportType;
        this.onlineDurations = onlineDurations;
        this.dailyAllStatMap = dailyAllStatMap;
    }

}
