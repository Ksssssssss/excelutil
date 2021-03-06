package com.hoolai.bi;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hoolai.bi.context.ReportEnvConfig;
import com.hoolai.bi.util.DateUtil;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BiApplicationTests {

    @Autowired
    private DailyStatsMapper dailyStatsMapper;
    @Autowired
    private DailyOsStatsMapper dailyOsStatsMapper;
    @Autowired
    private RetentionMapper retentionMapper;
    @Autowired
    private ReportEnvConfig reportEnvConfig;

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
    public void calculate() {
        String m = "5-10";
        String n = "10-20";
        System.out.println(m.getBytes());
        System.out.println(n.getBytes());
//        System.out.println(Integer.parseInt(n));

        System.out.println(m.compareTo(n));
    }

    @Test
    public void round() {
        Date date = new Date();
        System.out.println(new Random().nextInt(10));
    }

    @Test
    public void date() {
        long start = System.currentTimeMillis();
        System.out.println("start::" + System.currentTimeMillis());

        long count = 0l;
        for (long i = 0l; i < Integer.MAX_VALUE; i++) {
            count += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("end::" + System.currentTimeMillis());
        System.out.println(end - start);
    }

    @Test
    public void regex() {

//        Pattern pattern = Pattern.compile("\\d+");
//
//        Matcher matcher = pattern.matcher("1-5分钟");
//        while (matcher.find()) {
//            System.out.println(matcher.group(0));
//        }

        Set<String> keys = new HashSet<>();
        keys.add("1-5分钟");
        keys.add("5-10分钟");
        keys.add("大于30分钟");

        Pattern pattern = Pattern.compile("\\d+");
//        String
//
//        Matcher matcher2 = pattern.matcher(k1);
//        int res2 = 0;
//        while (matcher2.find()) {
//            res1 = Integer.parseInt(matcher2.group(0));
//            break;
//        }
//        return res1 - res2;
//    });
}

    @Test
    public void enumTest() {
        System.out.println(ReportType.ALL.getName());
    }

}
