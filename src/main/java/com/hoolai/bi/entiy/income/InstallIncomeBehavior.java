package com.hoolai.bi.entiy.income;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.google.common.collect.Lists;
import com.hoolai.bi.context.ReportEnvConfig;
import com.hoolai.bi.entiy.*;
import com.hoolai.bi.excel.ExcelDatas;
import com.hoolai.bi.excel.info.ExcelStyleStrategy;
import com.hoolai.bi.excel.ExcelWriterBehavior;
import com.hoolai.bi.excel.info.ExtraType;
import com.hoolai.bi.entiy.income.InstallIncomeRate;
import com.hoolai.bi.entiy.income.InstallIncomes;
import com.hoolai.bi.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-10-14 19:26
 */

public class InstallIncomeBehavior implements ExcelWriterBehavior {
    private ReportEnvConfig config;

    public InstallIncomeBehavior(ReportEnvConfig config) {
        this.config = config;
    }

    @Override
    public void write(int index, ExcelDatas reportDatas, ExcelWriter excelWriter, ExcelStyleStrategy excelStyleStrategy, QueryInfo info) {
        WriteSheet writeSheet;
        String startDs = info.getStartDs();
        String endDs = info.getEndDs();
        InstallIncomes installIncomes = (InstallIncomes) reportDatas;
        installIncomes.initIncomeRate(startDs, endDs, config);
        writeSheet = EasyExcel.writerSheet(index, installIncomes.getType().getName()).needHead(Boolean.FALSE).build();
        List<List<Object>> rows = rows(startDs, endDs, installIncomes,config.getMaxInstallIncomeDay());
        List<List<String>> headList = headLists(installIncomes.getExtraType());
        WriteTable table = EasyExcel.writerTable(0).registerWriteHandler(excelStyleStrategy.customCellStyle()).needHead(true).build();
        table.setHead(headList);
        excelWriter.write(rows, writeSheet, table);
    }

    /**
     * @param startDs
     * @param endDs
     * @return
     * @description: 动态添加 rows --> 所有行集合
     */
    private List<List<Object>> rows(String startDs, String endDs, InstallIncomes installIncomes,int maxInstallIncomeDay) {
        List<List<Object>> rows = new ArrayList<>();

        String caculateDay = DateUtil.dateCalculate(endDs,1);
        for (String ds = endDs; DateUtil.dateCompare(ds, startDs) >= 0; ds = DateUtil.dateCalculate(ds, -1)) {
            List<List<InstallIncomeRate>> installIncomeRates = installIncomes.groupAndGetKey(ds);
            if (CollectionUtils.isEmpty(installIncomeRates)) {
                continue;
            }

            int suitDay = chooseSuitDayNum(DateUtil.dateCompare(caculateDay, ds),maxInstallIncomeDay);

            for (List<InstallIncomeRate> shareRetentionList : installIncomeRates) {
                List<Object> row = fullRow(suitDay, ds, shareRetentionList, installIncomes.getExtraType());
                installIncomes.fullEmptyRow(row);
                rows.add(new ArrayList<>(row));
            }
        }
        return rows;
    }

    /**
     * @param suitDay
     * @param ds
     * @param installIcomeRates
     * @return
     * @description 动态填充row
     */
    private List<Object> fullRow(int suitDay, String ds, List<InstallIncomeRate> installIcomeRates, ExtraType type) {
        List<Object> row = initRow(suitDay, ds, type, installIcomeRates.stream().findAny().get());
        installIcomeRates.forEach(installIncomeRate -> installIncomeRate.fullRow(row, type));
        return row;
    }

    private List<Object> initRow(int suitDay, String ds, ExtraType type, InstallIncomeRate installIncomeRate) {
        List<Object> row = Lists.newArrayListWithCapacity(suitDay + type.getNeedRowLength());
        for (int i = 0; i <= suitDay; i++) {
            if (i == 0) {
                row.add(ds);
                type.addExtraCell(row, installIncomeRate);
                continue;
            }
            row.add("0");
        }
        return row;
    }

    /**
     * @return
     * @description: 动态添加 head --> 所有head
     */
    private List<List<String>> headLists(ExtraType type) {
        List<List<String>> headList = new ArrayList<>();

        for (int i = 0; i <= config.getMaxInstallIncomeDay(); i++) {
            if (i == 0) {
                for (String head : type.getNeedExcelHead()) {
                    headList.add(Collections.singletonList(head));
                }
                continue;
            }
            headList.add(Collections.singletonList(i + "日"));
        }

        return headList;
    }

    private int chooseSuitDayNum(int intervalDay,int maxInstallIncomeDay) {
        if (intervalDay > maxInstallIncomeDay) {
            intervalDay = maxInstallIncomeDay;
        }
        return intervalDay;
    }
}
