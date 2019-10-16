package com.hoolai.bi.entiy.excel.customwriter;

import com.alibaba.excel.ExcelWriter;
import com.hoolai.bi.entiy.excel.ExcelStyleStrategy;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-10-15 21:30
 */

public interface CustomExcelWriter {
    public void write(int index, Object reportDatas, ExcelWriter excelWriter, ExcelStyleStrategy excelStyleStrategy);
}
