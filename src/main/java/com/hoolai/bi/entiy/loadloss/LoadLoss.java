package com.hoolai.bi.entiy.loadloss;

import com.alibaba.excel.annotation.ExcelProperty;
import com.hoolai.bi.entiy.GameInfo;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-12-19 10:52
 * 
 */
 
public class LoadLoss extends GameInfo {
    @ExcelProperty(value = "步骤")
    private String step;
    @ExcelProperty(value = "人数")
    private int numbers;
    @ExcelProperty(value = "描述")
    private String description;

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public int getNumbers() {
        return numbers;
    }

    public void setNumbers(int numbers) {
        this.numbers = numbers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
