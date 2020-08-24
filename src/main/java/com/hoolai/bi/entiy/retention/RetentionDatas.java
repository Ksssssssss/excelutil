package com.hoolai.bi.entiy.retention;

import com.google.common.collect.Maps;
import com.hoolai.bi.excel.ExcelDatas;
import com.hoolai.bi.entiy.ReportType;
import com.hoolai.bi.excel.info.ExtraType;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @description: retention集合
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-10-10 17:17
 */

@Data
public class RetentionDatas implements ExcelDatas {
    private Map<String, List<ShareRetention>> shareRetentions;
    private ReportType type;
    private ExtraType extraType;

    public RetentionDatas(Map<String, List<ShareRetention>> shareRetentions, ReportType type,ExtraType extraType) {
        this.shareRetentions = shareRetentions;
        this.type = type;
        this.extraType = extraType;
    }

    public List<List<ShareRetention>> groupAndGetKey(String ds) {
        Map<String, List<ShareRetention>> result = Maps.newHashMap();
        List<ShareRetention> shareRetentionList = shareRetentions.get(ds);
        if (CollectionUtils.isEmpty(shareRetentionList)) {
            return new ArrayList<>();
        }
        switch (type) {
            case RETWNTION:
                result.put(ReportType.RETWNTION.getName(), shareRetentionList);
                break;
            case RETWNTION_OS:
                result = shareRetentionList.stream().collect(Collectors.groupingBy(shareRetention -> ((ShareOsRetention) shareRetention).getOs()));
                break;
            case RETWNTION_CREATIVE:
                result = shareRetentionList.stream().collect(Collectors.groupingBy(shareRetention -> ((ShareCreativeRetention) shareRetention).getCreative()));
                break;
            case RETWNTION_AD:
                result = shareRetentionList.stream().collect(Collectors.groupingBy(shareRetention -> ((RetentionOfAdId) shareRetention).getAdId()));
                break;
        }
        return new ArrayList<>(result.values());
    }

    public boolean isEmpty(){
        return CollectionUtils.isEmpty(shareRetentions);
    }

}