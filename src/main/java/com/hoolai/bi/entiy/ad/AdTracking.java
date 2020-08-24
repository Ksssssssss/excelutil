package com.hoolai.bi.entiy.ad;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.baomidou.mybatisplus.annotation.TableId;
import com.hoolai.bi.entiy.GameInfo;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-12-02 21:13
 */

public class AdTracking extends GameInfo {
    @ExcelIgnore
    private String ifa;

    @ExcelProperty(value = "描述")
    private String desc;
    @ExcelProperty(value = "人数")
    private int numbers;
    @ExcelProperty(value = "次数")
    private int frequency;

    @NumberFormat(value = "0.00%")
    @ExcelProperty(value = "占比(人数/dau)")
    private float rate;
    @ExcelProperty(value = "平均次数")
    private float averageFrequency;

    public String getIfa() {
        return ifa;
    }

    public void setIfa(String ifa) {
        this.ifa = ifa;
    }

    public int getNumbers() {
        return numbers;
    }

    public void setNumbers(int numbers) {
        this.numbers = numbers;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public float getAverageFrequency() {
        return averageFrequency;
    }

    public void setAverageFrequency(float averageFrequency) {
        this.averageFrequency = averageFrequency;
    }
}
