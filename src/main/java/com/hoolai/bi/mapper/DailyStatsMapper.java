package com.hoolai.bi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hoolai.bi.entiy.daily.DailyStats;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-09-29 11:23
 */

@Mapper
public interface DailyStatsMapper extends BaseMapper<DailyStats> {
}