package com.hoolai.bi.entiy.time;

import com.alibaba.excel.annotation.ExcelProperty;
import com.hoolai.bi.entiy.GameInfo;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2020-03-12 16:28
 * 
 */
 
public class TimeDistribution extends GameInfo {
    @ExcelProperty(value = "时间")
    private int hour;
    @ExcelProperty(value = "人数")
    private int nums;

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getNums() {
        return nums;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }
}
