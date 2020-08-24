package com.hoolai.bi.mapper;

import com.hoolai.bi.entiy.daily.MonthInfo;
import com.hoolai.bi.entiy.daily.WeekInfo;
import com.hoolai.bi.entiy.extra.Extra;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2020-01-16 20:19
 * 
 */
 
public interface ExtraMapper {
    List<WeekInfo> queryWus(@Param("gameId") int gameid, @Param("startDs") String startDs, @Param("endDs") String endDs);
    List<MonthInfo> queryMus(@Param("gameId") int gameid, @Param("startDs") String startDs, @Param("endDs") String endDs);
    List<Extra> queryExtra(@Param("gameId") int gameid, @Param("startDs") String startDs, @Param("endDs") String endDs);
}
