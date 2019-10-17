package com.hoolai.bi.entiy.excel.writer;

import com.alibaba.excel.ExcelWriter;
import com.hoolai.bi.entiy.ExcelDatas;
import com.hoolai.bi.entiy.QueryInfo;
import com.hoolai.bi.entiy.excel.ExcelStyleStrategy;
import com.hoolai.bi.entiy.excel.ExcelWriterBehavior;
import com.hoolai.bi.entiy.excel.Writer;
import com.hoolai.bi.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-10-16 09:53
 */

@Component
public class RetentionCreativeWriter extends Writer {

    @Autowired
    private ExcelStyleStrategy excelStyleStrategy;
    @Autowired
    private ExcelWriterBehavior retentionWriterBehavior;
    @Autowired
    private ReportService retentionCreativeServiceImpl;

    public RetentionCreativeWriter() {
    }

    @Override
    public void write(int index, ExcelWriter excelWriter, QueryInfo info) {
        ExcelDatas reportDatas = produceDatas(info);
        retentionWriterBehavior.write(index,reportDatas,excelWriter,excelStyleStrategy, info);
    }

    @Override
    public ExcelDatas produceDatas(QueryInfo info) {
        ExcelDatas dailyStatsDatas = retentionCreativeServiceImpl.produceDatas(info.getStartDs(),info.getEndDs(),info.getGameid());;
        return dailyStatsDatas;
    }
}
