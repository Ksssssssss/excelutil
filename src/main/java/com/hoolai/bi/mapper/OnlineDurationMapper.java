package com.hoolai.bi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hoolai.bi.entiy.device.DeviceDistribution;
import com.hoolai.bi.entiy.duration.OnlineDuration;
import com.hoolai.bi.entiy.rank.RankDistribution;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-11-28 19:53
 */

public interface OnlineDurationMapper extends BaseMapper<OnlineDuration> {
    List<OnlineDuration> query(@Param("gameId") int gameid, @Param("startDs") String startDs, @Param("endDs") String endDs);
}
