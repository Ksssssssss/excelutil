package com.hoolai.bi.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
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
import com.hoolai.bi.mapper.DailyCreativeStatsMapper;
import com.hoolai.bi.mapper.DailyOsStatsMapper;
import com.hoolai.bi.mapper.DailyStatsMapper;
import com.hoolai.bi.mapper.RetentionMapper;
import org.ehcache.core.internal.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @description: excel报表
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-09-29 14:32
 */

@Service
public class GenerateExcelService {

    @Autowired
    private DailyStatsMapper dailyStatsMapper;
    @Autowired
    private DailyOsStatsMapper dailyOsStatsMapper;
    @Autowired
    private DailyCreativeStatsMapper dailyCreativeStatsMapper;
    @Autowired
    private RetentionMapper retentionMapper;
    private static final int SUIT_DAY = 30;

    /**
     * @param response
     * @param startDs
     * @param endDs
     * @param gameId
     * @throws IOException
     * @description 动态生成sheet
     */
    public void repeatedWrite(HttpServletResponse response, String startDs, String endDs, int gameId) throws IOException {
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();

        WriteSheet writeSheet = null;

        Map<SheetNameEnum, List<DailyStats>> datas = reportData(startDs, endDs, gameId);
        int i = 0;
        for (SheetNameEnum sheetName : datas.keySet()) {
            List<DailyStats> data = datas.get(sheetName);
            Class clazz = data.stream().findFirst().get().getClass();
            writeSheet = EasyExcel.writerSheet(i, sheetName.getName()).head(clazz).build();
            excelWriter.write(data, writeSheet);
            i++;
        }

        List<ShareRetentionResultType> retentionResultTypes = retentionMapper.queryRetens(gameId, startDs, endDs);
        Map<String, List<ShareRetentionResultType>> retentionMap = retentionResultTypes.stream().collect(Collectors.groupingBy(retention -> retention.getDs()));

        writeSheet = EasyExcel.writerSheet(i, SheetNameEnum.RETWNTION.getName()).needHead(Boolean.FALSE).build();
        List<List<Object>> rows = rows(startDs, endDs, retentionMap);
        List<List<String>> headList = headLists(startDs, endDs);
        WriteTable table = EasyExcel.writerTable(0).needHead(true).build();
        table.setHead(headList);
        excelWriter.write(rows, writeSheet, table);
        excelWriter.finish();
    }

    /**
     * @param startDs
     * @param endDs
     * @param gameId
     * @return
     * @
     * @description:返回日报集合
     */
    public Map<SheetNameEnum, List<DailyStats>> reportData(String startDs, String endDs, int gameId) {
        EnumMap<SheetNameEnum, List<DailyStats>> result = Maps.newEnumMap(SheetNameEnum.class);
        List<DailyStats> dailyStatsList = dailyStats(startDs, endDs, gameId);
        List<DailyStats> dailyOsStatsList = dailyOsStats(startDs, endDs, gameId);
        result.put(SheetNameEnum.ALL, dailyStatsList);
        result.put(SheetNameEnum.OS, dailyOsStatsList);
        return result;
    }

    /**
     * @param startDs
     * @param endDs
     * @return
     * @description: 动态添加 rows --> 所有行集合
     */
    private List<List<Object>> rows(String startDs, String endDs, Map<String, List<ShareRetentionResultType>> retentionMap) {
        List<List<Object>> rows = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("0.00%");
        int intervalDay = 0;
        int suitDay = chooseSuitDayNum(DateUtil.dateCompare(endDs, startDs));
        for (String ds = startDs; DateUtil.dateCompare(endDs, ds) >= 0; ds = DateUtil.dateIncr(ds)) {
            List<ShareRetentionResultType> shareRetentionResultTypes = retentionMap.get(ds);
            if (CollectionUtils.isEmpty(shareRetentionResultTypes)) {
                continue;
            }
            intervalDay = DateUtil.dateCompare(endDs, ds);

            intervalDay = chooseSuitDayNum(intervalDay);
            if (intervalDay <= 0) return rows;

            List<Object> row = Lists.newLinkedList();

            for (int i = 0; i <= suitDay + intervalDay; i++) {
                if (i == 0) {
                    row.add(ds);
                    row.add(shareRetentionResultTypes.stream().findAny().get().getInstallNum());
                    continue;
                }
                if (i <= intervalDay) {
                    row.add("0%");
                    continue;
                }
                if (i > suitDay && i <= suitDay + intervalDay) {
                    row.add(0);
                    continue;
                }
                row.add("");
            }

            for (ShareRetentionResultType shareRetentionResultType : shareRetentionResultTypes) {
                row.set(shareRetentionResultType.getDr() + 1, df.format(shareRetentionResultType.getRetentionPercentages()));
                row.set(suitDay + shareRetentionResultType.getDr() + 1, shareRetentionResultType.getRetention());
            }
            rows.add(new ArrayList<>(row));
        }
        return rows;
    }

    /**
     * @param startDs
     * @param endDs
     * @return
     * @description: 动态添加 head --> 所有head
     */
    private List<List<String>> headLists(String startDs, String endDs) {
        List<List<String>> headList = new ArrayList<>();
        int suitDay = chooseSuitDayNum(DateUtil.dateCompare(endDs, startDs));

        for (int i = 0; i <= suitDay; i++) {
            if (i == 0) {
                headList.add(Collections.singletonList("日期"));
                headList.add(Collections.singletonList("安装数"));
                continue;
            }
            headList.add(Collections.singletonList(i + "日留存"));
        }

        for (int i = 1; i <= suitDay; i++) {
            headList.add(Collections.singletonList(i + "日留存人数"));
        }
        return headList;
    }


    private Map<String, List<ShareRetentionResultType>> retentionData(String startDs, String endDs, int gameId) {
        Map<String, List<ShareRetentionResultType>> retentionMap = retentionMap(startDs, endDs, gameId);
        return retentionMap;
    }

    private List<DailyStats> dailyStats(String startDs, String endDs, int gameId) {
        return dailyStatsMapper.selectList(new LambdaQueryWrapper<DailyStats>().ge(DailyStats::getDs, startDs).le(DailyStats::getDs, endDs).eq(DailyStats::getGameid, gameId));
    }

    private List<DailyStats> dailyOsStats(String startDs, String endDs, int gameId) {
        List<DailyOsStats> queryDailyOsStatsList = dailyOsStatsMapper.selectList(new LambdaQueryWrapper<DailyOsStats>().ge(DailyStats::getDs, startDs).le(DailyStats::getDs, endDs).eq(DailyStats::getGameid, gameId));

        List<DailyStats> dailyOsStatsList = Lists.newArrayListWithCapacity(queryDailyOsStatsList.size());
        queryDailyOsStatsList.forEach(dailyOsStats -> dailyOsStatsList.add(dailyOsStats));
        return dailyOsStatsList;
    }

    private Map<String, List<ShareRetentionResultType>> retentionMap(String startDs, String endDs, int gameId) {
        Map<String, List<ShareRetentionResultType>> result = Maps.newHashMap();
        result = retentionMapper.queryRetens(gameId, startDs, endDs).stream().collect(Collectors.groupingBy(retention -> retention.getDs() + gameId));
        return result;
    }


    private int chooseSuitDayNum(int intervalDay) {
        if (intervalDay > SUIT_DAY) {
            intervalDay = SUIT_DAY;
        }
        return intervalDay;
    }

}
