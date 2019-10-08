package com.hoolai.bi.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hoolai.bi.entiy.DateUtil;
import com.hoolai.bi.entiy.GameInfo;
import com.hoolai.bi.entiy.daily.DailyOsStats;
import com.hoolai.bi.entiy.daily.DailyStats;
import com.hoolai.bi.entiy.SheetNameEnum;
import com.hoolai.bi.entiy.retention.ShareRetentionResultType;
import com.hoolai.bi.mapper.DailyCreativeStatsMapper;
import com.hoolai.bi.mapper.DailyOsStatsMapper;
import com.hoolai.bi.mapper.DailyStatsMapper;
import com.hoolai.bi.mapper.RetentionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
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

    /**
     *
     * @param response
     * @param startDs
     * @param endDs
     * @param gameId
     * @description 动态生成sheet
     * @throws IOException
     */
    public void repeatedWrite(HttpServletResponse response, String startDs, String endDs, int gameId) throws IOException {
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();

        WriteSheet writeSheet = null;

        Map<SheetNameEnum, List<DailyStats>> datas = reportData(startDs, endDs, gameId);
        Map<String, List<ShareRetentionResultType>> retentionDatas = retentionData(startDs, endDs, gameId);
        List<ShareRetentionResultType> retentionResultTypes = retentionMapper.queryRetens(1, startDs, endDs);
        Map<String, List<ShareRetentionResultType>> retentionMap = retentionResultTypes.stream().collect(Collectors.groupingBy(retention -> retention.getDs()));
        int i = 0;
        for (SheetNameEnum sheetName : datas.keySet()) {
            List<DailyStats> data = datas.get(sheetName);
            Class clazz = data.stream().findFirst().get().getClass();
            writeSheet = EasyExcel.writerSheet(i, sheetName.getName()).head(clazz).build();
            excelWriter.write(data, writeSheet);
            i++;
        }

        writeSheet = EasyExcel.writerSheet(i, SheetNameEnum.RETWNTION.getName()).needHead(Boolean.FALSE).build();
        List<List<Object>> rows = rows(startDs, endDs, retentionMap);
        List<List<String>> headList = headLists(startDs, endDs);
        WriteTable table = EasyExcel.writerTable(0).needHead(true).build();
        table.setHead(headList);
        excelWriter.write(rows, writeSheet, table);
        excelWriter.finish();
    }

    /**
     * @
     * @param startDs
     * @param endDs
     * @param gameId
     * @description:返回日报集合
     * @return
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
        for (String ds = startDs; DateUtil.dateCompare(endDs, ds) >= 0; ds = DateUtil.dateIncr(ds)) {
            List<ShareRetentionResultType> shareRetentionResultTypes = retentionMap.get(ds);
            if (CollectionUtils.isEmpty(shareRetentionResultTypes)) {
                continue;
            }
            intervalDay = DateUtil.dateCompare(endDs, ds);
            List<Object> row = Lists.newLinkedList();
            for (int i = 0; i <= intervalDay; i++) {
                if (intervalDay==0){
                    break;
                }
                if (i == 0) {
                    row.add(ds);
                    continue;
                }
                row.add("_");
            }
            for (ShareRetentionResultType shareRetentionResultType : shareRetentionResultTypes) {
                if (shareRetentionResultType.getDr() <= intervalDay) {
                    row.set(shareRetentionResultType.getDr(), df.format(shareRetentionResultType.getRetentionPercentages()));
                }
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
        for (int i = 0; i <= DateUtil.dateCompare(endDs, startDs); i++) {
            List<String> title = Lists.newArrayListWithCapacity(1);
            if (i == 0) {
                title.add("日期");
            } else {
                title.add(i + "日留存");
            }
            headList.add(title);
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
}
