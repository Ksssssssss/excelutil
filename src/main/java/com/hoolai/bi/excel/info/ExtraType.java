package com.hoolai.bi.excel.info;

import com.hoolai.bi.entiy.GameInfo;
import com.hoolai.bi.entiy.income.InstallIncomeRate;
import com.hoolai.bi.entiy.rank.RankDistribution;
import com.hoolai.bi.entiy.retention.RetentionOfAdId;
import com.hoolai.bi.entiy.retention.ShareCreativeRetention;
import com.hoolai.bi.entiy.retention.ShareOsRetention;

import java.util.Arrays;
import java.util.List;

/**
 * @description:额外字段
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-10-10 15:11
 */

public enum ExtraType {
    SIMPLE(1, Arrays.asList("日期","安装数")){
        @Override
        public void addExtraCell(List<Object> row, GameInfo gameInfo) {
            return;
        }
    },
    OS(2,Arrays.asList("日期","操作系统","安装数")){
        @Override
        public void addExtraCell(List<Object> row, GameInfo gameInfo) {
            row.add(((ShareOsRetention)gameInfo).getOs());
        }
    },
    CREATIVE(2,Arrays.asList("日期","渠道","安装数")){
        @Override
        public void addExtraCell(List<Object> row, GameInfo gameInfo) {
            row.add(((ShareCreativeRetention)gameInfo).getCreative());
        }
    },
    INSTALL_INCOME_ALL(1, Arrays.asList("日期","安装数")){
        @Override
        public void addExtraCell(List<Object> row, GameInfo gameInfo) {
            row.add(((InstallIncomeRate)gameInfo).getInstallNum());
        }
    },
    DAU_RANK_DISTRIBUTION(1, Arrays.asList("日期","总人数")){
        @Override
        public void addExtraCell(List<Object> row, GameInfo gameInfo) {
            row.add(((RankDistribution)gameInfo).getTotalNumbers());
        }
    },
    INSTALL_RANK_DISTRIBUTION(1, Arrays.asList("日期","安装人数")) {
        @Override
        public void addExtraCell(List<Object> row, GameInfo gameInfo) {
            row.add(((RankDistribution) gameInfo).getTotalNumbers());
        }
    },
    HOUR_DAU(1,Arrays.asList("日期/人数","活跃人数")){
        @Override
        public void addExtraCell(List<Object> row, GameInfo gameInfo) {

        }
    },
    HOUR_INSTALL(1,Arrays.asList("日期/人数","安装人数")){
        @Override
        public void addExtraCell(List<Object> row, GameInfo gameInfo) {

        }
    },
    AD_ID(2,Arrays.asList("日期","广告","安装数")){
        @Override
        public void addExtraCell(List<Object> row, GameInfo gameInfo) {
            row.add(((RetentionOfAdId)gameInfo).getAdId());
        }
    },

    EXTRA(3,Arrays.asList("人数","留存用户数","留存率")){
        @Override
        public void addExtraCell(List<Object> row, GameInfo gameInfo) {
            return;
        }
    }
    ;


    private int needRowLength;
    private List<String> needExcelHead;

    public abstract void addExtraCell(List<Object> row, GameInfo gameInfo);

    ExtraType(int needRowLength, List<String> needExcelHead) {
        this.needRowLength = needRowLength;
        this.needExcelHead = needExcelHead;
    }


    public int getNeedRowLength() {
        return needRowLength;
    }

    public void setNeedRowLength(int needRowLength) {
        this.needRowLength = needRowLength;
    }

    public List<String> getNeedExcelHead() {
        return needExcelHead;
    }

    public void setNeedExcelHead(List<String> needExcelHead) {
        this.needExcelHead = needExcelHead;
    }}


