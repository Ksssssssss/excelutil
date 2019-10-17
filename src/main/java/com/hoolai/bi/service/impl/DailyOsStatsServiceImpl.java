package com.hoolai.bi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.hoolai.bi.entiy.ReportType;
import com.hoolai.bi.entiy.daily.DailyOsStats;
import com.hoolai.bi.entiy.daily.DailyStats;
import com.hoolai.bi.entiy.daily.DailyStatsDatas;
import com.hoolai.bi.mapper.DailyOsStatsMapper;
import com.hoolai.bi.service.DailyReportService;
import com.hoolai.bi.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-10-10 16:47
 */

@Service
public class DailyOsStatsServiceImpl implements ReportService {
    @Autowired
    private DailyOsStatsMapper dailyOsStatsMapper;

    @Override
    public DailyStatsDatas produceDatas(String startDs, String endDs, int gameId) {
        DailyStatsDatas dailyStatsDatas;
        List<DailyOsStats> queryDailyOsStatsList = dailyOsStatsMapper.selectList(new LambdaQueryWrapper<DailyOsStats>().ge(DailyStats::getDs, startDs).le(DailyStats::getDs, endDs).eq(DailyStats::getGameid, gameId));
        List<DailyStats> dailyStatsList = new ArrayList<DailyStats>(queryDailyOsStatsList);
        dailyStatsDatas = new DailyStatsDatas(dailyStatsList, ReportType.OS);
        return dailyStatsDatas;
    }
}
