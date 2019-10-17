package com.hoolai.bi.entiy.excel;

import com.alibaba.excel.ExcelWriter;
import com.hoolai.bi.entiy.ExcelDatas;
import com.hoolai.bi.entiy.QueryInfo;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-10-15 21:30
 */

public interface ExcelWriterBehavior {
    public void write(int index, ExcelDatas reportDatas, ExcelWriter excelWriter, ExcelStyleStrategy excelStyleStrategy, QueryInfo info);
}
