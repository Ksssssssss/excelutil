package com.hoolai.bi.entiy.ip2region;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import com.hoolai.bi.entiy.GameInfo;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2020-01-14 15:12
 */

public class ProvinceAddress extends GameInfo {
    @ExcelProperty(value = "省份")
    private String province;
    @ExcelProperty(value = "人数")
    private int numbers;
    @NumberFormat("0.00%")
    @TableField(exist = false)
    @ExcelProperty(value = "占比")
    private float ratio;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
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
