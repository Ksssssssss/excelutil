package com.hoolai.bi.entiy.excel.writer;

import com.alibaba.excel.ExcelWriter;
import com.hoolai.bi.entiy.ExcelDatas;
import com.hoolai.bi.entiy.QueryInfo;
import com.hoolai.bi.entiy.excel.ExcelStyleStrategy;
import com.hoolai.bi.entiy.excel.Writer;
import com.hoolai.bi.entiy.excel.ExcelWriterBehavior;
import com.hoolai.bi.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-10-16 09:53
 */

@Component
public class DailyOsWriter extends Writer {

    @Autowired
    private ExcelStyleStrategy excelStyleStrategy;
    @Autowired
    private ExcelWriterBehavior dailyWriterBehavior;
    @Autowired
    private ReportService dailyOsStatsServiceImpl;

    public DailyOsWriter() {
    }

    @Override
    public void write(int index,ExcelWriter excelWriter,QueryInfo info) {
        ExcelDatas reportDatas = produceDatas(info);
        dailyWriterBehavior.write(index,reportDatas,excelWriter,excelStyleStrategy, info);
    }

    @Override
    public ExcelDatas produceDatas(QueryInfo info) {
        ExcelDatas dailyStatsDatas = dailyOsStatsServiceImpl.produceDatas(info.getStartDs(),info.getEndDs(),info.getGameid());;
        return dailyStatsDatas;
    }
}
