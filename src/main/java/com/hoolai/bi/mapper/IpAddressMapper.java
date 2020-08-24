package com.hoolai.bi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hoolai.bi.entiy.ip2region.CityAddress;
import com.hoolai.bi.entiy.ip2region.ProvinceAddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2020-01-14 15:52
 * 
 */
 
public interface IpAddressMapper {
    List<CityAddress> queryCity(@Param("gameId") int gameid, @Param("endDs") String endDs);
    List<ProvinceAddress> queryProvince(@Param("gameId") int gameid, @Param("endDs") String endDs);
}
