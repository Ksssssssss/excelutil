package com.hoolai.bi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hoolai.bi.entiy.Datas;
import com.hoolai.bi.entiy.ReportType;
import com.hoolai.bi.entiy.ip2region.ProvinceAddress;
import com.hoolai.bi.entiy.time.TimeDistribution;
import com.hoolai.bi.entiy.time.TimeDistributionDatas;
import com.hoolai.bi.excel.ExcelDatas;
import com.hoolai.bi.excel.info.ExtraType;
import com.hoolai.bi.mapper.TimeDistributionMapper;
import com.hoolai.bi.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2020-03-12 16:32
 * 
 */
@Service
public class HourDauServiceImpl implements ReportService {
    @Autowired
    private TimeDistributionMapper timeDistributionMapper;

    @Override
    public ExcelDatas produceDatas(String startDs, String endDs, int gameId, int snid) {
        List<TimeDistribution> hourDaus = timeDistributionMapper.queryHourDau(snid,gameId,startDs,endDs);
        return new TimeDistributionDatas(hourDaus,ReportType.HOUR_DAU, ExtraType.HOUR_DAU);
    }
}
