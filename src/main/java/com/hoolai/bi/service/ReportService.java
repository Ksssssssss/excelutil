package com.hoolai.bi.service;

import com.hoolai.bi.entiy.daily.DailyStats;

import java.util.List;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-10-16 11:43
 * 
 */
 
public interface ReportService {
    public List<DailyStats> produceData(String startDs, String endDs, int gameId);
}
