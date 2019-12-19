package com.hoolai.bi.service.impl;

import com.hoolai.bi.entiy.ReportType;
import com.hoolai.bi.entiy.ad.AdTracking;
import com.hoolai.bi.entiy.Datas;
import com.hoolai.bi.excel.ExcelDatas;
import com.hoolai.bi.mapper.AdTrackingMapper;
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
public class AdTrackingServiceImpl implements ReportService {

    @Autowired
    private AdTrackingMapper adTrackingMapper;

    @Override
    public ExcelDatas produceDatas(String startDs, String endDs, int gameId) {
        List<AdTracking> adTrackings = adTrackingMapper.query(gameId,startDs,endDs);
        Datas datas = new Datas(ReportType.AD_TRACKING, adTrackings);
        return datas;
    }
}
