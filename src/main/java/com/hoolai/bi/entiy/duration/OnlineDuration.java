package com.hoolai.bi.entiy.duration;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.hoolai.bi.entiy.GameInfo;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-11-28 19:47
 * 
 */
 
public class OnlineDuration extends GameInfo {
    @ExcelProperty(value = "在线时长")
    private String duration;
    @ExcelProperty(value = "安装人数")
    private int numbers;
    @ExcelProperty(value = "次日人数")
    private int retentionNumbers;

    @NumberFormat("0.00%")
    @ExcelProperty(value = "次留")
    private float retentionRatio;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getNumbers() {
        return numbers;
    }

    public void setNumbers(int numbers) {
        this.numbers = numbers;
    }

    public int getRetentionNumbers() {
        return retentionNumbers;
    }

    public void setRetentionNumbers(int retentionNumbers) {
        this.retentionNumbers = retentionNumbers;
    }

    public float getRetentionRatio() {
        return retentionRatio;
    }

    public void setRetentionRatio(float retentionRatio) {
        this.retentionRatio = retentionRatio;
    }
}
