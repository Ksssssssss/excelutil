package com.hoolai.bi.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.google.common.collect.Lists;
import com.hoolai.bi.entiy.DateUtil;
import com.hoolai.bi.entiy.ReportType;
import com.hoolai.bi.entiy.retention.ShareRetention;
import com.hoolai.bi.mapper.RetentionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-10-10 16:00
 */

@Service
public class RetentionServiceImpl {
    @Autowired
    private RetentionMapper retentionMapper;
    private static final int SUIT_DAY = 30;

    public void writeRetention(String startDs, String endDs, int gameId, ExcelWriter excelWriter, int i,ReportType type) {
        WriteSheet writeSheet;
        Map<String, List<ShareRetention>> retentionMap = produceRetention(startDs, endDs, gameId);

        writeSheet = EasyExcel.writerSheet(i, type.getName()).needHead(Boolean.FALSE).build();
        List<List<Object>> rows = rows(startDs, endDs, retentionMap);
        List<List<String>> headList = headLists(startDs, endDs);
        WriteTable table = EasyExcel.writerTable(0).needHead(true).build();
        table.setHead(headList);
        excelWriter.write(rows, writeSheet, table);
    }

    private Map<String, List<ShareRetention>> produceRetention(String startDs, String endDs, int gameId) {
        List<ShareRetention> retentionResultTypes = retentionMapper.queryRetens(gameId, startDs, endDs);
        return retentionResultTypes.stream().collect(Collectors.groupingBy(retention -> retention.getDs()));
    }

    /**
     * @param startDs
     * @param endDs
     * @return
     * @description: 动态添加 rows --> 所有行集合
     */
    private List<List<Object>> rows(String startDs, String endDs, Map<String, List<ShareRetention>> retentionMap) {
        List<List<Object>> rows = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("0.00%");
        int intervalDay = 0;
        int suitDay = chooseSuitDayNum(DateUtil.dateCompare(endDs, startDs));
        for (String ds = startDs; DateUtil.dateCompare(endDs, ds) >= 0; ds = DateUtil.dateIncr(ds)) {
            List<ShareRetention> shareRetentions = retentionMap.get(ds);
            if (CollectionUtils.isEmpty(shareRetentions)) {
                continue;
            }
            intervalDay = DateUtil.dateCompare(endDs, ds);

            intervalDay = chooseSuitDayNum(intervalDay);
            if (intervalDay <= 0) return rows;

            List<Object> row = fullRow(intervalDay, suitDay, ds, shareRetentions);

            for (ShareRetention shareRetention : shareRetentions) {
                row.set(shareRetention.getDr() + 1, df.format(shareRetention.getRetentionPercentages()));
                row.set(suitDay + shareRetention.getDr() + 1, shareRetention.getRetention());
            }
            rows.add(new ArrayList<>(row));
        }
        return rows;
    }

    /**
     * @param intervalDay
     * @param suitDay
     * @param ds
     * @param shareRetentions
     * @return
     * @description 动态填充row
     */
    private List<Object> fullRow(int intervalDay, int suitDay, String ds, List<ShareRetention> shareRetentions) {
        List<Object> row = Lists.newLinkedList();

        for (int i = 0; i <= suitDay + intervalDay + 1; i++) {
            if (i == 0) {
                row.add(ds);
                row.add(shareRetentions.stream().findAny().get().getInstallNum());
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
        return row;
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

    private int chooseSuitDayNum(int intervalDay) {
        if (intervalDay > SUIT_DAY) {
            intervalDay = SUIT_DAY;
        }
        return intervalDay;
    }
}
