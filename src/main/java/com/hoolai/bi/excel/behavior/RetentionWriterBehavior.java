package com.hoolai.bi.excel.behavior;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.google.common.collect.Lists;
import com.hoolai.bi.context.ReportEnvConfig;
import com.hoolai.bi.entiy.retention.ShareRetention;
import com.hoolai.bi.excel.ExcelDatas;
import com.hoolai.bi.entiy.QueryInfo;
import com.hoolai.bi.excel.info.ExcelStyleStrategy;
import com.hoolai.bi.excel.ExcelWriterBehavior;
import com.hoolai.bi.entiy.retention.RetentionDatas;
import com.hoolai.bi.excel.info.ExtraType;
import com.hoolai.bi.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-10-16 17:22
 * 
 */

public class RetentionWriterBehavior implements ExcelWriterBehavior {

    private DecimalFormat df = new DecimalFormat("0.00%");
    private ReportEnvConfig config;

    public RetentionWriterBehavior(ReportEnvConfig config) {
        this.config = config;
    }

    @Override
    public void write(int index, ExcelDatas reportDatas, ExcelWriter excelWriter, ExcelStyleStrategy excelStyleStrategy, QueryInfo info) {
        WriteSheet writeSheet;
        RetentionDatas retentionDatas = (RetentionDatas)reportDatas;
        writeSheet = EasyExcel.writerSheet(index, retentionDatas.getType().getName()).needHead(Boolean.FALSE).build();
        List<List<Object>> rows = rows(info.getStartDs(), info.getEndDs(), retentionDatas);
        List<List<String>> headList = heads(info.getStartDs(),info.getEndDs(),retentionDatas.getExtraType());
        WriteTable table = EasyExcel.writerTable(0).registerWriteHandler(excelStyleStrategy.customCellStyle()).needHead(true).build();
        table.setHead(headList);
        excelWriter.write(rows, writeSheet, table);
    }

    private List<List<Object>> rows(String startDs, String endDs, RetentionDatas excelDatas) {
        List<List<Object>> rows = new ArrayList<>();

        int intervalDay = 0;
        int suitDay = chooseSuitDayNum(DateUtil.dateCompare(endDs, startDs));
        for (String ds = endDs; DateUtil.dateCompare(ds, startDs) >= 0; ds = DateUtil.dateCalculate(ds, -1)) {
            intervalDay = DateUtil.dateCompare(endDs, ds);
            intervalDay = chooseSuitDayNum(intervalDay);
            if (intervalDay <= 0) continue ;

            List<List<ShareRetention>> shareRetentionLists = excelDatas.groupAndGetKey(ds);
            if (CollectionUtils.isEmpty(shareRetentionLists)) {
                continue;
            }

            for (List<ShareRetention> shareRetentionList : shareRetentionLists) {
                List<Object> row = fullRow(intervalDay, suitDay, ds, shareRetentionList,excelDatas.getExtraType());
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
    private List<Object> fullRow(int intervalDay, int suitDay, String ds, List<ShareRetention> shareRetentions, ExtraType type) {
        List<Object> row = Lists.newLinkedList();
        ShareRetention retention = shareRetentions.stream().findAny().get();
        for (int i = 0; i < suitDay + intervalDay + type.getNeedRowLength(); i++) {
            if (i == 0) {
                row.add(ds);
                type.addExtraCell(row, retention);
                row.add(retention.getInstallNum());
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
    public List<List<String>> heads(String startDs, String endDs, ExtraType type) {

        List<List<String>> heads = new ArrayList<>();
        int suitDay = chooseSuitDayNum(DateUtil.dateCompare(endDs, startDs));
        for (int i = 0; i <= suitDay; i++) {
            if (i == 0) {
                for (String head : type.getNeedExcelHead()) {
                    heads.add(Collections.singletonList(head));
                }
                continue;
            }
            heads.add(Collections.singletonList(i + "日"));
        }

        for (int i = 1; i <= suitDay; i++) {
            heads.add(Collections.singletonList(i + "日人数"));
        }
        return heads;
    }

    private int chooseSuitDayNum(int intervalDay) {
        if (intervalDay > config.getMaxRetentionDay()) {
            intervalDay = config.getMaxRetentionDay();
        }
        return intervalDay;
    }

}
