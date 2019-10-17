package com.hoolai.bi.entiy.excel.behavior;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.hoolai.bi.context.ReportEnvConfig;
import com.hoolai.bi.entiy.ExcelDatas;
import com.hoolai.bi.entiy.GameInfo;
import com.hoolai.bi.entiy.QueryInfo;
import com.hoolai.bi.entiy.excel.Excel;
import com.hoolai.bi.entiy.excel.ExcelStyleStrategy;
import com.hoolai.bi.entiy.excel.ExcelWriterBehavior;
import com.hoolai.bi.entiy.retention.RetentionDatas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-10-16 17:22
 * 
 */

@Component
public class RetentionWriterBehavior implements ExcelWriterBehavior {
    @Autowired
    private ReportEnvConfig config;
    @Autowired
    private ExcelStyleStrategy excelStyleStrategy;

    @Override
    public void write(int index, ExcelDatas reportDatas, ExcelWriter excelWriter, ExcelStyleStrategy excelStyleStrategy, QueryInfo info) {
        WriteSheet writeSheet;
        Excel excel = Excel.fullExcel(info,reportDatas,config);
        RetentionDatas retentionDatas = (RetentionDatas)reportDatas;
        writeSheet = EasyExcel.writerSheet(index, retentionDatas.getType().getName()).needHead(Boolean.FALSE).build();
        List<List<Object>> rows = excel.getRows();
        List<List<String>> headList = excel.getHeads();
        WriteTable table = EasyExcel.writerTable(0).registerWriteHandler(excelStyleStrategy.customCellStyle()).needHead(true).build();
        table.setHead(headList);
        excelWriter.write(rows, writeSheet, table);
    }

}
