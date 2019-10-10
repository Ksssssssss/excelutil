package com.hoolai.bi.entiy.retention;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-10-10 15:11
 */

public enum RetentionType {
    RETENTION(1, Arrays.asList("留存","安装数")),
    RETENTION_OS(2,Arrays.asList("留存","安装数","操作系统"));

    private int needRowLength;
    private List<String> needExcelHead;

    RetentionType(int needRowLength, List<String> needExcelHead) {
        this.needRowLength = needRowLength;
        this.needExcelHead = needExcelHead;
    }

    public int getNeedRowLength() {
        return needRowLength;
    }

    public void setNeedRowLength(int needRowLength) {
        this.needRowLength = needRowLength;
    }

    public List<String> getNeedExcelHead() {
        return needExcelHead;
    }

    public void setNeedExcelHead(List<String> needExcelHead) {
        this.needExcelHead = needExcelHead;
    }}


