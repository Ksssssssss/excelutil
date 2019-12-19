package com.hoolai.bi.context;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.hoolai.bi.entiy.daily.DailyExtraInfo;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-10-09 19:58
 * 
 */
 
public class  ExtraInfoConvert implements Converter<DailyExtraInfo> {

    public ExtraInfoConvert() {
    }

    @Override
    public Class supportJavaTypeKey() {
        return DailyExtraInfo.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public DailyExtraInfo convertToJavaData(CellData cellData, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return null;
    }

    @Override
    public CellData convertToExcelData(DailyExtraInfo dailyExtraInfo, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return new CellData(dailyExtraInfo.toString());
    }
}
