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

public class LoadLossTimeDistributionBehavior implements ExcelWriterBehavior {

    @Override
    public void write(int index, ExcelDatas reportDatas, ExcelWriter excelWriter, ExcelStyleStrategy excelStyleStrategy, QueryInfo info) {
        WriteSheet writeSheet;
        Datas infos = (Datas) reportDatas;
        List<LoadLossTimeDistribution> datas = (List<LoadLossTimeDistribution>) infos.getInfos();
        float sum = datas.stream().mapToInt(data -> data.getNums()).sum();
        datas.stream().forEach(data -> data.setRate(data.getNums() / sum));
        if (CollectionUtils.isEmpty(datas)) {
            return;
        }
        Class clazz = datas.stream().findFirst().get().getClass();
        writeSheet = EasyExcel.writerSheet(index, infos.getReportType().getName()).registerWriteHandler(excelStyleStrategy.customCellStyle()).head(clazz).build();
        excelWriter.write(datas, writeSheet);
    }
}
