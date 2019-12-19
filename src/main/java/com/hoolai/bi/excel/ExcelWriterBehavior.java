package com.hoolai.bi.excel;

import com.alibaba.excel.ExcelWriter;
import com.hoolai.bi.entiy.QueryInfo;
import com.hoolai.bi.excel.info.ExcelStyleStrategy;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-10-15 21:30
 */

public interface ExcelWriterBehavior {
    public void write(int index, ExcelDatas reportDatas, ExcelWriter excelWriter, ExcelStyleStrategy excelStyleStrategy, QueryInfo info);
}
