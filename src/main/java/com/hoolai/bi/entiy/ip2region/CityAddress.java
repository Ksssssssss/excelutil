package com.hoolai.bi.entiy.ip2region;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import com.hoolai.bi.entiy.GameInfo;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2020-01-14 15:28
 */

public class CityAddress extends GameInfo {
    @ExcelProperty(value = "城市")
    private String city;
    @ExcelProperty(value = "人数")
    private int numbers;

    @NumberFormat("0.00%")
    @TableField(exist = false)
    @ExcelProperty(value = "占比")
    private float ratio;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
