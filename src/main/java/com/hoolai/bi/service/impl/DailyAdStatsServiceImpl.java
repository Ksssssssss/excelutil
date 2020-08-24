package com.hoolai.bi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hoolai.bi.entiy.ReportType;
import com.hoolai.bi.entiy.daily.DailyAdStats;
import com.hoolai.bi.entiy.daily.DailyStats;
import com.hoolai.bi.entiy.daily.DailyStatsDatas;
import com.hoolai.bi.mapper.DailyAdStatsMapper;
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
public class DailyAdStatsServiceImpl implements ReportService {
    @Autowired
    private DailyAdStatsMapper dailyAdStatsMapper;

    @Override
    public DailyStatsDatas produceDatas(String startDs, String endDs, int gameId, int snid) {
        DailyStatsDatas dailyStatsDatas;
        List<DailyAdStats> queryDailyAdStatsList = dailyAdStatsMapper.selectList(new LambdaQueryWrapper<DailyAdStats>().ge(DailyStats::getDs, startDs).le(DailyStats::getDs, endDs).eq(DailyStats::getGameid, gameId));
        List<DailyStats> dailyStatsList = new ArrayList<DailyStats>(queryDailyAdStatsList);
        dailyStatsDatas = new DailyStatsDatas(dailyStatsList, ReportType.AD);
        return dailyStatsDatas;
    }
}
