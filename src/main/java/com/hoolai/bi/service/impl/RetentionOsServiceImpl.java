package com.hoolai.bi.service.impl;

import com.hoolai.bi.entiy.ExcelDatas;
import com.hoolai.bi.entiy.ExtraType;
import com.hoolai.bi.entiy.ReportType;
import com.hoolai.bi.entiy.retention.RetentionDatas;
import com.hoolai.bi.entiy.retention.ShareOsRetention;
import com.hoolai.bi.entiy.retention.ShareRetention;
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
public class RetentionOsServiceImpl implements ReportService {
    @Autowired
    private RetentionOsMapper retentionOsMapper;

    @Override
    public ExcelDatas produceDatas(String startDs, String endDs, int gameId) {
        List<ShareOsRetention> retentionResultTypes = retentionOsMapper.queryRetentionOs(gameId, startDs, endDs);
        Map<String, List<ShareRetention>> shareRetentionMap = retentionResultTypes.stream().collect(Collectors.groupingBy(retention -> retention.getDs()));
        RetentionDatas retentionDatas = new RetentionDatas(shareRetentionMap, ReportType.RETWNTION_OS,ExtraType.OS);
        return retentionDatas;
    }
}
