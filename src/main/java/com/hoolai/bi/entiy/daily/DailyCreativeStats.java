package com.hoolai.bi.entiy.daily;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-09-29 19:28
 */

public class DailyCreativeStats extends DailyStats {
    @TableId
    @ExcelProperty(value = "渠道", index = 1)
    private String creative;

    public String getCreative() {
        return creative;
    }

    public void setCreative(String creative) {
        this.creative = creative;
    }

}
