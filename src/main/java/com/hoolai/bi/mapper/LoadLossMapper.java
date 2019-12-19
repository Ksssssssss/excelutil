package com.hoolai.bi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hoolai.bi.entiy.loadloss.LoadLoss;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-10-14 17:02
 * 
 */
 
public interface LoadLossMapper extends BaseMapper<LoadLoss> {
    List<LoadLoss> query(@Param("gameId") int gameid, @Param("startDs") String startDs);
}
