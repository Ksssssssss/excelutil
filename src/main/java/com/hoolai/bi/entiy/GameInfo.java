package com.hoolai.bi.entiy;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.hoolai.bi.context.GameContext;
import com.hoolai.bi.util.SpringUtils;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-09-30 11:49
 */

public class GameInfo {
    @TableId
    @ExcelIgnore
    protected int gameid;
    @TableId
    @ExcelProperty(value = "日期", index = 0)
    @ColumnWidth(20)
    protected String ds;
    @TableId
    @ExcelIgnore
    protected int snid;

    @ExcelIgnore
    @TableField(exist = false)
    protected GameContext gameContext;

    public GameInfo() {
        gameContext = SpringUtils.getBean(GameContext.class);
    }

    protected float checkDivide(float divisor, int dividend) {
        float result = divisor / dividend;
        if (Float.isNaN(result) || Float.isInfinite(result)) {
            return 0.0f;
        }
        return result;
    }

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

    public GameContext getGameContext() {
        return gameContext;
    }

    public void setGameContext(GameContext gameContext) {
        this.gameContext = gameContext;
    }
}
