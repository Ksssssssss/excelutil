package com.hoolai.bi.service.impl;

import com.alibaba.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hoolai.bi.entiy.ReportType;
import com.hoolai.bi.entiy.daily.DailyStats;
import com.hoolai.bi.mapper.DailyStatsMapper;
import com.hoolai.bi.service.DailyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-10-10 16:22
 */

@Service
public class DailyStatsServiceImpl extends DailyReportService {
    @Autowired
    private DailyStatsMapper dailyStatsMapper;

    @Override
    public List<DailyStats> produceData(String startDs, String endDs, int gameId) {
        return dailyStatsMapper.selectList(new LambdaQueryWrapper<DailyStats>().ge(DailyStats::getDs, startDs).le(DailyStats::getDs, endDs).eq(DailyStats::getGameid, gameId));
    }
}
