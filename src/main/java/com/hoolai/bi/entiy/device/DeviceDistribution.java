package com.hoolai.bi.entiy.device;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import com.hoolai.bi.entiy.GameInfo;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-11-28 16:21
 * 
 */
 
public class DeviceDistribution extends GameInfo {
    @ExcelProperty(value = "设备")
    private String device;
    @ExcelProperty(value = "安装人数")
    private int numbers;

    @NumberFormat("0.00%")
    @ExcelProperty("设备比")
    private float ratio;

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public int getNumbers() {
        return numbers;
    }

    public void setNumbers(int numbers) {
        this.numbers = numbers;
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }
}
