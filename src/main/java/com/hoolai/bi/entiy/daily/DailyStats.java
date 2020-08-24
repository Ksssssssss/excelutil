package com.hoolai.bi.entiy.daily;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import com.hoolai.bi.context.GameContext;
import com.hoolai.bi.context.ReportEnvConfig;
import com.hoolai.bi.entiy.GameInfo;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-09-29 11:04
 */

@Component
@Data
public abstract class DailyStats extends GameInfo {
    @ExcelProperty(value = "活跃")
    private int dauNum;
    @ExcelProperty(value = "安装")
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

    @TableField(exist = false)
    @ExcelProperty("安装付费率")
    private String installPayRate;

    @TableField(exist = false)
    @ExcelProperty("安装arpu(元)")
    private String installArpu;
    @TableField(exist = false)
    @ExcelProperty("安装arppu(元)")
    private String installArppu;

    @ExcelProperty("新付费人数")
    private int newPayCount;
    @NumberFormat("0.00")
    @ExcelProperty(value = "新付费金额（元）")
    private float newPayAmount;
    @ExcelProperty("新付费次数")
    private int newPayTimes;

    public DailyStats() {
    }

    public void init(GameContext gameContext) {
        initRate(gameContext);
        arpu = String.format("%.2f", checkDivide(payAmount, dauNum));
        arppu = String.format("%.2f", checkDivide(payAmount, payCount));
        payRate = String.format("%.2f", checkDivide(payCount, dauNum) * 100) + "%";
        installPayRate = String.format("%.2f", checkDivide(payInstallCount, installNum) * 100) + "%";
        installArpu = String.format("%.2f", checkDivide(payInstallAmount, installNum));
        installArppu = String.format("%.2f", checkDivide(payInstallAmount, payInstallCount));
    }

    private void initRate(GameContext gameContext) {
        float currencyRate = gameContext.get(gameid).getCurrencyRate();
        payAmount /= currencyRate;
        payInstallAmount /= currencyRate;
        newPayAmount /= currencyRate;
    }

}
