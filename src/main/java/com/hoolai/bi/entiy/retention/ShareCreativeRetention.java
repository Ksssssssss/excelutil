package com.hoolai.bi.entiy.retention;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-10-12 15:30
 */

public class ShareCreativeRetention extends ShareRetention {

    @ExcelProperty("渠道")
    private String creative;

    public String getCreative() {
        return creative;
    }

    public void setCreative(String creative) {
        this.creative = creative;
    }
}
