package com.hoolai.bi.service;

import com.hoolai.bi.entiy.ExcelDatas;
import com.hoolai.bi.entiy.daily.DailyStats;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-10-16 11:43
 * 
 */

@Service
public interface ReportService {
    public ExcelDatas produceDatas(String startDs, String endDs, int gameId);
}
