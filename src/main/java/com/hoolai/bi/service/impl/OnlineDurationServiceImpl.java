package com.hoolai.bi.service.impl;

import com.hoolai.bi.entiy.ReportType;
import com.hoolai.bi.entiy.daily.DailyAllStats;
import com.hoolai.bi.entiy.duration.OnlineDuration;
import com.hoolai.bi.entiy.duration.OnlineDurationDatas;
import com.hoolai.bi.excel.ExcelDatas;
import com.hoolai.bi.mapper.DailyAllStatsMapper;
import com.hoolai.bi.mapper.OnlineDurationMapper;
import com.hoolai.bi.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-11-04 20:36
 */

@Service
public class OnlineDurationServiceImpl implements ReportService {

    @Autowired
    private OnlineDurationMapper onlineDurationMapper;
    @Autowired
    private DailyAllStatsMapper dailyAllStatsMapper;

    @Override
    public ExcelDatas produceDatas(String startDs, String endDs, int gameId, int snid) {
        List<OnlineDuration> onlineDurations = onlineDurationMapper.query(gameId, startDs, endDs);
        List<DailyAllStats> dailyAllStats = dailyAllStatsMapper.queryAllList(gameId, startDs, endDs);
        Map<String, DailyAllStats> dailyAllStatMap = dailyAllStats.stream().collect(Collectors.toMap(d -> d.getDs(), d -> d));

        return new OnlineDurationDatas(ReportType.ONLINE_DURATION_DISTRIBUTION,onlineDurations,dailyAllStatMap);
    }
}
