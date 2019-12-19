package com.hoolai.bi.entiy.daily;

import com.alibaba.excel.annotation.ExcelProperty;
import com.hoolai.bi.context.ReportEnvConfig;

import java.util.Optional;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-10-12 16:36
 */

public class DailyAllStats extends DailyStats {

    @ExcelProperty(value = {"活跃", "android"}, index = 2)
    private String dauAndroidRate;
    @ExcelProperty(value = {"活跃", "ios"}, index = 3)
    private String dauIosRate;
    @ExcelProperty(value = {"活跃", "unknown"}, index = 4)
    private String dauUnknownRate;

    @ExcelProperty(value = {"安装", "android"}, index = 6)
    private String installAndroidRate;
    @ExcelProperty(value = {"安装", "ios"}, index = 7)
    private String installIosRate;
    @ExcelProperty(value = {"安装", "unknown"}, index = 8)
    private String installUnknownRate;

    @ExcelProperty(value = "萌币兑换次数", index = 9)
    private int exchangeMyTimes;
    @ExcelProperty(value = "萌币兑换人数", index = 10)
    private int exchangeMyNums;
    @ExcelProperty(value = "萌币兑换金额", index = 11)
    private String exchangeMyAmount;

    @ExcelProperty(value = "访问次数", index = 12)
    private String visits;
    @ExcelProperty(value = "人均访问次数", index = 13)
    private String averageVisits;
    @ExcelProperty(value = "人均停留时长(秒)", index = 14)
    private String averageStayTime;

    @Override
    public void init(ReportEnvConfig config) {
        super.init(config);
        dauAndroidRate = changeRate(dauAndroidRate);
        dauIosRate = changeRate(dauIosRate);
        dauUnknownRate = changeRate(dauUnknownRate);
        installAndroidRate = changeRate(installAndroidRate);
        installIosRate = changeRate(installIosRate);
        installUnknownRate = changeRate(installUnknownRate);
    }

    private String changeRate(String rate) {
        Optional<String> optional = Optional.ofNullable(rate);
        return String.format("%.2f", Float.parseFloat(optional.orElse("0")) * 100) + "%";
    }


    public String getDauAndroidRate() {
        return dauAndroidRate;
    }

    public void setDauAndroidRate(String dauAndroidRate) {
        this.dauAndroidRate = dauAndroidRate;
    }

    public String getDauIosRate() {
        return dauIosRate;
    }

    public void setDauIosRate(String dauIosRate) {
        this.dauIosRate = dauIosRate;
    }

    public String getDauUnknownRate() {
        return dauUnknownRate;
    }

    public void setDauUnknownRate(String dauUnknownRate) {
        this.dauUnknownRate = dauUnknownRate;
    }

    public String getInstallAndroidRate() {
        return installAndroidRate;
    }

    public void setInstallAndroidRate(String installAndroidRate) {
        this.installAndroidRate = installAndroidRate;
    }

    public String getInstallIosRate() {
        return installIosRate;
    }

    public void setInstallIosRate(String installIosRate) {
        this.installIosRate = installIosRate;
    }

    public String getInstallUnknownRate() {
        return installUnknownRate;
    }

    public void setInstallUnknownRate(String installUnknownRate) {
        this.installUnknownRate = installUnknownRate;
    }

    public int getExchangeMyTimes() {
        return exchangeMyTimes;
    }

    public void setExchangeMyTimes(int exchangeMyTimes) {
        this.exchangeMyTimes = exchangeMyTimes;
    }

    public int getExchangeMyNums() {
        return exchangeMyNums;
    }

    public void setExchangeMyNums(int exchangeMyNums) {
        this.exchangeMyNums = exchangeMyNums;
    }

    public String getExchangeMyAmount() {
        return exchangeMyAmount;
    }

    public void setExchangeMyAmount(String exchangeMyAmount) {
        this.exchangeMyAmount = exchangeMyAmount;
    }

    public String getVisits() {
        return visits;
    }

    public void setVisits(String visits) {
        this.visits = visits;
    }

    public String getAverageVisits() {
        return averageVisits;
    }

    public void setAverageVisits(String averageVisits) {
        this.averageVisits = averageVisits;
    }

    public String getAverageStayTime() {
        return averageStayTime;
    }

    public void setAverageStayTime(String averageStayTime) {
        this.averageStayTime = averageStayTime;
    }
}
