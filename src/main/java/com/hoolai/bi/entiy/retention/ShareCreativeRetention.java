package com.hoolai.bi.entiy.retention;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-10-12 15:30
 */

@Data
public class ShareCreativeRetention extends ShareRetention {

    @ExcelProperty("渠道")
    private String creative;

}
