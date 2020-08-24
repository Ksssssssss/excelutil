package com.hoolai.bi.entiy.retention;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.hoolai.bi.entiy.GameInfo;
import lombok.Data;

import java.util.List;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-08-05 14:49
 * Description:
 */

@Data
public class ShareRetention extends GameInfo {

    @TableId
    @ExcelProperty("第几日留存")
    private int dr;
    @ExcelProperty("留存数")
    private int retention;
    @ExcelProperty("安装数")
    private int installNum;
    @ExcelProperty("留存率")
    private float retentionPercentages = 0;

    public ShareRetention() {
    }

    private ShareRetention(int dr) {
        this.dr = dr;
    }

    public ShareRetention(String ds, int dr, int retention, String os, String creative, int clientid, String retentionPercentage, float retentionPercentages) {
        this.ds = ds;
        this.dr = dr;
        this.retention = retention;
        this.retentionPercentages = retentionPercentages;
    }

    @Override
    public String toString() {
        return "ShareRetention{" +
                "dr=" + dr +
                ", retentionwriter=" + retention +
                ", installNum=" + installNum +
                ", retentionPercentages=" + retentionPercentages +
                ", gameid=" + gameid +
                ", ds='" + ds + '\'' +
                ", snid=" + snid +
                '}';
    }
}
