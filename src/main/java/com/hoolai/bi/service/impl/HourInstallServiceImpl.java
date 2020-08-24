package com.hoolai.bi.service.impl;

import com.hoolai.bi.entiy.Datas;
import com.hoolai.bi.entiy.ReportType;
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
public class HourInstallServiceImpl implements ReportService {
    @Autowired
    private TimeDistributionMapper timeDistributionMapper;

    @Override
    public ExcelDatas produceDatas(String startDs, String endDs, int gameId, int snid) {
        List<TimeDistribution> hourInstalls = timeDistributionMapper.queryHourInstall(snid,gameId,startDs,endDs);
        return new TimeDistributionDatas(hourInstalls,ReportType.HOUR_INSTALL, ExtraType.HOUR_INSTALL);
    }
}
