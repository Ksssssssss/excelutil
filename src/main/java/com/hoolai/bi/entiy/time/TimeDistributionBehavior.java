package com.hoolai.bi.entiy.time;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.google.common.collect.Lists;
import com.hoolai.bi.entiy.Datas;
import com.hoolai.bi.entiy.QueryInfo;
import com.hoolai.bi.entiy.ip2region.ProvinceAddress;
import com.hoolai.bi.entiy.rank.RankDistribution;
import com.hoolai.bi.entiy.rank.RankDistributions;
import com.hoolai.bi.excel.ExcelDatas;
import com.hoolai.bi.excel.ExcelWriterBehavior;
import com.hoolai.bi.excel.info.ExcelStyleStrategy;
import com.hoolai.bi.excel.info.ExtraType;
import com.hoolai.bi.util.DateUtil;
import org.springframework.util.CollectionUtils;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2020-03-12 16:58
 */

public class TimeDistributionBehavior implements ExcelWriterBehavior {
    private static final int TIME = 24;
    private DecimalFormat df = new DecimalFormat("0.00%");

    @Override
    public void write(int index, ExcelDatas timeDatas, ExcelWriter excelWriter, ExcelStyleStrategy excelStyleStrategy, QueryInfo info) {
        WriteSheet writeSheet;
        TimeDistributionDatas datas = (TimeDistributionDatas) timeDatas;
        if (datas == null || CollectionUtils.isEmpty(datas.getTimeDistributions())) {
            return;
        }

        ExtraType type = datas.getExtraType();
        writeSheet = EasyExcel.writerSheet(index, datas.getType().getName()).needHead(Boolean.FALSE).build();
        List<TimeDistribution> timeDistributions = datas.getTimeDistributions();
        Map<String, List<TimeDistribution>> timeDistributionMap = timeDistributions.stream().collect(Collectors.groupingBy(TimeDistribution::getDs));
        List<List<String>> heads = headLists(type);
        List<List<Object>> rows = rowLists(info.getStartDs(), info.getEndDs(), timeDistributionMap, heads.size());
        WriteTable table = EasyExcel.writerTable(0).registerWriteHandler(excelStyleStrategy.customCellStyle()).needHead(true).build();
        table.setHead(heads);
        excelWriter.write(rows, writeSheet, table);
    }

    private List<List<Object>> rowLists(String startDs, String endDs, Map<String, List<TimeDistribution>> datas, int size) {
        List<List<Object>> rows = new ArrayList<>();
        for (String ds = endDs; DateUtil.dateCompare(ds, startDs) >= 0; ds = DateUtil.dateCalculate(ds, -1)) {
            List<TimeDistribution> timeDistributions = datas.get(ds);
            if (CollectionUtils.isEmpty(timeDistributions)) {
                continue;
            }
            List<Object> row = fullRow(timeDistributions, ds, size);
            rows.add(row);
        }
        return rows;
    }

    private List<Object> fullRow(List<TimeDistribution> timeDistributions, String ds, int size) {
        List<Object> row = Lists.newLinkedList();
        int sum = timeDistributions.stream().mapToInt(TimeDistribution::getNums).sum();
        for (int i = 0; i < size -1 ; i++) {
            if (i == 0){
                row.add(ds);
                row.add(sum);
                continue;
            }
            row.add(0);
        }
        for (TimeDistribution timeDistribution : timeDistributions) {
            String target = df.format((float)timeDistribution.getNums()/sum);
            row.set(timeDistribution.getHour() + 2, target);
        }
        return row;
    }

    private List<List<String>> headLists(ExtraType type) {
        List<List<String>> headList = Lists.newArrayListWithExpectedSize(TIME);

        for (int i = 0; i <= TIME; i++) {
            if (i == 0) {
                for (String head : type.getNeedExcelHead()) {
                    headList.add(Collections.singletonList(head));
                }
                continue;
            }

            headList.add(Collections.singletonList(((i - 1)) + "点" + "-" + i + "点"));
        }
        return headList;
    }
}
