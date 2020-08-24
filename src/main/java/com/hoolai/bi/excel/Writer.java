package com.hoolai.bi.excel;

import com.alibaba.excel.ExcelWriter;
import com.hoolai.bi.entiy.QueryInfo;
import com.hoolai.bi.excel.info.ExcelStyleStrategy;
import com.hoolai.bi.service.ReportService;
import lombok.extern.slf4j.Slf4j;

import java.util.Stack;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-10-16 09:53
 */

@Slf4j
public class Writer extends AbstractWriter {

    private ExcelStyleStrategy excelStyleStrategy;
    private ExcelWriterBehavior writerBehavior;
    private ReportService queryServiceImpl;

    private final ExcelStyleStrategy defaultStyle = new ExcelStyleStrategy();

    public Writer(ExcelWriterBehavior writerBehavior, ReportService queryServiceImpl) {
        this();
        this.writerBehavior = writerBehavior;
        this.queryServiceImpl = queryServiceImpl;
    }

    public Writer() {
        this.excelStyleStrategy = defaultStyle;
    }

    @Override
    public void write(int index, ExcelWriter excelWriter, QueryInfo info) {
        log.info("handler service = {}",queryServiceImpl.getClass());
        ExcelDatas reportDatas = produceDatas(info);
        if (reportDatas == null) {
            return;
        }
        writerBehavior.write(index, reportDatas, excelWriter, excelStyleStrategy, info);
    }

    @Override
    public ExcelDatas produceDatas(QueryInfo info) {
        ExcelDatas dailyStatsDatas = queryServiceImpl.produceDatas(info.getStartDs(), info.getEndDs(), info.getGameid(), info.getSnid());
        return dailyStatsDatas;
    }
}
