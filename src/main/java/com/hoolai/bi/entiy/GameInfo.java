package com.hoolai.bi.entiy;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-09-30 11:49
 * 
 */
 
public class GameInfo {
    @TableId
    @ExcelProperty(value = "游戏编号")
    protected int gameid;
    @TableId
    @ExcelProperty(value = "日期",index = 0)
    protected String ds;
    @TableId
    @ExcelProperty(value = "平台编号")
    protected int snid;

    public int getGameid() {
        return gameid;
    }

    public void setGameid(int gameid) {
        this.gameid = gameid;
    }

    public String getDs() {
        return ds;
    }

    public void setDs(String ds) {
        this.ds = ds;
    }

    public int getSnid() {
        return snid;
    }

    public void setSnid(int snid) {
        this.snid = snid;
    }
}
