package com.hoolai.bi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hoolai.bi.entiy.income.InstallIncomeRate;
import com.hoolai.bi.entiy.rank.RankDistribution;
import com.hoolai.bi.entiy.retention.ShareRetention;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-10-14 17:02
 * 
 */
 
public interface RankDistributionsMapper extends BaseMapper<RankDistribution> {
    List<RankDistribution> queryDauRanks(@Param("gameId") int gameid, @Param("startDs") String startDs, @Param("endDs") String endDs);
    List<RankDistribution> queryInstallRanks(@Param("gameId") int gameid, @Param("startDs") String startDs, @Param("endDs") String endDs);
}
