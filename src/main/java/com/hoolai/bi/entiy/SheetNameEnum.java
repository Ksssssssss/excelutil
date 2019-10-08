package com.hoolai.bi.entiy;
/**
 *
 *@description: 表名
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-09-29 15:41
 * 
 */
 
public enum SheetNameEnum {
    ALL("总览"),
    OS("操作系统"),
//    SOURCE("来源"),
    RETWNTION("留存"),
//    RETENTION_OS("留存(操作系统)")
    ;

    private String name;
    private SheetNameEnum(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }}
