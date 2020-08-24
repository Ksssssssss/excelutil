package com.hoolai.bi.entiy;

import com.baomidou.mybatisplus.annotation.TableField;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-12-03 10:49
 */

public class Game {

    private int parentId;

    private int gameid;

    /**
     * 游戏名称
     */
    private String name;

    /**
     * 游戏别名
     */
    private String alias;

    /**
     * 游戏logo，服务器图片地址
     */
    private String logo;

    /**
     * 月KPI
     */
    private double monthKpi;

    /**
     * 年KPI
     */
    private double yearKpi;

    /**
     * 当前月总收入
     */
    private double currMonthKpi;

    /**
     * 当年总收入
     */
    private double currYearKpi;

    /**
     * 货币类型
     */
    private String currency;

    /**
     * 货币汇率（相较RMB)
     */
    private float currencyRate;

    /**
     * 是否开启etl
     */
    private String stats;

    /**
     * 世界时区
     */
    @TableField("timeZone")
    private String timeZone;

    /**
     * 安装总用户
     */
    private int install;

    /**
     * 付费总额
     */
    private int payAmount;

    /**
     * 统计几日留存 0 不开启日志统计
     */
    private int retention;

    /**
     * 上线时间
     */
    private String onlineDate;

    /**
     * etl触发时间
     */
    private String etlTriggerTime;

    /**
     * etl触发id
     */
    private String etlTriggerId;

    /**
     * etl当前状态
     */
    private String etlStatus;

    /**
     * 统计优先级
     */
    private int etlOrder;

    @Override
    public String toString() {
        return "Game{" +
                "parentId=" + parentId +
                ", gameid=" + gameid +
                ", name='" + name + '\'' +
                ", alias='" + alias + '\'' +
                ", logo='" + logo + '\'' +
                ", monthKpi=" + monthKpi +
                ", yearKpi=" + yearKpi +
                ", currMonthKpi=" + currMonthKpi +
                ", currYearKpi=" + currYearKpi +
                ", currency='" + currency + '\'' +
                ", currencyRate=" + currencyRate +
                ", stats='" + stats + '\'' +
                ", timeZone='" + timeZone + '\'' +
                ", install=" + install +
                ", payAmount=" + payAmount +
                ", retention=" + retention +
                ", onlineDate='" + onlineDate + '\'' +
                ", etlTriggerTime='" + etlTriggerTime + '\'' +
                ", etlTriggerId='" + etlTriggerId + '\'' +
                ", etlStatus='" + etlStatus + '\'' +
                ", etlOrder=" + etlOrder +
                '}';
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getGameid() {
        return gameid;
    }

    public void setGameid(int gameid) {
        this.gameid = gameid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public double getMonthKpi() {
        return monthKpi;
    }

    public void setMonthKpi(double monthKpi) {
        this.monthKpi = monthKpi;
    }

    public double getYearKpi() {
        return yearKpi;
    }

    public void setYearKpi(double yearKpi) {
        this.yearKpi = yearKpi;
    }

    public double getCurrMonthKpi() {
        return currMonthKpi;
    }

    public void setCurrMonthKpi(double currMonthKpi) {
        this.currMonthKpi = currMonthKpi;
    }

    public double getCurrYearKpi() {
        return currYearKpi;
    }

    public void setCurrYearKpi(double currYearKpi) {
        this.currYearKpi = currYearKpi;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Float getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(Float currencyRate) {
        this.currencyRate = currencyRate;
    }

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public int getInstall() {
        return install;
    }

    public void setInstall(int install) {
        this.install = install;
    }

    public int getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(int payAmount) {
        this.payAmount = payAmount;
    }

    public int getRetention() {
        return retention;
    }

    public void setRetention(int retention) {
        this.retention = retention;
    }

    public String getOnlineDate() {
        return onlineDate;
    }

    public void setOnlineDate(String onlineDate) {
        this.onlineDate = onlineDate;
    }

    public String getEtlTriggerTime() {
        return etlTriggerTime;
    }

    public void setEtlTriggerTime(String etlTriggerTime) {
        this.etlTriggerTime = etlTriggerTime;
    }

    public String getEtlTriggerId() {
        return etlTriggerId;
    }

    public void setEtlTriggerId(String etlTriggerId) {
        this.etlTriggerId = etlTriggerId;
    }

    public String getEtlStatus() {
        return etlStatus;
    }

    public void setEtlStatus(String etlStatus) {
        this.etlStatus = etlStatus;
    }

    public int getEtlOrder() {
        return etlOrder;
    }

    public void setEtlOrder(int etlOrder) {
        this.etlOrder = etlOrder;
    }
}
