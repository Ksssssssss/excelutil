package com.hoolai.bi.service.impl;

import com.hoolai.bi.entiy.ReportType;
import com.hoolai.bi.entiy.retention.RetentionDatas;
import com.hoolai.bi.entiy.retention.RetentionOfAdId;
import com.hoolai.bi.entiy.retention.ShareOsRetention;
import com.hoolai.bi.entiy.retention.ShareRetention;
import com.hoolai.bi.excel.ExcelDatas;
import com.hoolai.bi.excel.info.ExtraType;
import com.hoolai.bi.mapper.RetentionAdIdMapper;
import com.hoolai.bi.mapper.RetentionOsMapper;
import com.hoolai.bi.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-10-10 19:54
 */
@Service
public class RetentionAdIdServiceImpl implements ReportService {
    @Autowired
    private RetentionAdIdMapper retentionAdIdMapper;

    @Override
    public ExcelDatas produceDatas(String startDs, String endDs, int gameId, int snid) {
        List<RetentionOfAdId> retentionOfAdIds = retentionAdIdMapper.queryRetentionAdIds(gameId,startDs,endDs);
        Map<String, List<ShareRetention>> shareRetentionMap = retentionOfAdIds.stream().collect(Collectors.groupingBy(retention -> retention.getDs()));
        RetentionDatas retentionDatas = new RetentionDatas(shareRetentionMap, ReportType.RETWNTION_AD,ExtraType.AD_ID);
        return retentionDatas;
    }
}
