package com.hoolai.bi.mapper;

import com.hoolai.bi.entiy.retention.ShareRetentionResultType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-08-05 14:54
 * Description:
 */
public interface RetentionMapper {

	List<ShareRetentionResultType> queryRetentionList(int gameid, int data);

	List<ShareRetentionResultType> queryRetens(@Param("gameId") int gameid,@Param("startDs") String startDs, @Param("endDs") String endDs);
}
