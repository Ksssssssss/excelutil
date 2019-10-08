package com.hoolai.bi.entiy.daily;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.hoolai.bi.entiy.GameInfo;

/**
 *
 *@description:
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-09-29 11:04
 *
 */

public class DailyStats extends GameInfo {

    @ExcelProperty("活跃")
    private int dauNum;
    @ExcelProperty("安装")
    private int installNum;
    @ExcelProperty("付费人数")
    private int payCount;
    @ExcelProperty("付费金额")
    private int payAmount;
    @ExcelProperty("付费次数")
    private int payTimes;
    @ExcelProperty("安装付费人数")
    private int payInstallCount;
    @ExcelProperty("安装付金额")
    private int payInstallAmount;
    @ExcelProperty("安装付费次数")
    private int payInstallTimes;
    @ExcelProperty("新付费人数")
    private int newPayCount;
    @ExcelProperty("新付费金额")
    private int newPayAmount;
    @ExcelProperty("新付费次数")
    private int newPayTimes;

    @Override
    public String toString() {
        return "DailyStats{" +
                ", dauNum=" + dauNum +
                ", installNum=" + installNum +
                ", payCount=" + payCount +
                ", payAmount=" + payAmount +
                ", payTimes=" + payTimes +
                ", payInstallCount=" + payInstallCount +
                ", payInstallAmount=" + payInstallAmount +
                ", payInstallTimes=" + payInstallTimes +
                ", newPayCount=" + newPayCount +
                ", newPayAmount=" + newPayAmount +
                ", newPayTimes=" + newPayTimes +
                '}';
    }

    public int getDauNum() {
        return dauNum;
    }

    public void setDauNum(int dauNum) {
        this.dauNum = dauNum;
    }

    public int getInstallNum() {
        return installNum;
    }

    public void setInstallNum(int installNum) {
        this.installNum = installNum;
    }

    public int getPayCount() {
        return payCount;
    }

    public void setPayCount(int payCount) {
        this.payCount = payCount;
    }

    public int getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(int payAmount) {
        this.payAmount = payAmount/100;
    }

    public int getPayTimes() {
        return payTimes;
    }

    public void setPayTimes(int payTimes) {
        this.payTimes = payTimes;
    }

    public int getPayInstallCount() {
        return payInstallCount;
    }

    public void setPayInstallCount(int payInstallCount) {
        this.payInstallCount = payInstallCount;
    }

    public int getPayInstallAmount() {
        return payInstallAmount;
    }

    public void setPayInstallAmount(int payInstallAmount) {
        this.payInstallAmount = payInstallAmount/100;
    }

    public int getPayInstallTimes() {
        return payInstallTimes;
    }

    public void setPayInstallTimes(int payInstallTimes) {
        this.payInstallTimes = payInstallTimes;
    }

    public int getNewPayCount() {
        return newPayCount;
    }

    public void setNewPayCount(int newPayCount) {
        this.newPayCount = newPayCount;
    }

    public int getNewPayAmount() {
        return newPayAmount;
    }

    public void setNewPayAmount(int newPayAmount) {
        this.newPayAmount = newPayAmount/100;
    }

    public int getNewPayTimes() {
        return newPayTimes;
    }

    public void setNewPayTimes(int newPayTimes) {
        this.newPayTimes = newPayTimes;
    }
}
