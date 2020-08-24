package com.hoolai.bi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hoolai.bi.entiy.time.TimeDistribution;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface TimeDistributionMapper extends BaseMapper<TimeDistribution> {
    List<TimeDistribution> queryHourDau(@Param("snid") int snid,@Param("gameId") int gameid, @Param("startDs") String startDs, @Param("endDs") String endDs);
    List<TimeDistribution> queryHourInstall(@Param("snid") int snid,@Param("gameId") int gameid, @Param("startDs") String startDs, @Param("endDs") String endDs);
}
