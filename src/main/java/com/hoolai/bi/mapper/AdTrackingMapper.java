package com.hoolai.bi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hoolai.bi.entiy.ad.AdTracking;
import com.hoolai.bi.entiy.daily.DailyAllStats;
import com.hoolai.bi.entiy.device.DeviceDistribution;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-10-14 17:02
 * 
 */
 
public interface AdTrackingMapper extends BaseMapper<AdTracking> {
    List<AdTracking> query(@Param("gameId") int gameid, @Param("startDs") String startDs, @Param("endDs") String endDs);
}
