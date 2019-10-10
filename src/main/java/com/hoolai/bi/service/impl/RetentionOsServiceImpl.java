package com.hoolai.bi.service.impl;

import com.hoolai.bi.entiy.retention.RetentionType;
import com.hoolai.bi.entiy.retention.ShareOsRetention;
import com.hoolai.bi.entiy.retention.ShareRetention;
import com.hoolai.bi.entiy.retention.ShareRetentions;
import com.hoolai.bi.mapper.RetentionMapper;
import com.hoolai.bi.mapper.RetentionOsMapper;
import com.hoolai.bi.service.RetentionReportService;
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
public class RetentionOsServiceImpl extends RetentionReportService {
    @Autowired
    private RetentionOsMapper retentionOsMapper;

    @Override
    protected ShareRetentions produceRetention(String startDs, String endDs, int gameId) {
        List<ShareOsRetention> retentionResultTypes = retentionOsMapper.queryRetentionOs(gameId, startDs, endDs);
        Map<String, List<ShareRetention>> shareRetentionMap = retentionResultTypes.stream().collect(Collectors.groupingBy(retention -> retention.getDs()));
        return new ShareRetentions(shareRetentionMap, RetentionType.RETENTION_OS);
    }
}
