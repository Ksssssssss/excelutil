package com.hoolai.bi.entiy.income;

import com.baomidou.mybatisplus.annotation.TableField;
import com.hoolai.bi.context.ReportEnvConfig;
import com.hoolai.bi.util.DateUtil;
import com.hoolai.bi.excel.info.ExtraType;
import com.hoolai.bi.entiy.GameInfo;

import java.text.DecimalFormat;
import java.util.List;

/**
 * @description: 注收比
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-10-14 15:28
 */

public class InstallIncomeRate extends GameInfo {

    private int installNum;
    /**
     * 收入间隔天
     */
    private int incomeDay;
    /**
     * 收入
     */
    private int income;
    @TableField(exist = false)
    private float incomeRate;

    private DecimalFormat df = new DecimalFormat("0.00");

    public void init(ReportEnvConfig config) {
        if (DateUtil.dateCompare(ds, config.getChangeRateDs()) < 0) {
            income *= config.getRate();
        }
        incomeRate = (float) income / installNum / 1000;
    }

    public void fullRow(List<Object> row, ExtraType type) {
        if (incomeRate == 0){
            return;
        }
        row.set(incomeDay + type.getNeedRowLength() + 1, df.format(incomeRate));
    }

    public void incrIncome(float incomeRate) {
        this.incomeRate += incomeRate;
    }

    public int getInstallNum() {
        return installNum;
    }

    public void setInstallNum(int installNum) {
        this.installNum = installNum;
    }

    public int getIncomeDay() {
        return incomeDay;
    }

    public void setIncomeDay(int incomeDay) {
        this.incomeDay = incomeDay;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public void setIncomeRate(float incomeRate) {
        this.incomeRate = incomeRate;
    }

    public float getIncomeRate() {
        return incomeRate;
    }
}