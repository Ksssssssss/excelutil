package com.hoolai.bi.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.google.common.collect.Lists;
import com.hoolai.bi.context.ReportEnvConfig;
import com.hoolai.bi.entiy.DateUtil;
import com.hoolai.bi.entiy.ReportType;
import com.hoolai.bi.entiy.ExtraType;
import com.hoolai.bi.entiy.excel.ExcelStyleStrategy;
import com.hoolai.bi.entiy.income.InstallIncomeRate;
import com.hoolai.bi.entiy.income.InstallIncomes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-10-14 19:26
 */

public abstract class InstallIncomeService {
    @Autowired
    private ReportEnvConfig config;
    @Autowired
    private ExcelStyleStrategy excelStyleStrategy;

    public void write(String startDs, String endDs, int gameId, ExcelWriter excelWriter, int i, ReportType type) {
        InstallIncomes installIncomes = produceData(startDs, endDs, gameId);
        installIncomes.initIncomeRate(startDs, endDs, config);
        excelWrite(startDs, endDs, excelWriter, i, type, installIncomes);
    }

    private void excelWrite(String startDs, String endDs, ExcelWriter excelWriter, int i, ReportType type, InstallIncomes installIncomes) {
        WriteSheet writeSheet;
        writeSheet = EasyExcel.writerSheet(i, type.getName()).needHead(Boolean.FALSE).build();
        List<List<Object>> rows = rows(startDs, endDs, installIncomes);
        List<List<String>> headList = headLists(startDs, endDs, installIncomes.getType());
        WriteTable table = EasyExcel.writerTable(0).registerWriteHandler(excelStyleStrategy.customCellStyle()).needHead(true).build();
        table.setHead(headList);
        excelWriter.write(rows, writeSheet, table);
    }

    /**
     * @param startDs
     * @param endDs
     * @param gameId
     * @return
     */
    protected abstract InstallIncomes produceData(String startDs, String endDs, int gameId);

    /**
     * @param startDs
     * @param endDs
     * @return
     * @description: 动态添加 rows --> 所有行集合
     */
    private List<List<Object>> rows(String startDs, String endDs, InstallIncomes installIncomes) {
        List<List<Object>> rows = new ArrayList<>();
        int suitDay = config.getMaxInstallIncomeDay();
        for (String ds = endDs; DateUtil.dateCompare(ds, startDs) >= 0; ds = DateUtil.dateCalculate(ds, -1)) {
            List<List<InstallIncomeRate>> installIncomeRates = installIncomes.groupAndGetKey(ds);
            if (CollectionUtils.isEmpty(installIncomeRates)) {
                continue;
            }
            for (List<InstallIncomeRate> shareRetentionList : installIncomeRates) {
                List<Object> row = fullRow(suitDay, ds, shareRetentionList, installIncomes.getType());
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
        for (int i = 0; i <= suitDay + type.getNeedRowLength(); i++) {
            if (i == 0) {
                row.add(ds);
                type.addExtraCell(row, installIncomeRate);
                continue;
            }
            row.add("0.00");
        }
        return row;
    }

    /**
     * @param startDs
     * @param endDs
     * @return
     * @description: 动态添加 head --> 所有head
     */
    private List<List<String>> headLists(String startDs, String endDs, ExtraType type) {
        List<List<String>> headList = new ArrayList<>();

        for (int i = 0; i <= config.getMaxInstallIncomeDay()+type.getNeedRowLength(); i++) {
            if (i == 0) {
                for (String head : type.getNeedExcelHead()) {
                    headList.add(Collections.singletonList(head));
                }
                continue;
            }
            headList.add(Collections.singletonList("day"+(i-1)));
        }

        return headList;
    }

    private int chooseSuitDayNum(int intervalDay) {
        if (intervalDay != config.getMaxInstallIncomeDay()) {
            intervalDay = config.getMaxInstallIncomeDay();
        }
        return intervalDay;
    }
}
