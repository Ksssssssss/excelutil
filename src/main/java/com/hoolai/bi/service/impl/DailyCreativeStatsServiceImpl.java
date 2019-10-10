package com.hoolai.bi.service.impl;

import com.alibaba.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.hoolai.bi.entiy.ReportType;
import com.hoolai.bi.entiy.daily.DailyCreativeStats;
import com.hoolai.bi.entiy.daily.DailyOsStats;
import com.hoolai.bi.entiy.daily.DailyStats;
import com.hoolai.bi.mapper.DailyCreativeStatsMapper;
import com.hoolai.bi.mapper.DailyStatsMapper;
import com.hoolai.bi.service.DailyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-10-10 16:22
 */
@Service
public class DailyCreativeStatsServiceImpl extends DailyReportService {
    @Autowired
    private DailyCreativeStatsMapper dailyCreativeStatsMapper;

    @Override
    public List<DailyStats> produceData(String startDs, String endDs, int gameId) {
        List<DailyCreativeStats> dailyCreativeStatsList = dailyCreativeStatsMapper.queryCreativeList(gameId,startDs,endDs);

        List<DailyStats> dailyOsStatsList = Lists.newArrayListWithCapacity(dailyCreativeStatsList.size());
        dailyCreativeStatsList.forEach(dailyOsStats -> dailyOsStatsList.add(dailyOsStats));
        return dailyOsStatsList;
    }
}
