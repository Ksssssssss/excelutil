package com.hoolai.bi.entiy.excel;

import com.google.common.collect.Lists;
import com.hoolai.bi.context.ReportEnvConfig;
import com.hoolai.bi.entiy.*;
import com.hoolai.bi.entiy.retention.RetentionDatas;
import com.hoolai.bi.entiy.retention.ShareRetention;
import org.springframework.util.CollectionUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-10-16 17:55
 */

public class Excel {
    DecimalFormat df = new DecimalFormat("0.00%");
    private ReportEnvConfig config;
    private List<List<Object>> rows = new ArrayList<>();
    private List<List<String>> heads = new ArrayList<>();

    public static Excel fullExcel(QueryInfo info,ExcelDatas excelDatas, ReportEnvConfig config) {
        Excel excel = new Excel();
        RetentionDatas retentionDatas = (RetentionDatas)excelDatas;
        excel.setConfig(config);
        excel.fullRows(info.getStartDs(),info.getEndDs(),retentionDatas);
        excel.fullHeads(info.getStartDs(),info.getEndDs(),retentionDatas.getExtraType());
        return excel;
    }

    /**
     * @param startDs
     * @param endDs
     * @return
     * @description: 动态添加 rows --> 所有行集合
     */
    public void fullRows(String startDs,String endDs,RetentionDatas excelDatas) {
        int intervalDay = 0;
        int suitDay = chooseSuitDayNum(DateUtil.dateCompare(endDs, startDs));
        for (String ds = endDs; DateUtil.dateCompare(ds, startDs) >= 0; ds = DateUtil.dateCalculate(ds, -1)) {
            List<List<ShareRetention>> shareRetentionLists = excelDatas.groupAndGetKey(ds);
            if (CollectionUtils.isEmpty(shareRetentionLists)) {
                continue;
            }
            intervalDay = DateUtil.dateCompare(endDs, ds);

            intervalDay = chooseSuitDayNum(intervalDay);
            if (intervalDay <= 0) return ;

            for (List<ShareRetention> shareRetentionList : shareRetentionLists) {
                List<Object> row = fullRow(intervalDay, suitDay, ds, shareRetentionList,excelDatas.getExtraType());
                rows.add(new ArrayList<>(row));
            }

        }
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
    public void fullHeads(String startDs, String endDs, ExtraType type) {
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
    }

    private int chooseSuitDayNum(int intervalDay) {
        if (intervalDay > config.getMaxRetentionDay()) {
            intervalDay = config.getMaxRetentionDay();
        }
        return intervalDay;
    }

    public List<List<Object>> getRows() {
        return rows;
    }

    public void setRows(List<List<Object>> rows) {
        this.rows = rows;
    }

    public List<List<String>> getHeads() {
        return heads;
    }

    public void setHeads(List<List<String>> heads) {
        this.heads = heads;
    }

    public ReportEnvConfig getConfig() {
        return config;
    }

    public void setConfig(ReportEnvConfig config) {
        this.config = config;
    }

    public DecimalFormat getDf() {
        return df;
    }

    public void setDf(DecimalFormat df) {
        this.df = df;
    }
}
