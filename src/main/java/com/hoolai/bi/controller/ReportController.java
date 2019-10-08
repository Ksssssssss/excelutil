package com.hoolai.bi.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hoolai.bi.entiy.daily.DailyOsStats;
import com.hoolai.bi.entiy.daily.DailyStats;
import com.hoolai.bi.excel.GenerateExcelService;
import com.hoolai.bi.mapper.DailyOsStatsMapper;
import com.hoolai.bi.mapper.DailyStatsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-09-29 14:48
 */
@RestController
public class ReportController {
    @Autowired
    private GenerateExcelService excelService;

    @RequestMapping("/api/report/generateReport")
    public void generateReport(HttpServletResponse response, String startDs, String endDs, int gameId) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");

        String fileName = URLEncoder.encode("日报", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        excelService.repeatedWrite(response, startDs, endDs, gameId);
    }
}
