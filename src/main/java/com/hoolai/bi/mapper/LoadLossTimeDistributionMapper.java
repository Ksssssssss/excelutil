package com.hoolai.bi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hoolai.bi.entiy.loadloss.LoadLoss;
import com.hoolai.bi.entiy.loadloss.LoadLossTimeDistribution;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-10-14 17:02
 * 
 */
 
public interface LoadLossTimeDistributionMapper extends BaseMapper<LoadLossTimeDistribution> {
    List<LoadLossTimeDistribution> query(@Param("gameId") int gameid, @Param("endDs") String endDs);
}
