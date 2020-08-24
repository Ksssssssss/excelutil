package com.hoolai.bi.mapper;

import com.hoolai.bi.entiy.retention.RetentionOfAdId;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-10-10 19:58
 * 
 */

public interface RetentionAdIdMapper {
    List<RetentionOfAdId> queryRetentionAdIds(@Param("gameId") int gameid, @Param("startDs") String startDs, @Param("endDs") String endDs);
}
