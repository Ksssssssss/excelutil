package com.hoolai.bi.entiy.daily;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2020-04-17 16:16
 */

@Data
public class DailyAdStats extends DailyStats {

    @TableId
    @ExcelProperty(value = "广告", index = 1)
    private String adId;

    @TableField(exist = false)
    @ExcelIgnore
    private int newPayCount;
    @TableField(exist = false)
    @ExcelIgnore
    private float newPayAmount;
    @TableField(exist = false)
    @ExcelIgnore
    private int newPayTimes;

}
