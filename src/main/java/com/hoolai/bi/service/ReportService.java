package com.hoolai.bi.service;

import com.hoolai.bi.excel.ExcelDatas;
import org.springframework.stereotype.Service;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-10-16 11:43
 * 
 */

public interface ReportService {
    public ExcelDatas produceDatas(String startDs, String endDs, int gameId);

}
