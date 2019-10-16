package com.hoolai.bi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hoolai.bi.entiy.daily.DailyAllStats;
import com.hoolai.bi.entiy.daily.DailyCreativeStats;
import com.hoolai.bi.entiy.daily.DailyStats;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-09-29 11:23
 */

@Mapper
public interface DailyAllStatsMapper extends BaseMapper<DailyAllStats> {
    List<DailyAllStats> queryAllList(@Param("gameId") int gameid, @Param("startDs") String startDs, @Param("endDs") String endDs);
}