package com.hoolai.bi.context;

import com.google.common.collect.Maps;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-10-11 14:41
 */
@Component
@Data
@ConfigurationProperties(prefix = "report.env")
public class ReportEnvConfig {
    /**
     * 转换汇率日期
     */
    private String changeRateDs;
    /**
     * 转换比例
     */
    private int rate;
    /**
     * 最大的留存天数
     */
    private int maxRetentionDay;
    /**
     * 最大注收比
     */
    private int maxInstallIncomeDay;
    /**
     * 等级分布汇总条数
     */
    private int levelDistributionSummary;
    /**
     * 游戏信息 id_name
     */
    private List<String> gameInfos;

    public String gameName(int gameId) {
        Map<Integer, String> result = Maps.newHashMapWithExpectedSize(gameInfos.size());
        for (String gameInfo : gameInfos) {
            String[] split = gameInfo.split("_");
            if (split.length == 2){
                result.put(Integer.parseInt(split[0]),split[1]);
            }
        }
        return result.get(gameId);
    }

}
