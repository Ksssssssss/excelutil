package com.hoolai.bi.entiy.loadloss;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.hoolai.bi.entiy.Datas;
import com.hoolai.bi.entiy.QueryInfo;
import com.hoolai.bi.excel.ExcelDatas;
import com.hoolai.bi.excel.ExcelWriterBehavior;
import com.hoolai.bi.excel.info.ExcelStyleStrategy;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-12-19 11:58
 */

public class LoadLossBehavior implements ExcelWriterBehavior {

    @Override
    public void write(int index, ExcelDatas reportDatas, ExcelWriter excelWriter, ExcelStyleStrategy excelStyleStrategy, QueryInfo info) {
        WriteSheet writeSheet;
        Datas infos = (Datas) reportDatas;
        List<LoadLoss> datas = (List<LoadLoss>) infos.getInfos();
        if (CollectionUtils.isEmpty(datas)) {
            return;
        }
        datas = datas.stream().sorted(Comparator.comparing(LoadLoss::getPriority)).collect(Collectors.toList());
        Class clazz = datas.stream().findFirst().get().getClass();
        writeSheet = EasyExcel.writerSheet(index, infos.getReportType().getName()).registerWriteHandler(excelStyleStrategy.customCellStyle()).head(clazz).build();
        excelWriter.write(datas, writeSheet);
    }
}
