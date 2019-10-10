package com.hoolai.bi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hoolai.bi.entiy.daily.DailyCreativeStats;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-09-29 19:29
 * 
 */
 @Mapper
public interface DailyCreativeStatsMapper extends BaseMapper<DailyCreativeStats> {
 List<DailyCreativeStats> queryCreativeList(@Param("gameId") int gameid, @Param("startDs") String startDs, @Param("endDs") String endDs);
}
