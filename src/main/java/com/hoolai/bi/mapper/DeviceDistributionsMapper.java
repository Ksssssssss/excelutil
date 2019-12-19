package com.hoolai.bi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hoolai.bi.entiy.daily.DailyAllStats;
import com.hoolai.bi.entiy.device.DeviceDistribution;
import com.hoolai.bi.entiy.rank.RankDistribution;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-10-14 17:02
 * 
 */
 
public interface DeviceDistributionsMapper extends BaseMapper<DeviceDistributionsMapper> {
    List<DeviceDistribution> queryInstallDevice(@Param("gameId") int gameid, @Param("endDs") String endDs);
    List<DeviceDistribution> queryDauDevice(@Param("gameId") int gameid, @Param("startDs") String startDs, @Param("endDs") String endDs);
}
