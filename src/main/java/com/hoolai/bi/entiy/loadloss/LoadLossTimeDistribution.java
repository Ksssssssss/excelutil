package com.hoolai.bi.entiy.loadloss;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import com.hoolai.bi.entiy.GameInfo;
import lombok.Data;

/**
 *
 *@description: 加载流失时间分布
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2020-04-26 16:07
 * 
 */

@Data
public class LoadLossTimeDistribution extends GameInfo {
    @ExcelProperty(value = "时间")
    private String time;
    @ExcelProperty(value = "比例")
    @NumberFormat(value = "0.00%")
    @TableField(exist = false)
    private float rate;
    @ExcelProperty(value = "人数")
    private int nums;
}
