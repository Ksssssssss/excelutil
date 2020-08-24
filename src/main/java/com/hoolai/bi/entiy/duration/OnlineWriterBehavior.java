package com.hoolai.bi.entiy.duration;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.google.common.collect.Lists;
import com.hoolai.bi.entiy.QueryInfo;
import com.hoolai.bi.entiy.daily.DailyAllStats;
import com.hoolai.bi.entiy.retention.RetentionDatas;
import com.hoolai.bi.entiy.retention.ShareRetention;
import com.hoolai.bi.excel.ExcelDatas;
import com.hoolai.bi.excel.ExcelWriterBehavior;
import com.hoolai.bi.excel.info.ExcelStyleStrategy;
import com.hoolai.bi.excel.info.ExtraType;
import com.hoolai.bi.util.DateUtil;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.apache.commons.collections4.ComparatorUtils;
import org.springframework.util.CollectionUtils;

import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-11-28 20:14
 */

public class OnlineWriterBehavior implements ExcelWriterBehavior {

    private DecimalFormat df = new DecimalFormat("0.00%");

    @Override
    public void write(int index, ExcelDatas reportDatas, ExcelWriter excelWriter, ExcelStyleStrategy excelStyleStrategy, QueryInfo info) {
        WriteSheet writeSheet;
        OnlineDurationDatas onlineDurationDatas = (OnlineDurationDatas) reportDatas;
        List<OnlineDuration> datas = onlineDurationDatas.getOnlineDurations();
        Map<String, DailyAllStats> dailyAllStatMap = onlineDurationDatas.getDailyAllStatMap();

        if (CollectionUtils.isEmpty(datas)) {
            return;
        }

        Map<String, List<OnlineDuration>> onlineDurationMap = datas.stream()
                .collect(Collectors.groupingBy(o -> o.getDs()));

        Map<String, List<OnlineDuration>> keyPrefixMap = datas.stream()
                .collect(Collectors.groupingBy(o -> o.getDuration()));
        Set<String> keys = keyPrefixMap.keySet();
        String[] k = keys.toArray(new String[keys.size()]);
        sort(k);
        List<List<String>> heads = heads(k);
        List<List<Object>> rows = rows(info.getStartDs(), info.getEndDs(), onlineDurationMap, dailyAllStatMap, k);

        writeSheet = EasyExcel.writerSheet(index, onlineDurationDatas.getReportType()
                .getName())
                .needHead(false)
                .build();

        WriteTable table = EasyExcel.writerTable(0)
                .registerWriteHandler(excelStyleStrategy.customCellStyle())
                .needHead(true)
                .build();

        table.setHead(heads);
        excelWriter.write(rows, writeSheet, table);
    }

    private List<List<Object>> rows(String startDs, String endDs, Map<String, List<OnlineDuration>> onlineDurationMap, Map<String, DailyAllStats> dailyAllStatMap, String[] keys) {
        List<List<Object>> rows = new ArrayList<>();

        for (String ds = endDs; DateUtil.dateCompare(ds, startDs) >= 0; ds = DateUtil.dateCalculate(ds, -1)) {
            List<OnlineDuration> onlineDurations = onlineDurationMap.get(ds);
            if (CollectionUtils.isEmpty(onlineDurations)) {
                continue;
            }
            List<Object> row = new ArrayList<>();
            int sum = dailyAllStatMap.get(ds).getInstallNum();
            row.add(ds);
            row.add(sum);
            for (String key : keys) {
                boolean isExist = false;
                for (OnlineDuration onlineDuration : onlineDurations) {
                    if (onlineDuration.getDuration().equals(key)) {
                        isExist = true;
                        row.add(onlineDuration.getNumbers());
                        row.add(onlineDuration.getRetentionNumbers());
                        row.add(df.format(onlineDuration.getRetentionRatio()));
                    }
                }
                if (!isExist) {
                    for (int i = 0; i < ExtraType.EXTRA.getNeedRowLength(); i++) {
                        row.add(0);
                    }
                }
            }
            rows.add(row);

        }
        return rows;
    }

    private List<List<String>> heads(String[] keys) {
        List<List<String>> heads = new ArrayList<>();

        List<String> headNames = ExtraType.SIMPLE.getNeedExcelHead();
        for (String headName : headNames) {
            heads.add(Lists.newArrayList(headName));
        }

        ExtraType type = ExtraType.EXTRA;
        for (String key : keys) {
            for (String name : type.getNeedExcelHead()) {
                List<String> head = new ArrayList<>();
                head.add(key);
                head.add(name);
                heads.add(head);
            }
        }

        return heads;
    }

    private void sort(String[] keys) {
        if (keys.length < 1) {
            return;
        }

        for (int i = 0; i < keys.length; i++) {
            for (int j = 0; j < keys.length - 1; j++) {
                if (OnlineDuration.comparator.compare(keys[j], keys[j + 1]) > 0) {
                    String tmp = keys[j];
                    keys[j] = keys[j + 1];
                    keys[j + 1] = tmp;
                }
            }
        }
    }
}
