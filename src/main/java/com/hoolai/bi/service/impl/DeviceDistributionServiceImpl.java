package com.hoolai.bi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Maps;
import com.hoolai.bi.entiy.ReportType;
import com.hoolai.bi.entiy.daily.DailyOsStats;
import com.hoolai.bi.entiy.daily.DailyStats;
import com.hoolai.bi.entiy.daily.DailyStatsDatas;
import com.hoolai.bi.entiy.device.DeviceDatas;
import com.hoolai.bi.entiy.device.DeviceDistribution;
import com.hoolai.bi.entiy.rank.RankDistribution;
import com.hoolai.bi.entiy.rank.RankDistributions;
import com.hoolai.bi.excel.ExcelDatas;
import com.hoolai.bi.excel.info.ExtraType;
import com.hoolai.bi.mapper.DeviceDistributionsMapper;
import com.hoolai.bi.service.ReportService;
import com.hoolai.bi.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-11-04 20:36
 * 
 */

@Service
public class DeviceDistributionServiceImpl implements ReportService {

    @Autowired
    private DeviceDistributionsMapper deviceDistributionsMapper;

    @Override
    public ExcelDatas produceDatas(String startDs, String endDs, int gameId) {
        DeviceDatas deviceDatas;
        List<DeviceDistribution> deviceDistributions = deviceDistributionsMapper.queryInstallDevice(gameId,endDs);
        deviceDatas = new DeviceDatas(deviceDistributions,ReportType.INSTALL_DEVICE_DISTRIBUTION);

        return deviceDatas;
    }
}
