package com.hoolai.bi.entiy.retention;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-10-10 19:24
 * 
 */
 
public class ShareOsRetention extends ShareRetention{
    @ExcelProperty("操作系统")
    private String os;

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }
}
