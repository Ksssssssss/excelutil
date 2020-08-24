package com.hoolai.bi.service.impl;

import com.hoolai.bi.excel.info.ExtraType;
import com.hoolai.bi.entiy.ReportType;
import com.hoolai.bi.entiy.retention.RetentionDatas;
import com.hoolai.bi.entiy.retention.ShareRetention;
import com.hoolai.bi.mapper.RetentionMapper;
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
public class RetentionServiceImpl implements ReportService {
    @Autowired
    private RetentionMapper retentionMapper;

    @Override
    public RetentionDatas produceDatas(String startDs, String endDs, int gameId, int snid) {
        List<ShareRetention> retentionResultTypes = retentionMapper.queryRetens(gameId, startDs, endDs);
        Map<String, List<ShareRetention>> shareRetentionMap = retentionResultTypes.stream().collect(Collectors.groupingBy(retention -> retention.getDs()));
        return new RetentionDatas(shareRetentionMap, ReportType.RETWNTION, ExtraType.SIMPLE);
    }
}
