package com.hoolai.bi.entiy.daily;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @description: 额外信息
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-10-09 15:50
 */

public class DailyExtraInfo {

    @ExcelProperty("用户平均收益")
    private float arpu;
    @ExcelProperty("付费用户平均收益")
    private float arppu;

    public DailyExtraInfo(int dau,int payment,int payCount) {
        this.arpu = payment/dau;
        this.arppu = payment/payCount;
    }

    public float getArpu() {
        return arpu;
    }

    public void setArpu(float arpu) {
        this.arpu = arpu;
    }

    public float getArppu() {
        return arppu;
    }

    public void setArppu(float arppu) {
        this.arppu = arppu;
    }

    @Override
    public String toString() {
        return "DailyExtraInfo{" +
                "arpu=" + arpu +
                ", arppu=" + arppu +
                '}';
    }
}
