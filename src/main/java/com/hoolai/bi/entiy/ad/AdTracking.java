package com.hoolai.bi.entiy.ad;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.hoolai.bi.entiy.GameInfo;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-12-02 21:13
 * 
 */
 
public class AdTracking extends GameInfo {
    @ExcelProperty(value = "标识")
    private String ifa;
    @ExcelProperty(value = "人数")
    private int numbers;
    @ExcelProperty(value = "次数")
    private int frequency;
    @ExcelProperty(value = "描述")
    private String desc;

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
}
