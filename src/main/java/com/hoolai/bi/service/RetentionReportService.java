package com.hoolai.bi.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.google.common.collect.Lists;
import com.hoolai.bi.context.ReportEnvConfig;
import com.hoolai.bi.entiy.DateUtil;
import com.hoolai.bi.entiy.ReportType;
import com.hoolai.bi.entiy.retention.RetentionType;
import com.hoolai.bi.entiy.retention.ShareOsRetention;
import com.hoolai.bi.entiy.retention.ShareRetention;
import com.hoolai.bi.entiy.retention.ShareRetentions;
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

public abstract class RetentionReportService {

    @Autowired
    private ReportEnvConfig config;
    DecimalFormat df = new DecimalFormat("0.00%");

    public void writeRetention(String startDs, String endDs, int gameId, ExcelWriter excelWriter, int i, ReportType type) {
        WriteSheet writeSheet;
        ShareRetentions shareRetentions = produceRetention(startDs, endDs, gameId);

        writeSheet = EasyExcel.writerSheet(i, type.getName()).needHead(Boolean.FALSE).build();
        List<List<Object>> rows = rows(startDs, endDs, shareRetentions);
        List<List<String>> headList = headLists(startDs, endDs, shareRetentions.getRetentionType());
        WriteTable table = EasyExcel.writerTable(0).needHead(true).build();
        table.setHead(headList);
        excelWriter.write(rows, writeSheet, table);
    }

    /**
     * @param startDs
     * @param endDs
     * @param gameId
     * @return
     */
    protected abstract ShareRetentions produceRetention(String startDs, String endDs, int gameId);

    /**
     * @param startDs
     * @param endDs
     * @return
     * @description: 动态添加 rows --> 所有行集合
     */
    private List<List<Object>> rows(String startDs, String endDs, ShareRetentions shareRetentions) {
        List<List<Object>> rows = new ArrayList<>();
        int intervalDay = 0;
        int suitDay = chooseSuitDayNum(DateUtil.dateCompare(endDs, startDs));
        for (String ds = endDs; DateUtil.dateCompare(ds, startDs) >= 0; ds = DateUtil.dateDesc(ds)) {
            List<List<ShareRetention>> shareRetentionLists = shareRetentions.groupAndGetKey(ds);
            if (CollectionUtils.isEmpty(shareRetentionLists)) {
                continue;
            }
            intervalDay = DateUtil.dateCompare(endDs, ds);

            intervalDay = chooseSuitDayNum(intervalDay);
            if (intervalDay <= 0) return rows;

            for (List<ShareRetention> shareRetentionList : shareRetentionLists) {
                List<Object> row = fullRow(intervalDay, suitDay, ds, shareRetentionList, shareRetentions.getRetentionType());
                rows.add(new ArrayList<>(row));
            }

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
    private List<Object> fullRow(int intervalDay, int suitDay, String ds, List<ShareRetention> shareRetentions, RetentionType type) {
        List<Object> row = Lists.newLinkedList();
        for (int i = 0; i <= suitDay + intervalDay + type.getNeedRowLength(); i++) {
            if (i == 0) {

                row.add(ds);
                if (type == RetentionType.RETENTION_OS) {
                    row.add(((ShareOsRetention) shareRetentions.stream().findAny().get()).getOs());
                }
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

        for (ShareRetention shareRetention : shareRetentions) {
            row.set(shareRetention.getDr() + type.getNeedRowLength(), df.format(shareRetention.getRetentionPercentages()));
            row.set(suitDay + shareRetention.getDr() + type.getNeedRowLength(), shareRetention.getRetention());
        }
        return row;
    }

    /**
     * @param startDs
     * @param endDs
     * @return
     * @description: 动态添加 head --> 所有head
     */
    private List<List<String>> headLists(String startDs, String endDs, RetentionType type) {
        List<List<String>> headList = new ArrayList<>();
        int suitDay = chooseSuitDayNum(DateUtil.dateCompare(endDs, startDs));

        for (int i = 0; i <= suitDay; i++) {
            if (i == 0) {
                for (String head : type.getNeedExcelHead()) {
                    headList.add(Collections.singletonList(head));
                }

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
        if (intervalDay > config.getMaxRetentionDay()) {
            intervalDay = config.getMaxRetentionDay();
        }
        return intervalDay;
    }
}
