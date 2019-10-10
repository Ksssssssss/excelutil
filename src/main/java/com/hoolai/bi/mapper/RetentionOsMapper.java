package com.hoolai.bi.mapper;

import com.hoolai.bi.entiy.retention.ShareOsRetention;
import com.hoolai.bi.entiy.retention.ShareRetention;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-10-10 19:58
 * 
 */

public interface RetentionOsMapper {
    List<ShareOsRetention> queryRetentionOs(@Param("gameId") int gameid, @Param("startDs") String startDs, @Param("endDs") String endDs);
}
