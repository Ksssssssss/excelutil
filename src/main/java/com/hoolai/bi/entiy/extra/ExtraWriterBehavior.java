package com.hoolai.bi.entiy.extra;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.hoolai.bi.context.ReportEnvConfig;
import com.hoolai.bi.entiy.Datas;
import com.hoolai.bi.entiy.QueryInfo;
import com.hoolai.bi.entiy.daily.DailyStats;
import com.hoolai.bi.entiy.daily.DailyStatsDatas;
import com.hoolai.bi.excel.ExcelDatas;
import com.hoolai.bi.excel.ExcelWriterBehavior;
import com.hoolai.bi.excel.info.ExcelStyleStrategy;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-10-16 10:43
 */
public class ExtraWriterBehavior implements ExcelWriterBehavior {

    @Override
    public void write(int index, ExcelDatas reportDatas, ExcelWriter excelWriter, ExcelStyleStrategy excelStyleStrategy, QueryInfo info) {
        WriteSheet writeSheet;
        Datas datas = (Datas) reportDatas;
        if (CollectionUtils.isEmpty(datas.getInfos())) {
            return;
        }
        Class clazz = datas.getInfos().stream().findFirst().get().getClass();
        writeSheet = EasyExcel.writerSheet(index, datas.getReportType().getName()).registerWriteHandler(excelStyleStrategy.customCellStyle()).head(clazz).build();
        excelWriter.write(datas.getInfos(), writeSheet);
    }
}
