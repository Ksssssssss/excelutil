package com.hoolai.bi.entiy.retention;

import com.google.common.collect.Maps;
import com.hoolai.bi.entiy.ReportType;
import com.hoolai.bi.entiy.ExtraType;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @description: retention集合
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-10-10 17:17
 */

public class ShareRetentions {
    private Map<String, List<ShareRetention>> shareRetentions;
    private ExtraType extraType;

    public ShareRetentions() {
    }

    public ShareRetentions(Map<String, List<ShareRetention>> shareRetentions, ExtraType extraType) {
        this.shareRetentions = shareRetentions;
        this.extraType = extraType;
    }

    public List<List<ShareRetention>> groupAndGetKey(String ds) {
        Map<String, List<ShareRetention>> result = Maps.newHashMap();
        List<ShareRetention> shareRetentionList = shareRetentions.get(ds);
        if (CollectionUtils.isEmpty(shareRetentionList)) {
            return new ArrayList<>();
        }
        switch (extraType) {
            case ALL:
                result.put(ReportType.RETWNTION.getName(), shareRetentionList);
                break;
            case OS:
                result = shareRetentionList.stream().collect(Collectors.groupingBy(shareRetention -> ((ShareOsRetention) shareRetention).getOs()));
                break;
            case CREATIVE:
                result = shareRetentionList.stream().collect(Collectors.groupingBy(shareRetention -> ((ShareCreativeRetention) shareRetention).getCreative()));
                break;
        }
        return new ArrayList<>(result.values());
    }

    public Map<String, List<ShareRetention>> getShareRetentions() {
        return shareRetentions;
    }

    public void setShareRetentions(Map<String, List<ShareRetention>> shareRetentions) {
        this.shareRetentions = shareRetentions;
    }

    public ExtraType getExtraType() {
        return extraType;
    }

    public void setExtraType(ExtraType extraType) {
        this.extraType = extraType;
    }

}
