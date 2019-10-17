package com.hoolai.bi.entiy;

/**
 * @description: 表名
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-09-29 15:41
 */

public enum ReportType {
    ALL("总览"),
    RETWNTION("留存"),
    RETWNTION_OS("留存(按操作系统)"),
    OS("操作系统"),
    CREATIVE("渠道"),
    RETWNTION_CREATIVE("留存(按渠道)"),
    INSTALL_INCOME_RATE("注收比");

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
