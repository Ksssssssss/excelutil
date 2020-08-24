package com.hoolai.bi.service.impl;

import com.hoolai.bi.entiy.Datas;
import com.hoolai.bi.entiy.ReportType;
import com.hoolai.bi.entiy.ip2region.CityAddress;
import com.hoolai.bi.excel.ExcelDatas;
import com.hoolai.bi.mapper.IpAddressMapper;
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
public class CityServiceImpl implements ReportService {

    @Autowired
    private IpAddressMapper ipAddressMapper;

    @Override
    public ExcelDatas produceDatas(String startDs, String endDs, int gameId, int snid) {
        List<CityAddress> addresses = ipAddressMapper.queryCity(gameId,endDs);
        Datas datas = new Datas(ReportType.CITY, addresses);
        return datas;
    }
}
