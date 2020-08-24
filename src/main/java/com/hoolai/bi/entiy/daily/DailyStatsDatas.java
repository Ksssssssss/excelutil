package com.hoolai.bi.entiy.daily;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.baomidou.mybatisplus.annotation.TableField;
import com.hoolai.bi.context.GameContext;
import com.hoolai.bi.context.ReportEnvConfig;
import com.hoolai.bi.excel.ExcelDatas;
import com.hoolai.bi.entiy.ReportType;
import com.hoolai.bi.util.SpringUtils;
import lombok.Data;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-10-16 10:48
 */
@Data
public class DailyStatsDatas implements ExcelDatas {
    private ReportType type;
    private List<DailyStats> dailyStatsList;

    @TableField(exist = false)
    protected GameContext gameContext = SpringUtils.getBean(GameContext.class);

    public DailyStatsDatas(List<DailyStats> dailyStatsList, ReportType type) {
        this.type = type;
        this.dailyStatsList = dailyStatsList;
    }

    public List<DailyStats> initAndGetDailyStatsDatas() {
        dailyStatsList = dailyStatsList.stream().sorted(Comparator.comparing(DailyStats::getDs).reversed()).collect(Collectors.toList());
        dailyStatsList.forEach(dailyStats -> dailyStats.init(gameContext));
        return dailyStatsList;
    }

}
