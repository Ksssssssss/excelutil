package com.hoolai.bi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hoolai.bi.entiy.income.InstallIncomeRate;
import com.hoolai.bi.entiy.retention.ShareCreativeRetention;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-10-14 17:02
 * 
 */
 
public interface InstallIncomeMapper {
    List<InstallIncomeRate> queryInstallIncomes(@Param("gameId") int gameid, @Param("startDs") String startDs, @Param("endDs") String endDs);
}
