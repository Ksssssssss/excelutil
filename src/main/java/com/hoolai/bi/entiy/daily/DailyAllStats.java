package com.hoolai.bi.entiy.daily;

import com.alibaba.excel.annotation.ExcelProperty;
import com.hoolai.bi.context.GameContext;
import com.hoolai.bi.context.ReportEnvConfig;
import lombok.Data;

import java.util.Optional;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-10-12 16:36
 */

@Data
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

    @ExcelProperty(value = "周活跃", index = 15)
    private int wau;
    @ExcelProperty(value = "月活跃", index = 16)
    private int mau;
    @ExcelProperty(value = "周安装", index = 17)
    private int wnu;
    @ExcelProperty(value = "月安装", index = 18)
    private int mnu;

    @ExcelProperty(value = "周付费", index = 19)
    private float weekPay;
    @ExcelProperty(value = "月付费", index = 20)
    private float monthPay;

    @Override
    public void init(GameContext gameContext) {
        super.init(gameContext);
        float rate = gameContext.get(gameid).getCurrencyRate();
        weekPay /= rate;
        monthPay /= rate;

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

    public void initExtra(WeekInfo week, MonthInfo month) {
        this.wau = week.getWdu();
        this.wnu = week.getWnu();
        this.mau = month.getMdu();
        this.mnu = month.getMnu();
        this.weekPay = week.getWeekPayment();
        this.monthPay = month.getMonthPayment();
    }

    public void initPay(int weekPay, int monthPay) {
        this.weekPay = weekPay;
        this.monthPay = monthPay;
    }

}
