package com.hoolai.bi.entiy.extra;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import com.hoolai.bi.entiy.GameInfo;
import lombok.Data;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2020-07-30 17:37
 */

@Data
public class Extra extends GameInfo {
    @TableField(exist = false)
    @ExcelProperty(value = "活跃")
    private int dauNum;
    @TableField(exist = false)
    @ExcelProperty(value = "安装")
    private int installNum;

    @ExcelProperty("抽中次数")
    private int winningNum;
    @ExcelProperty("总次数")
    private int  winningCount;

    @NumberFormat("0.00%")
    @ExcelProperty("抽中率")
    private float winingRate;

    @ExcelProperty("重试次数")
    private String retryTime;
    @ExcelProperty("分享次数")
    private String shareClick;
    @ExcelProperty("选择男性")
    @NumberFormat("0.00%")
    private float chooseMenRate;
    @ExcelProperty("选择女性")
    @NumberFormat("0.00%")
    private float chooseWomanRate;

}
