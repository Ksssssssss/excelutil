package com.hoolai.bi.excel.info;

import com.hoolai.bi.entiy.GameInfo;
import com.hoolai.bi.entiy.income.InstallIncomeRate;
import com.hoolai.bi.entiy.rank.RankDistribution;
import com.hoolai.bi.entiy.retention.ShareCreativeRetention;
import com.hoolai.bi.entiy.retention.ShareOsRetention;

import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-10-10 15:11
 */

public enum ExtraType {
    ALL(1, Arrays.asList("日期","安装数")){
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
    };


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


