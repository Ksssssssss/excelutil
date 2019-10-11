package com.hoolai.bi.context;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-10-11 14:41
 */
@Component
@ConfigurationProperties(prefix = "report.env")
public class ReportEnvConfig {
    /** 转换汇率日期*/
    private String changeRateDs;
    /** 转换比例*/
    private int rate;
    /** 最大的留存天数*/
    private int maxRetentionDay;

    public String getChangeRateDs() {
        return changeRateDs;
    }

    public void setChangeRateDs(String changeRateDs) {
        this.changeRateDs = changeRateDs;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getMaxRetentionDay() {
        return maxRetentionDay;
    }

    public void setMaxRetentionDay(int maxRetentionDay) {
        this.maxRetentionDay = maxRetentionDay;
    }
}
