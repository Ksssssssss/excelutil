package com.hoolai.bi.entiy.loadloss;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.hoolai.bi.entiy.GameInfo;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-12-19 10:52
 */

@Data
public class LoadLoss extends GameInfo {
    @ExcelProperty(value = "步骤")
    private String step;
    @ExcelProperty(value = "人数")
    private int numbers;
    @NumberFormat("0.00%")
    @ExcelProperty(value = "比例")
    private float rate;
    @ExcelProperty(value = "描述")
    private String description;
    @ExcelIgnore
    private int priority;

}
