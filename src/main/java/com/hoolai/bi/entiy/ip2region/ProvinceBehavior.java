package com.hoolai.bi.entiy.ip2region;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.hoolai.bi.entiy.Datas;
import com.hoolai.bi.entiy.QueryInfo;
import com.hoolai.bi.excel.ExcelDatas;
import com.hoolai.bi.excel.ExcelWriterBehavior;
import com.hoolai.bi.excel.info.ExcelStyleStrategy;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2020-01-14 16:03
 */

public class ProvinceBehavior implements ExcelWriterBehavior {

    @Override
    public void write(int index, ExcelDatas reportDatas, ExcelWriter excelWriter, ExcelStyleStrategy excelStyleStrategy, QueryInfo info) {
        WriteSheet writeSheet;
        Datas infos = (Datas) reportDatas;

        if (CollectionUtils.isEmpty(infos.getInfos())) {
            return;
        }

        List<ProvinceAddress> datas = (List<ProvinceAddress>) infos.getInfos();
        double sum = datas.stream().mapToDouble(ProvinceAddress::getNumbers).sum();
        datas.forEach(provinceAddress -> provinceAddress.setRatio((float) (provinceAddress.getNumbers()/sum)));
        Class clazz = datas.stream().findFirst().get().getClass();
        writeSheet = EasyExcel.writerSheet(index, infos.getReportType().getName()).registerWriteHandler(excelStyleStrategy.customCellStyle()).head(clazz).build();
        excelWriter.write(datas, writeSheet);
    }
}
