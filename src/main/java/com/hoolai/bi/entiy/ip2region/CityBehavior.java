package com.hoolai.bi.entiy.ip2region;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.hoolai.bi.entiy.Datas;
import com.hoolai.bi.entiy.QueryInfo;
import com.hoolai.bi.entiy.ReportType;
import com.hoolai.bi.entiy.loadloss.LoadLoss;
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
 * @time: 2020-01-14 16:03
 */

public class CityBehavior implements ExcelWriterBehavior {

    @Override
    public void write(int index, ExcelDatas reportDatas, ExcelWriter excelWriter, ExcelStyleStrategy excelStyleStrategy, QueryInfo info) {
        WriteSheet writeSheet;
        Datas infos = (Datas) reportDatas;

        if (CollectionUtils.isEmpty(infos.getInfos())) {
            return;
        }

        List<CityAddress> datas = (List<CityAddress>) infos.getInfos();
        double sum = datas.stream().mapToDouble(CityAddress::getNumbers).sum();
        datas.forEach(cityAddress -> cityAddress.setRatio((float) (cityAddress.getNumbers() / sum)));
        Class clazz = datas.stream().findFirst().get().getClass();
        writeSheet = EasyExcel.writerSheet(index, infos.getReportType().getName()).registerWriteHandler(excelStyleStrategy.customCellStyle()).head(clazz).build();
        excelWriter.write(datas, writeSheet);
    }
}
