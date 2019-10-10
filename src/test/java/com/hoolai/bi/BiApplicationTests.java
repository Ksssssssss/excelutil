package com.hoolai.bi;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hoolai.bi.entiy.DateUtil;
import com.hoolai.bi.entiy.daily.DailyOsStats;
import com.hoolai.bi.entiy.daily.DailyStats;
import com.hoolai.bi.entiy.ReportType;
import com.hoolai.bi.mapper.DailyOsStatsMapper;
import com.hoolai.bi.mapper.DailyStatsMapper;
import com.hoolai.bi.mapper.RetentionMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BiApplicationTests {

    @Autowired
    private DailyStatsMapper dailyStatsMapper;
    @Autowired
    private DailyOsStatsMapper dailyOsStatsMapper;
    @Autowired
    private RetentionMapper retentionMapper;

    @Test
    public void contextLoads() {

    }

    public Map<String, List<DailyStats>> reportData(String startDs, String endDs, int gameId) {
        Map<String, List<DailyStats>> result = Maps.newHashMapWithExpectedSize(ReportType.values().length);
        List<DailyStats> dailyStatsList = dailyStatsMapper.selectList(new LambdaQueryWrapper<DailyStats>().ge(DailyStats::getDs, startDs).le(DailyStats::getDs, endDs).eq(DailyStats::getGameid, gameId));
        List<DailyOsStats> queryDailyOsStatsList = dailyOsStatsMapper.selectList(new LambdaQueryWrapper<DailyOsStats>().ge(DailyStats::getDs, startDs).le(DailyStats::getDs, endDs).eq(DailyStats::getGameid, gameId));

        List<DailyStats> dailyOsStatsList = Lists.newArrayListWithCapacity(queryDailyOsStatsList.size());

        for (DailyOsStats dailyOsStats : queryDailyOsStatsList) {
            dailyOsStatsList.add(dailyOsStats);
        }

        result.put(ReportType.ALL.name(), dailyStatsList);
        result.put(ReportType.OS.name(), dailyOsStatsList);
        return result;
    }

    public void dailyStats() {
        List<DailyStats> dailyStats = dailyStatsMapper.selectList(new LambdaQueryWrapper<DailyStats>().lt(DailyStats::getDs, "2019-09-27").gt(DailyStats::getDs, "2019-09-22").eq(DailyStats::getGameid, 1));
        assert dailyStats.size() == 4;
        for (DailyStats dailyStats1 : dailyStats) {
            System.out.println(dailyStats1);
        }
    }


    @Test
    public void date(){
        int day = DateUtil.dateCompare("2019-09-29", "2019-09-26");
        System.out.println(day);
    }

    @Test
    public void enumTest(){
        System.out.println(ReportType.ALL.getName());
    }

}
