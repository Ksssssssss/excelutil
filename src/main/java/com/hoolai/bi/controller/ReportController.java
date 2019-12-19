package com.hoolai.bi.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.hoolai.bi.context.GameContext;
import com.hoolai.bi.context.ReportEnvConfig;
import com.hoolai.bi.entiy.QueryInfo;
import com.hoolai.bi.entiy.ad.AdTrackingBehavior;
import com.hoolai.bi.entiy.loadloss.LoadLossBehavior;
import com.hoolai.bi.excel.behavior.*;
import com.hoolai.bi.excel.Writer;
import com.hoolai.bi.service.ReportService;
import com.hoolai.bi.service.impl.*;
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

    @RequestMapping("/api/report/generateReport")
    public void generateReport(HttpServletResponse response, String startDs, String endDs, int gameId) throws IOException {
        response.setContentType("application/vnd.ms-service");
        response.setCharacterEncoding("utf-8");
        String gameName = gameContext.get(gameId).getName();
        String fileName = URLEncoder.encode(gameName + "日报" + endDs, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        repeatedWrite(response, startDs, endDs, gameId);
    }

    /**
     * @param response
     * @param startDs
     * @param endDs
     * @param gameId
     * @throws IOException
     * @description 动态生成sheet
     */
    public void repeatedWrite(HttpServletResponse response, String startDs, String endDs, int gameId) throws IOException {
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();
        QueryInfo info = new QueryInfo(startDs, endDs, gameId);
        List<Writer> writers = writers();
        for (int i = 0; i < writers.size(); i++) {
            writers.get(i).write(i, excelWriter, info);
        }
        excelWriter.finish();
    }

    private List<Writer> writers() {
        Writer dailyWriter = new Writer(new DailyWriterBehavior(config), dailyAllStatsServiceImpl);
        Writer dailyOsWriter = new Writer(new DailyWriterBehavior(config), dailyOsStatsServiceImpl);
        Writer dailyCreativeWriter = new Writer(new DailyWriterBehavior(config), dailyCreativeStatsServiceImpl);
        Writer retentionWriter = new Writer(new RetentionWriterBehavior(config), retentionServiceImpl);
        Writer adTrackingWriter = new Writer(new AdTrackingBehavior(), adTrackingServiceImpl);
        Writer retentionOsWriter = new Writer(new RetentionWriterBehavior(config), retentionOsServiceImpl);
        Writer retentionCreativeWriter = new Writer(new RetentionWriterBehavior(config), retentionCreativeServiceImpl);
        Writer installIncomeWriter = new Writer(new InstallIncomeBehavior(config), installIncomeServiceImpl);
        Writer rankWriter = new Writer(new RankWriterBehavior(config), rankDistributionServiceImpl);
        Writer installRankWriter = new Writer(new RankWriterBehavior(config), installRankDistributionServiceImpl);
        Writer deviceWriter = new Writer(new DeviceWriterBehavior(), deviceDistributionServiceImpl);
        Writer onlineDurationWriter = new Writer(new OnlineWriterBehavior(), onlineDurationServiceImpl);
        Writer loadLossWriter = new Writer(new LoadLossBehavior(), loadLossServiceImpl);


        List<Writer> writers = Arrays.asList(dailyWriter, retentionWriter, adTrackingWriter, dailyOsWriter, dailyCreativeWriter, retentionOsWriter,
                retentionCreativeWriter, installIncomeWriter, rankWriter, installRankWriter,
                deviceWriter, onlineDurationWriter
        );
        return writers;
    }
}
