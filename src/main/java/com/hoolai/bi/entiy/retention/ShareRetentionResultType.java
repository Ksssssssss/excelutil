package com.hoolai.bi.entiy.retention;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.hoolai.bi.entiy.GameInfo;

import java.util.List;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-08-05 14:49
 * Description:
 */
public class ShareRetentionResultType extends GameInfo {

    @TableId
    @ExcelProperty("第几日留存")
    private int dr;
    @ExcelProperty("留存数")
    private int retention;
    @ExcelProperty("安装数")
    private int installNum;
    @ExcelProperty("留存率")
    private float retentionPercentages = 0;

    public ShareRetentionResultType() {
    }

    public ShareRetentionResultType(String ds, int dr, int retention, String os, String creative, int clientid, String retentionPercentage, float retentionPercentages) {
        this.ds = ds;
        this.dr = dr;
        this.retention = retention;

        this.retentionPercentages = retentionPercentages;
    }

    public static String comparingByDs(List<ShareRetentionResultType> retentionResultTypes){
        return retentionResultTypes.stream().findFirst().get().getDs();
    }

    public String getDs() {
        return ds;
    }

    public void setDs(String ds) {
        this.ds = ds;
    }

    public int getDr() {
        return dr;
    }

    public void setDr(int dr) {
        this.dr = dr;
    }

    public int getRetention() {
        return retention;
    }

    public void setRetention(int retention) {
        this.retention = retention;
    }

    public float getRetentionPercentages() {
        return retentionPercentages;
    }

    public void setRetentionPercentages(float retentionPercentages) {
        this.retentionPercentages = retentionPercentages;
    }

    public int getInstallNum() {
        return installNum;
    }

    public void setInstallNum(int installNum) {
        this.installNum = installNum;
    }

    @Override
    public String toString() {
        return "ShareRetentionResultType{" +
                "dr=" + dr +
                ", retention=" + retention +
                ", installNum=" + installNum +
                ", retentionPercentages=" + retentionPercentages +
                ", gameid=" + gameid +
                ", ds='" + ds + '\'' +
                ", snid=" + snid +
                '}';
    }
}
