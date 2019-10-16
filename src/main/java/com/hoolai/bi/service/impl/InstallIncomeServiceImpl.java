package com.hoolai.bi.service.impl;

import com.google.common.collect.Maps;
import com.hoolai.bi.entiy.DateUtil;
import com.hoolai.bi.entiy.ExtraType;
import com.hoolai.bi.entiy.income.InstallIncomeRate;
import com.hoolai.bi.entiy.income.InstallIncomes;
import com.hoolai.bi.mapper.InstallIncomeMapper;
import com.hoolai.bi.service.InstallIncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-10-14 19:26
 * 
 */

@Service
public class InstallIncomeServiceImpl extends InstallIncomeService {

    @Autowired
    private InstallIncomeMapper installIncomeMapper;

    @Override
    protected InstallIncomes produceData(String startDs, String endDs, int gameId) {
        List<InstallIncomeRate> installIncomeRateList = installIncomeMapper.queryInstallIncomes(gameId, startDs, endDs);
        Map<String, List<InstallIncomeRate>> result = Maps.newHashMapWithExpectedSize(DateUtil.dateCompare(endDs,startDs));
        result = installIncomeRateList.stream().collect(Collectors.groupingBy(installIncomeRate->installIncomeRate.getDs()));
        return new InstallIncomes(result, ExtraType.INSTALL_INCOME_ALL);
    }

}
