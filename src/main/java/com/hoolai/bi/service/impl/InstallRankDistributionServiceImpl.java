package com.hoolai.bi.service.impl;

import com.google.common.collect.Maps;
import com.hoolai.bi.util.DateUtil;
import com.hoolai.bi.excel.ExcelDatas;
import com.hoolai.bi.excel.info.ExtraType;
import com.hoolai.bi.entiy.ReportType;
import com.hoolai.bi.entiy.rank.RankDistribution;
import com.hoolai.bi.entiy.rank.RankDistributions;
import com.hoolai.bi.mapper.RankDistributionsMapper;
import com.hoolai.bi.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-11-04 20:36
 * 
 */

@Service
public class InstallRankDistributionServiceImpl implements ReportService {

    @Autowired
    private RankDistributionsMapper rankDistributionsMapper;

    @Override
    public ExcelDatas produceDatas(String startDs, String endDs, int gameId) {
        Map<String, List<RankDistribution>> result = Maps.newHashMapWithExpectedSize(DateUtil.dateCompare(endDs,startDs));
        List<RankDistribution> rankDistributionList = rankDistributionsMapper.queryInstallRanks(gameId,startDs,endDs);
        result = rankDistributionList.stream().collect(Collectors.groupingBy(rankDistribution->rankDistribution.getDs()));
        return new RankDistributions(result,ReportType.INSTALL_RANK_DISTRIBUTION, ExtraType.INSTALL_RANK_DISTRIBUTION);
    }
}
