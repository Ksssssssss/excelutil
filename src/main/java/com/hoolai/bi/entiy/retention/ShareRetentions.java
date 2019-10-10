package com.hoolai.bi.entiy.retention;

import java.util.List;
import java.util.Map;

/**
 *
 *@description: retention集合
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-10-10 17:17
 */

public class ShareRetentions {
    private List<ShareRetention> shareRetentions;
    private RetentionType retentionType;
    private String ds;

    public ShareRetentions() {
    }

    public ShareRetentions(List<ShareRetention> shareRetentions, RetentionType retentionType, String ds) {
        this.shareRetentions = shareRetentions;
        this.retentionType = retentionType;
        this.ds = ds;
    }

    public List<ShareRetention> getShareRetentions() {
        return shareRetentions;
    }

    public void setShareRetentions(List<ShareRetention> shareRetentions) {
        this.shareRetentions = shareRetentions;
    }

    public RetentionType getRetentionType() {
        return retentionType;
    }

    public void setRetentionType(RetentionType retentionType) {
        this.retentionType = retentionType;
    }

    public String getDs() {
        return ds;
    }

    public void setDs(String ds) {
        this.ds = ds;
    }
}
