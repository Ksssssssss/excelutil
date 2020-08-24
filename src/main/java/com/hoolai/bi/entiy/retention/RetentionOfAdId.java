package com.hoolai.bi.entiy.retention;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2020-04-20 17:29
 */

@Data
public class RetentionOfAdId extends ShareRetention{
    @ExcelProperty("广告")
    private String adId;
}
