package com.hoolai.bi.entiy;

/**
 * @description: 表名
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-09-29 15:41
 */

public enum ReportType {

    ALL("总览"),
    RETWNTION("留存"),
    AD("MP买量"),
    RETWNTION_OS("留存(按操作系统)"),
    RETWNTION_AD("留存(按广告)"),
    OS("操作系统"),
    CREATIVE("渠道"),
    RETWNTION_CREATIVE("留存(按渠道)"),
    INSTALL_INCOME_RATE("注收比"),
    DAU_RANK_DISTRIBUTION("等级分布(活跃)"),
    INSTALL_RANK_DISTRIBUTION("等级分布(安装)"),
    DAU_DEVICE_DISTRIBUTION("设备分布(活跃)"),
    INSTALL_DEVICE_DISTRIBUTION("设备分布(安装)"),
    ONLINE_DURATION_DISTRIBUTION("在线时长分布(安装)"),
    AD_TRACKING("流量主广告追踪"),
    LOAD_LOSS("加载流失"),
    LOAD_LOSS_TIME_DISTRIBUTION("新手加载流失时间分布"),
    PROVINCE("(活跃)省份分布"),
    CITY("(活跃)城市分布"),
    HOUR_DAU("(活跃)小时分布"),
    EXTRA("额外"),
    HOUR_INSTALL("(安装)小时分布");

    private String name;

    private ReportType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }}
