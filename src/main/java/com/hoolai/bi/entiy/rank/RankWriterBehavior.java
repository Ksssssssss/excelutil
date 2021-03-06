package com.hoolai.bi.entiy.rank;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.google.common.collect.Lists;
import com.hoolai.bi.context.ReportEnvConfig;
import com.hoolai.bi.util.DateUtil;
import com.hoolai.bi.excel.ExcelDatas;
import com.hoolai.bi.excel.info.ExtraType;
import com.hoolai.bi.entiy.QueryInfo;
import com.hoolai.bi.excel.info.ExcelStyleStrategy;
import com.hoolai.bi.excel.ExcelWriterBehavior;
import com.hoolai.bi.entiy.rank.RankDistribution;
import com.hoolai.bi.entiy.rank.RankDistributions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-10-16 10:43
 */
public class RankWriterBehavior implements ExcelWriterBehavior {
    private ReportEnvConfig config;
    /**
     * 默认间隔长度
     */
    private static final int DEFAULT_LEVEL_SUMMARY = 1;

    public RankWriterBehavior(ReportEnvConfig config) {
        this.config = config;
    }

    @Override
    public void write(int index, ExcelDatas reportDatas, ExcelWriter excelWriter, ExcelStyleStrategy excelStyleStrategy, QueryInfo info) {
        WriteSheet writeSheet;
        RankDistributions rankDistributions = (RankDistributions) reportDatas;
        rankDistributions.init();
        writeSheet = EasyExcel.writerSheet(index, rankDistributions.getType().getName()).needHead(Boolean.FALSE).build();
        int summary = config.getLevelDistributionSummary();

        //fill excel
        List<List<String>> headList = headLists(rankDistributions, DEFAULT_LEVEL_SUMMARY);
        List<List<Object>> rows = rows(info.getStartDs(), info.getEndDs(), rankDistributions, DEFAULT_LEVEL_SUMMARY);
        List<List<String>> headList_1 = headLists(rankDistributions, summary);
        List<List<Object>> rows_1 = rows(info.getStartDs(), info.getEndDs(), rankDistributions, summary);

        WriteTable table = EasyExcel.writerTable(0).registerWriteHandler(excelStyleStrategy.customCellStyle()).needHead(true).build();
        WriteTable table1 = EasyExcel.writerTable(1).registerWriteHandler(excelStyleStrategy.customCellStyle()).needHead(true).build();
        table.setHead(headList);
        table1.setHead(headList_1);
        excelWriter.write(rows, writeSheet, table);
        excelWriter.write(rows_1, writeSheet, table1);
    }

    private List<List<String>> headLists(RankDistributions rankDistributions, int summary) {
        List<List<String>> headList = new ArrayList<>();
        int maxLevel = rankDistributions.maxLevel();
        int minLevel = rankDistributions.minLevel();
        for (int i = 0; i <= (int) Math.ceil(maxLevel / (float) summary); i++) {
            List<String> heads = new ArrayList<>();
            if (i == 0) {
                addExtraHead(rankDistributions, headList);
                if (minLevel == 0) {
                    headList.add(Collections.singletonList(i + "级"));
                }
                continue;
            }
            if (summary == DEFAULT_LEVEL_SUMMARY) {
                headList.add(Collections.singletonList(i + "级"));
                continue;
            }
            int index = maxLevel < i * summary ? maxLevel : i * summary;
            headList.add(Collections.singletonList(((i - 1) * summary + 1) + "级" + "-" + index + "级"));
        }
        return headList;
    }

    private void addExtraHead(RankDistributions rankDistributions, List<List<String>> headList) {
        for (String head : rankDistributions.getExtraType().getNeedExcelHead()) {
            headList.add(Collections.singletonList(head));
        }
    }


    private List<List<Object>> rows(String startDs, String endDs, RankDistributions rankDistributions, int summary) {
        List<List<Object>> rows = new ArrayList<>();
        int suitRowLength = (int) Math.ceil(rankDistributions.maxLevel() / (float) summary);
        //0占一个字段长度
        int minLevel = rankDistributions.minLevel();
        ExtraType type = rankDistributions.getExtraType();
        int needLength = type.getNeedRowLength();
        if (minLevel == 0) {
            suitRowLength++;
            needLength++;
        }

        for (String ds = endDs; DateUtil.dateCompare(ds, startDs) >= 0; ds = DateUtil.dateCalculate(ds, -1)) {
            List<RankDistribution> rankDistributionList = rankDistributions.getRankDistributions().get(ds);
            if (CollectionUtils.isEmpty(rankDistributionList)) {
                continue;
            }

            List<Object> row = initRow(suitRowLength, ds, type, rankDistributionList.stream().findAny().get());
            fullRow(row, suitRowLength, rankDistributionList, needLength, summary, true);
            rows.add(new ArrayList<>(row));
        }
        return rows;
    }

    private void fullRow(List<Object> row, int suitRowLength, List<RankDistribution> rankDistributions, int needLength, final int summary, boolean fillZero) {

        if (summary == DEFAULT_LEVEL_SUMMARY) {
            rankDistributions.forEach(rankDistribution -> rankDistribution.fullRow(row, needLength));
            return;
        }

        for (int i = 1; i <= suitRowLength; i++) {
            final int j;
            if (fillZero) {
                j = i - 1;
            } else
                j = i;
            double summaryLevelPercentage = rankDistributions.stream().filter(rankDistribution ->
                    rankDistribution.getLevel() > (j - 1) * summary && rankDistribution.getLevel() <= j * summary)
                    .mapToDouble(RankDistribution::getRankNumPercentages).sum();
            row.set(j + needLength, String.format("%.2f", summaryLevelPercentage) + "%");
        }
    }

    private List<Object> initRow(int suitRowLength, String ds, ExtraType extraType, RankDistribution rankDistribution) {
        List<Object> row = Lists.newArrayListWithCapacity(suitRowLength);
        for (int i = 0; i <= suitRowLength; i++) {
            if (i == 0) {
                row.add(ds);
                extraType.addExtraCell(row, rankDistribution);
                continue;
            }
            row.add("0");
        }
        return row;
    }
}
