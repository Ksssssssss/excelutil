package com.hoolai.bi.entiy.retention;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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
    private RetentionType retentionType;

    public ShareRetentions() {
    }

    public ShareRetentions(Map<String, List<ShareRetention>> shareRetentions, RetentionType retentionType) {
        this.shareRetentions = shareRetentions;
        this.retentionType = retentionType;
    }

    public List<List<ShareRetention>> groupAndGetKey(String ds) {
        List<List<ShareRetention>> result = Lists.newArrayList();
        switch (retentionType) {
            case RETENTION:
                result.add(shareRetentions.get(ds));
                break;
            case RETENTION_OS:
                result = shareOsRetentions(ds);
                break;
        }
        return result;
    }

    private List<List<ShareRetention>> shareOsRetentions(String ds) {
        List<ShareRetention> shareRetentionList = shareRetentions.get(ds);
        if (CollectionUtils.isEmpty(shareRetentionList)){
            return new ArrayList<>();
        }
        Map<String, List<ShareRetention>> result = shareRetentionList.stream().collect(Collectors.groupingBy(shareRetention -> ((ShareOsRetention) shareRetention).getOs()));
        return new ArrayList<List<ShareRetention>>(result.values());
    }

    public Map<String, List<ShareRetention>> getShareRetentions() {
        return shareRetentions;
    }

    public void setShareRetentions(Map<String, List<ShareRetention>> shareRetentions) {
        this.shareRetentions = shareRetentions;
    }

    public RetentionType getRetentionType() {
        return retentionType;
    }

    public void setRetentionType(RetentionType retentionType) {
        this.retentionType = retentionType;
    }

}
