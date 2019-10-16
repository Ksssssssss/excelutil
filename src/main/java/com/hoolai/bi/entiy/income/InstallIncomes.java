package com.hoolai.bi.entiy.income;

import com.google.common.collect.Maps;
import com.hoolai.bi.context.ReportEnvConfig;
import com.hoolai.bi.entiy.DateUtil;
import com.hoolai.bi.entiy.ExtraType;
import com.hoolai.bi.entiy.ReportType;
;
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

public class InstallIncomes {
    private Map<String, List<InstallIncomeRate>> installIncomeRates;
    private ExtraType type;

    public InstallIncomes() {
    }

    public InstallIncomes(Map<String, List<InstallIncomeRate>> installIncomeRates, ExtraType type) {
        this.installIncomeRates = installIncomeRates;
        this.type = type;
    }

    public List<List<InstallIncomeRate>> groupAndGetKey(String ds) {
        Map<String, List<InstallIncomeRate>> result = Maps.newHashMap();
        List<InstallIncomeRate> installIcomeRateList = installIncomeRates.get(ds);
        if (CollectionUtils.isEmpty(installIcomeRateList)) {
            return new ArrayList<>();
        }
        switch (type) {
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
            init(installIncomeRateList, config);
        }
    }

    private void init(List<InstallIncomeRate> installIncomeRateList, ReportEnvConfig config) {
        for (int i = 0; i < installIncomeRateList.size(); i++) {
            InstallIncomeRate installIncomeRate = installIncomeRateList.get(i);
            installIncomeRate.init(config);
            if (i == 0)
                continue;
            float incomeRate = installIncomeRateList.get(i - 1).getIncomeRate();
            installIncomeRate.incrIncome(incomeRate);
        }
    }

    public void fullEmptyRow(List<Object> row) {
        List<Integer> indexs = new ArrayList<>();
        for (int i = type.getNeedRowLength() + 1; i < row.size(); i++) {
            if (!row.get(i).equals("0.00")) {
                indexs.add(i);
            }
        }
        if (indexs.size() <= 1){
            return;
        }
        float incomeRate = 0f;
        for (int i = 1; i < indexs.size(); i++) {
            for (int j = indexs.get(i-1); j < row.size(); j++) {
                if (j < indexs.get(i)){
                    row.set(j,row.get(indexs.get(i-1)));
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

    public ExtraType getType() {
        return type;
    }

    public void setType(ExtraType type) {
        this.type = type;
    }
}
