package com.hoolai.bi.entiy;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.hoolai.bi.context.GameContext;
import com.hoolai.bi.util.SpringUtils;
import lombok.Data;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-09-30 11:49
 */

@Data
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

    protected float checkDivide(float divisor, int dividend) {
        float result = divisor / dividend;
        if (Float.isNaN(result) || Float.isInfinite(result)) {
            return 0.0f;
        }
        return result;
    }

}
