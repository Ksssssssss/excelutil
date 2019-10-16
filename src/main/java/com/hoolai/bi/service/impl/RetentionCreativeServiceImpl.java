package com.hoolai.bi.service.impl;


import com.hoolai.bi.entiy.ExtraType;
import com.hoolai.bi.entiy.retention.ShareCreativeRetention;
import com.hoolai.bi.entiy.retention.ShareRetention;
import com.hoolai.bi.entiy.retention.ShareRetentions;
import com.hoolai.bi.mapper.RetentionCreativeMapper;
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
public class RetentionCreativeServiceImpl extends RetentionReportService {
    @Autowired
    private RetentionCreativeMapper retentionCreativeMapper;

    @Override
    protected ShareRetentions produceRetention(String startDs, String endDs, int gameId) {
        List<ShareCreativeRetention> retentionResultTypes = retentionCreativeMapper.queryRetentionCreative(gameId, startDs, endDs);
        Map<String, List<ShareRetention>> shareRetentionMap = retentionResultTypes.stream().collect(Collectors.groupingBy(retention -> retention.getDs()));
        return new ShareRetentions(shareRetentionMap, ExtraType.CREATIVE);
    }
}
