package com.hoolai.bi.entiy.daily;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import com.hoolai.bi.context.ReportEnvConfig;
import com.hoolai.bi.entiy.DateUtil;
import com.hoolai.bi.entiy.GameInfo;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-09-29 11:04
 */

public abstract class DailyStats extends GameInfo {

    @ExcelProperty(value = "活跃" )
    private int dauNum;
    @ExcelProperty("安装")
    private int installNum;
    @NumberFormat("0.00")
    @ExcelProperty("付费金额（元）")
    private float payAmount;
    @TableField(exist = false)
    @ExcelProperty("付费率")
    private String payRate;

    @TableField(exist = false)
    @ExcelProperty("arpu(元)")
    private String arpu;

    @TableField(exist = false)
    @ExcelProperty("arppu(元)")
    private String arppu;

    @ExcelProperty("付费人数")
    private int payCount;
    @ExcelProperty("付费次数")
    private int payTimes;
    @ExcelProperty("安装付费人数")
    private int payInstallCount;
    @ExcelProperty("安装付金额（元）")
    @NumberFormat("0.00")
    private float payInstallAmount;
    @ExcelProperty("安装付费次数")
    private int payInstallTimes;
    @ExcelProperty("新付费人数")
    private int newPayCount;
    @NumberFormat("0.00")
    @ExcelProperty(value = "新付费金额（元）")
    private float newPayAmount;
    @ExcelProperty("新付费次数")
    private int newPayTimes;
    @TableField(exist = false)
    @ExcelProperty("安装付费率")
    private String installPayRate;

    @TableField(exist = false)
    @ExcelProperty("安装arpu(元)")
    private String installArpu;

    @TableField(exist = false)
    @ExcelProperty("安装arppu(元)")
    private String installArppu;

    public void init() {
        initRate(config.getChangeRateDs(),config.getRate());
        arpu = String.format("%.2f", checkDivide(payAmount, dauNum));
        arppu = String.format("%.2f", checkDivide(payAmount, payCount));
        payRate = String.format("%.2f", checkDivide(payCount, dauNum) * 100) + "%";
        installPayRate = String.format("%.2f", checkDivide(payInstallCount, installNum) * 100) + "%";
        installArpu = String.format("%.2f", checkDivide(payInstallAmount, installNum));
        installArppu = String.format("%.2f", checkDivide(payInstallAmount, payInstallCount));
    }

    private float checkDivide(float divisor, int dividend) {
        float result = divisor / dividend;
        if (Float.isNaN(result) || Float.isInfinite(result)) {
            return 0.0f;
        }
        return result;
    }

    private void initRate(String changeRateDs,int rate) {
        if (DateUtil.dateCompare(ds, changeRateDs) < 0) {
            payAmount *= rate;
            payInstallAmount *= rate;
            newPayAmount *= rate;
        }
    }

    public boolean checkZero() {
        return false;
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

    public float getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(float payAmount) {
        this.payAmount = payAmount / 1000.0f;
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

    public float getPayInstallAmount() {
        return payInstallAmount;
    }

    public void setPayInstallAmount(float payInstallAmount) {
        this.payInstallAmount = payInstallAmount / 1000.0f;
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

    public float getNewPayAmount() {
        return newPayAmount;
    }

    public void setNewPayAmount(float newPayAmount) {
        this.newPayAmount = newPayAmount / 1000.0f;
    }

    public int getNewPayTimes() {
        return newPayTimes;
    }

    public void setNewPayTimes(int newPayTimes) {
        this.newPayTimes = newPayTimes;
    }

    public String getArpu() {
        return arpu;
    }

    public void setArpu(String arpu) {
        this.arpu = arpu;
    }

    public String getArppu() {
        return arppu;
    }

    public void setArppu(String arppu) {
        this.arppu = arppu;
    }

    public String getPayRate() {
        return payRate;
    }

    public void setPayRate(String payRate) {
        this.payRate = payRate;
    }

    public String getInstallPayRate() {
        return installPayRate;
    }

    public void setInstallPayRate(String installPayRate) {
        this.installPayRate = installPayRate;
    }

    public String getInstallArpu() {
        return installArpu;
    }

    public void setInstallArpu(String installArpu) {
        this.installArpu = installArpu;
    }

    public String getInstallArppu() {
        return installArppu;
    }

    public void setInstallArppu(String installArppu) {
        this.installArppu = installArppu;
    }
}
