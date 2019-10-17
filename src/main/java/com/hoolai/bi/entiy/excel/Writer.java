package com.hoolai.bi.entiy.excel;

import com.alibaba.excel.ExcelWriter;
import com.hoolai.bi.entiy.ExcelDatas;
import com.hoolai.bi.entiy.QueryInfo;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-10-16 10:10
 */

public abstract class Writer {

    public abstract void write(int index, ExcelWriter excelWriter, QueryInfo info);

    public abstract ExcelDatas produceDatas(QueryInfo info);
}
