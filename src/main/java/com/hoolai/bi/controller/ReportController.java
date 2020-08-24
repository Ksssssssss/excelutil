package com.hoolai.bi.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.hoolai.bi.context.GameContext;
import com.hoolai.bi.context.ReportEnvConfig;
import com.hoolai.bi.entiy.Game;
import com.hoolai.bi.entiy.QueryInfo;
import com.hoolai.bi.entiy.ad.AdTrackingBehavior;
import com.hoolai.bi.entiy.daily.DailyWriterBehavior;
import com.hoolai.bi.entiy.device.DeviceWriterBehavior;
import com.hoolai.bi.entiy.duration.OnlineWriterBehavior;
import com.hoolai.bi.entiy.extra.ExtraWriterBehavior;
import com.hoolai.bi.entiy.income.InstallIncomeBehavior;
import com.hoolai.bi.entiy.ip2region.CityBehavior;
import com.hoolai.bi.entiy.ip2region.ProvinceBehavior;
import com.hoolai.bi.entiy.loadloss.LoadLossBehavior;
import com.hoolai.bi.entiy.loadloss.LoadLossTimeDistributionBehavior;
import com.hoolai.bi.entiy.rank.RankWriterBehavior;
import com.hoolai.bi.entiy.retention.RetentionWriterBehavior;
import com.hoolai.bi.entiy.time.TimeDistributionBehavior;
import com.hoolai.bi.excel.Writer;
import com.hoolai.bi.service.ReportService;
import com.hoolai.bi.util.DateUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-09-29 14:48
 */

@Slf4j
@RestController
public class ReportController {

    @Autowired
    private ReportEnvConfig config;
    @Autowired
    private GameContext gameContext;
    @Autowired
    private ReportService dailyAllStatsServiceImpl;
    @Autowired
    private ReportService dailyOsStatsServiceImpl;
    @Autowired
    private ReportService dailyCreativeStatsServiceImpl;
    @Autowired
    private ReportService retentionServiceImpl;
    @Autowired
    private ReportService retentionAdIdServiceImpl;
    @Autowired
    private ReportService adTrackingServiceImpl;
    @Autowired
    private ReportService retentionOsServiceImpl;
    @Autowired
    private ReportService retentionCreativeServiceImpl;
    @Autowired
    private ReportService installIncomeServiceImpl;
    @Autowired
    private ReportService rankDistributionServiceImpl;
    @Autowired
    private ReportService installRankDistributionServiceImpl;
    @Autowired
    private ReportService deviceDistributionServiceImpl;
    @Autowired
    private ReportService onlineDurationServiceImpl;
    @Autowired
    private ReportService loadLossServiceImpl;
    @Autowired
    private ReportService cityServiceImpl;
    @Autowired
    private ReportService provinceServiceImpl;
    @Autowired
    private ReportService hourDauServiceImpl;
    @Autowired
    private ReportService hourInstallServiceImpl;
    @Autowired
    private ReportService dailyAdStatsServiceImpl;
    @Autowired
    private ReportService loadLossTimeDistributionServiceImpl;
    @Autowired
    private ReportService extraServiceImpl;

    @SneakyThrows
    @RequestMapping("/api/report/generateReport")
    public void generateReport(HttpServletResponse response, String startDs, String endDs, int gameId, int snid) {
        response.setContentType("application/vnd.ms-service");
        response.setCharacterEncoding("utf-8");
        Game game = gameContext.get(gameId);
        String gameName = game.getName();
        String fileName = URLEncoder.encode(gameName + "日报" + endDs, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        repeatedWrite(response, startDs, endDs, gameId, snid);
    }


    /**
     * @param response
     * @param startDs
     * @param endDs
     * @param gameId
     * @throws IOException
     * @description 动态生成sheet
     */
    public void repeatedWrite(HttpServletResponse response, String startDs, String endDs, int gameId, int snid) throws IOException {
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();
        if (DateUtil.dateCompare(endDs, startDs) < 0) {
            return;
        }
        QueryInfo info = new QueryInfo(startDs, endDs, gameId, snid);
        List<Writer> writers = writers();
        for (int i = 0; i < writers.size(); i++) {
            Writer writer = writers.get(i);
            writer.write(i, excelWriter, info);
        }
        excelWriter.finish();
    }

    private List<Writer> writers() {
        Writer dailyWriter = new Writer(new DailyWriterBehavior(config), dailyAllStatsServiceImpl);
        Writer dailyOsWriter = new Writer(new DailyWriterBehavior(config), dailyOsStatsServiceImpl);
        Writer dailyCreativeWriter = new Writer(new DailyWriterBehavior(config), dailyCreativeStatsServiceImpl);
        Writer dailyAdWriter = new Writer(new DailyWriterBehavior(config), dailyAdStatsServiceImpl);
        Writer retentionWriter = new Writer(new RetentionWriterBehavior(config), retentionServiceImpl);
        Writer retentionAdWriter = new Writer(new RetentionWriterBehavior(config), retentionAdIdServiceImpl);
        Writer adTrackingWriter = new Writer(new AdTrackingBehavior(), adTrackingServiceImpl);
        Writer retentionOsWriter = new Writer(new RetentionWriterBehavior(config), retentionOsServiceImpl);
        Writer retentionCreativeWriter = new Writer(new RetentionWriterBehavior(config), retentionCreativeServiceImpl);
        Writer installIncomeWriter = new Writer(new InstallIncomeBehavior(config), installIncomeServiceImpl);
        Writer rankWriter = new Writer(new RankWriterBehavior(config), rankDistributionServiceImpl);
        Writer installRankWriter = new Writer(new RankWriterBehavior(config), installRankDistributionServiceImpl);
        Writer deviceWriter = new Writer(new DeviceWriterBehavior(), deviceDistributionServiceImpl);
        Writer onlineDurationWriter = new Writer(new OnlineWriterBehavior(), onlineDurationServiceImpl);
        Writer loadLossWriter = new Writer(new LoadLossBehavior(), loadLossServiceImpl);
        Writer loadLossTimeDistributionWriter = new Writer(new LoadLossTimeDistributionBehavior(), loadLossTimeDistributionServiceImpl);
        Writer cityWriter = new Writer(new CityBehavior(), cityServiceImpl);
        Writer provinceWriter = new Writer(new ProvinceBehavior(), provinceServiceImpl);
        Writer hourDauWriter = new Writer(new TimeDistributionBehavior(), hourDauServiceImpl);
        Writer hourInstallWriter = new Writer(new TimeDistributionBehavior(), hourInstallServiceImpl);
        Writer extraWriter = new Writer(new ExtraWriterBehavior(), extraServiceImpl);

        List<Writer> writers = Arrays.asList(dailyWriter, retentionWriter, extraWriter, adTrackingWriter, retentionAdWriter, dailyOsWriter, dailyCreativeWriter, retentionOsWriter,
                dailyAdWriter, retentionCreativeWriter, installIncomeWriter, rankWriter, installRankWriter,
                deviceWriter, onlineDurationWriter, loadLossWriter, loadLossTimeDistributionWriter, hourDauWriter, hourInstallWriter
        );
        return writers;
    }
}
