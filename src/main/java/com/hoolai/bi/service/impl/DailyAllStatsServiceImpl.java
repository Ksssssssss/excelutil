package com.hoolai.bi.service.impl;

import com.hoolai.bi.entiy.daily.*;
import com.hoolai.bi.excel.ExcelDatas;
import com.hoolai.bi.entiy.ReportType;
import com.hoolai.bi.mapper.DailyAllStatsMapper;
import com.hoolai.bi.mapper.ExtraMapper;
import com.hoolai.bi.service.ReportService;
import com.hoolai.bi.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-10-10 16:22
 */

@Service
public class DailyAllStatsServiceImpl implements ReportService {
    @Autowired
    private DailyAllStatsMapper dailyAllStatsMapper;
    @Autowired
    private ExtraMapper extraMapper;

    @Override
    public ExcelDatas produceDatas(String startDs, String endDs, int gameId, int snid) {
        DailyStatsDatas dailyStatsDatas;
        List<DailyStats> dailyStatsList = new ArrayList(dailyAllStatsMapper.queryAllList(gameId, startDs, endDs));
        dailyStatsList = dailyStatsList.stream().sorted(Comparator.comparing(DailyStats::getDs).reversed()).collect(Collectors.toList());
        int intervalDay = DateUtil.dateCompare(endDs, startDs);

        List<MonthInfo> monthStats = extraMapper.queryMus(gameId, startDs, endDs);
        List<WeekInfo> weekStats = extraMapper.queryWus(gameId, startDs, endDs);
        Map<String, MonthInfo> monthStatsMap = monthStats.stream().collect(Collectors.toMap(m -> m.getDs(), m -> m));
        Map<String, WeekInfo> weekStatsMap = weekStats.stream().collect(Collectors.toMap(w -> w.getDs(), w -> w));

        if (dailyStatsList != null && dailyStatsList.size() > 0 && intervalDay >= 0) {
            for (String ds = startDs; DateUtil.dateCompare(endDs, ds) >= 0; ds = DateUtil.dateCalculate(ds, 1)) {
                for (DailyStats dailyStats : dailyStatsList) {
                    if (dailyStats.getDs().equals(ds)) {
                        MonthInfo monthInfo = monthStatsMap.get(ds);
                        WeekInfo wweekInfo = weekStatsMap.get(ds);

                        if ( monthInfo != null && wweekInfo != null){
                              ((DailyAllStats) dailyStats).initExtra(wweekInfo, monthInfo);
                        }
                    }
                }
            }
        }

        dailyStatsDatas = new DailyStatsDatas(dailyStatsList, ReportType.ALL);
        return dailyStatsDatas;
    }
}
