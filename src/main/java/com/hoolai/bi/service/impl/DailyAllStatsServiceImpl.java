package com.hoolai.bi.service.impl;

import com.alibaba.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hoolai.bi.entiy.ExcelDatas;
import com.hoolai.bi.entiy.ReportType;
import com.hoolai.bi.entiy.daily.DailyStats;
import com.hoolai.bi.entiy.daily.DailyStatsDatas;
import com.hoolai.bi.mapper.DailyAllStatsMapper;
import com.hoolai.bi.mapper.DailyStatsMapper;
import com.hoolai.bi.service.DailyReportService;
import com.hoolai.bi.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-10-10 16:22
 */

@Service
public class DailyAllStatsServiceImpl implements ReportService {
    @Autowired
    private DailyAllStatsMapper dailyAllStatsMapper;

    @Override
    public ExcelDatas produceDatas(String startDs, String endDs, int gameId) {
        DailyStatsDatas dailyStatsDatas;
        List<DailyStats> dailyStatsList = new ArrayList<DailyStats>(dailyAllStatsMapper.queryAllList(gameId,startDs,endDs));
        dailyStatsDatas = new DailyStatsDatas(dailyStatsList,ReportType.ALL);
        return dailyStatsDatas;
    }
}
