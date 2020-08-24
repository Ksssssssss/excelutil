package com.hoolai.bi.entiy.income;

import com.google.common.collect.Maps;
import com.hoolai.bi.context.ReportEnvConfig;
import com.hoolai.bi.entiy.*;
;
import com.hoolai.bi.excel.ExcelDatas;
import com.hoolai.bi.excel.info.ExtraType;
import com.hoolai.bi.util.DateUtil;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description: retention集合
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-10-10 17:17
 */

public class InstallIncomes implements ExcelDatas {
    private Map<String, List<InstallIncomeRate>> installIncomeRates;
    private ReportType type;
    private ExtraType extraType;
    private Game game;

    public InstallIncomes(Map<String, List<InstallIncomeRate>> installIncomeRates,Game game, ReportType type, ExtraType extraType) {
        this.installIncomeRates = installIncomeRates;
        this.type = type;
        this.game = game;
        this.extraType = extraType;
    }

    public List<List<InstallIncomeRate>> groupAndGetKey(String ds) {
        Map<String, List<InstallIncomeRate>> result = Maps.newHashMap();
        List<InstallIncomeRate> installIcomeRateList = installIncomeRates.get(ds);
        if (CollectionUtils.isEmpty(installIcomeRateList)) {
            return new ArrayList<>();
        }
        switch (extraType) {
            case INSTALL_INCOME_ALL:
                result.put(ReportType.INSTALL_INCOME_RATE.getName(), installIcomeRateList);
                break;
        }
        return new ArrayList<>(result.values());
    }

    public void initIncomeRate(String startDs, String endDs, ReportEnvConfig config) {
        if (CollectionUtils.isEmpty(installIncomeRates)) {
            return;
        }

        for (String ds = startDs; DateUtil.dateCompare(endDs, ds) >= 0; ds = DateUtil.dateCalculate(ds, 1)) {
            List<InstallIncomeRate> installIncomeRateList = installIncomeRates.get(ds);
            if (CollectionUtils.isEmpty(installIncomeRateList)) {
                continue;
            }
            installIncomeRateList = installIncomeRateList.stream().sorted(Comparator.comparing(installIncomeRate -> installIncomeRate.getIncomeDay())).collect(Collectors.toList());
            InstallIncomeRate maxInstallIncomeRate = installIncomeRateList.get(installIncomeRateList.size() - 1);
            init(installIncomeRateList, game);
        }
    }

    private void init(List<InstallIncomeRate> installIncomeRateList, Game game) {
        for (int i = 0; i < installIncomeRateList.size(); i++) {
            InstallIncomeRate installIncomeRate = installIncomeRateList.get(i);
            installIncomeRate.init(game);
            if (i == 0)
                continue;
            float incomeRate = installIncomeRateList.get(i - 1).getIncomeRate();
            installIncomeRate.incrIncome(incomeRate);
        }
    }

    public void fullEmptyRow(List<Object> row) {
        List<Integer> indexs = new ArrayList<>();
        for (int i = extraType.getNeedRowLength() + 1; i < row.size(); i++) {
            if (!row.get(i).equals("0")) {
                indexs.add(i);
            }
        }
        if (indexs.size() < 1) {
            return;
        }
        for (int i = 1; i <= indexs.size(); i++) {
            for (int j = indexs.get(i - 1); j < row.size(); j++) {
                if (i == indexs.size()) {
                    row.set(j,row.get(indexs.get(i-1)));
                    continue;
                }

                if (j < indexs.get(i)) {
                    row.set(j, row.get(indexs.get(i - 1)));
                }
            }
        }
    }

    public Map<String, List<InstallIncomeRate>> getInstallIncomeRates() {
        return installIncomeRates;
    }

    public void setInstallIncomeRates(Map<String, List<InstallIncomeRate>> installIncomeRates) {
        this.installIncomeRates = installIncomeRates;
    }

    public ReportType getType() {
        return type;
    }

    public void setType(ReportType type) {
        this.type = type;
    }

    public ExtraType getExtraType() {
        return extraType;
    }

    public void setExtraType(ExtraType extraType) {
        this.extraType = extraType;
    }
}
