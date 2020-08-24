package com.hoolai.bi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hoolai.bi.entiy.Datas;
import com.hoolai.bi.entiy.ReportType;
import com.hoolai.bi.entiy.daily.DailyAdStats;
import com.hoolai.bi.entiy.loadloss.LoadLoss;
import com.hoolai.bi.entiy.loadloss.LoadLossTimeDistribution;
import com.hoolai.bi.excel.ExcelDatas;
import com.hoolai.bi.mapper.LoadLossMapper;
import com.hoolai.bi.mapper.LoadLossTimeDistributionMapper;
import com.hoolai.bi.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-11-04 20:36
 */

@Service
public class LoadLossTimeDistributionServiceImpl implements ReportService {

    @Autowired
    private LoadLossTimeDistributionMapper loadLossTimeDistributionMapper;

    @Override
    public ExcelDatas produceDatas(String startDs, String endDs, int gameId, int snid) {
        List<LoadLossTimeDistribution> timeDistributions = loadLossTimeDistributionMapper.selectList(
                new LambdaQueryWrapper<LoadLossTimeDistribution>().eq(LoadLossTimeDistribution::getGameid, gameId)
                        .ge(LoadLossTimeDistribution::getDs, startDs)
                        .le(LoadLossTimeDistribution::getDs, endDs));
        Datas datas = new Datas(ReportType.LOAD_LOSS_TIME_DISTRIBUTION, timeDistributions);
        return datas;
    }
}
