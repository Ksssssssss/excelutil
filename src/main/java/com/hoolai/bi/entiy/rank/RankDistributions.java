package com.hoolai.bi.entiy.rank;

import com.hoolai.bi.excel.ExcelDatas;
import com.hoolai.bi.excel.info.ExtraType;
import com.hoolai.bi.entiy.ReportType;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-11-04 20:52
 */

public class RankDistributions implements ExcelDatas {

    private Map<String, List<RankDistribution>> rankDistributions;
    private ReportType type;
    private ExtraType extraType;

    public RankDistributions(Map<String, List<RankDistribution>> rankDistributions, ReportType type, ExtraType extraType) {
        this.rankDistributions = rankDistributions;
        this.type = type;
        this.extraType = extraType;
    }

    public void init() {
        rankDistributions.values().stream().forEach(rankDistributions1 -> rankDistributions1.stream().forEach(rankDistribution -> rankDistribution.init()));
    }

    public int maxLevel() {
        int maxLevel = 0;
        for (List<RankDistribution> rankDistributionList : rankDistributions.values()) {
            int temporary = rankDistributionList.stream().max(Comparator.comparing(RankDistribution::getLevel)).get().getLevel();
            if (maxLevel < temporary) {
                maxLevel = temporary;
            }
        }
        return maxLevel;
    }

    public int minLevel() {
        int minLevel = 0;
        for (List<RankDistribution> rankDistributionList : rankDistributions.values()) {
            minLevel = rankDistributionList.stream().min(Comparator.comparing(RankDistribution::getLevel)).get().getLevel();
        }
        return minLevel;
    }

    public ReportType getType() {
        return type;
    }

    public void setType(ReportType type) {
        this.type = type;
    }

    public Map<String, List<RankDistribution>> getRankDistributions() {
        return rankDistributions;
    }

    public void setRankDistributions(Map<String, List<RankDistribution>> rankDistributions) {
        this.rankDistributions = rankDistributions;
    }

    public ExtraType getExtraType() {
        return extraType;
    }

    public void setExtraType(ExtraType extraType) {
        this.extraType = extraType;
    }
}
