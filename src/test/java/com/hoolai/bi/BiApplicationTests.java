package com.hoolai.bi;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hoolai.bi.entiy.DateUtil;
import com.hoolai.bi.entiy.daily.DailyOsStats;
import com.hoolai.bi.entiy.daily.DailyStats;
import com.hoolai.bi.entiy.SheetNameEnum;
import com.hoolai.bi.entiy.retention.ShareRetentionResultType;
import com.hoolai.bi.excel.GenerateExcelService;
import com.hoolai.bi.mapper.DailyOsStatsMapper;
import com.hoolai.bi.mapper.DailyStatsMapper;
import com.hoolai.bi.mapper.RetentionMapper;
import javafx.beans.binding.ObjectExpression;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

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
    private GenerateExcelService excelService;

    @Test
    public void contextLoads() {

    }

    public Map<String, List<DailyStats>> reportData(String startDs, String endDs, int gameId) {
        Map<String, List<DailyStats>> result = Maps.newHashMapWithExpectedSize(SheetNameEnum.values().length);
        List<DailyStats> dailyStatsList = dailyStatsMapper.selectList(new LambdaQueryWrapper<DailyStats>().ge(DailyStats::getDs, startDs).le(DailyStats::getDs, endDs).eq(DailyStats::getGameid, gameId));
        List<DailyOsStats> queryDailyOsStatsList = dailyOsStatsMapper.selectList(new LambdaQueryWrapper<DailyOsStats>().ge(DailyStats::getDs, startDs).le(DailyStats::getDs, endDs).eq(DailyStats::getGameid, gameId));

        List<DailyStats> dailyOsStatsList = Lists.newArrayListWithCapacity(queryDailyOsStatsList.size());

        for (DailyOsStats dailyOsStats : queryDailyOsStatsList) {
            dailyOsStatsList.add(dailyOsStats);
        }

        result.put(SheetNameEnum.ALL.name(), dailyStatsList);
        result.put(SheetNameEnum.OS.name(), dailyOsStatsList);
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
        System.out.println(SheetNameEnum.ALL.getName());
    }

}
