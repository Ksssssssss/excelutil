package com.hoolai.bi.service.impl;

import com.hoolai.bi.entiy.ReportType;
import com.hoolai.bi.entiy.daily.DailyCreativeStats;
import com.hoolai.bi.entiy.daily.DailyStats;
import com.hoolai.bi.entiy.daily.DailyStatsDatas;
import com.hoolai.bi.mapper.DailyCreativeStatsMapper;
import com.hoolai.bi.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-10-10 16:22
 */
@Service
public class DailyCreativeStatsServiceImpl implements ReportService {
    @Autowired
    private DailyCreativeStatsMapper dailyCreativeStatsMapper;

    @Override
    public DailyStatsDatas produceDatas(String startDs, String endDs, int gameId, int snid) {
        DailyStatsDatas dailyStatsDatas;
        List<DailyCreativeStats> dailyCreativeStatsList = dailyCreativeStatsMapper.queryCreativeList(gameId,startDs,endDs);
        List<DailyStats> dailyStatsList = new ArrayList<DailyStats>(dailyCreativeStatsList);

        dailyStatsDatas = new DailyStatsDatas(dailyStatsList,ReportType.CREATIVE);
        HashMap map = new HashMap();
        map.clear();
        return dailyStatsDatas;
    }
}
