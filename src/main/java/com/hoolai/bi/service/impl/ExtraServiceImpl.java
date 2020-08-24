package com.hoolai.bi.service.impl;

import com.hoolai.bi.entiy.Datas;
import com.hoolai.bi.entiy.ReportType;
import com.hoolai.bi.entiy.daily.DailyAllStats;
import com.hoolai.bi.entiy.daily.DailyStats;
import com.hoolai.bi.entiy.extra.Extra;
import com.hoolai.bi.excel.ExcelDatas;
import com.hoolai.bi.mapper.DailyAllStatsMapper;
import com.hoolai.bi.mapper.ExtraMapper;
import com.hoolai.bi.service.ReportService;
import com.hoolai.bi.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2020-07-30 19:10
 */

@Service
public class ExtraServiceImpl implements ReportService {
    @Autowired
    private ExtraMapper extraMapper;

    @Autowired
    private DailyAllStatsMapper dailyAllStatsMapper;

    public ExcelDatas produceDatas(String startDs, String endDs, int gameId, int snid) {
        List<Extra> extras = extraMapper.queryExtra(gameId, startDs, endDs);
        List<DailyAllStats> dailyAllStats = dailyAllStatsMapper.queryAllList(gameId, startDs, endDs);
        Map<String, DailyAllStats> collect = dailyAllStats.stream().collect(Collectors.toMap(m -> m.getDs(), m -> m));
        if (!CollectionUtils.isEmpty(extras)) {
            for (String ds = startDs; DateUtil.dateCompare(endDs, ds) >= 0; ds = DateUtil.dateCalculate(ds, 1)) {
                for (Extra extra : extras) {
                    if (extra.getDs().equals(ds)) {
                        DailyAllStats dailyAll = collect.get(ds);
                        extra.setDauNum(dailyAll.getDauNum());
                        extra.setInstallNum(dailyAll.getInstallNum());
                    }
                }
            }
        }
        extras = extras.stream().sorted(Comparator.comparing(Extra::getDs).reversed()).collect(Collectors.toList());

        return new Datas(ReportType.EXTRA, extras);
    }
}
