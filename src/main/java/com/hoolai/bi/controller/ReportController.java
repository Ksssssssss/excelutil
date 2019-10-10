package com.hoolai.bi.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.hoolai.bi.entiy.ReportType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-09-29 14:48
 */
@RestController
public class ReportController {

    @RequestMapping("/api/report/generateReport")
    public void generateReport(HttpServletResponse response, String startDs, String endDs, int gameId) throws IOException {
        response.setContentType("application/vnd.ms-service");
        response.setCharacterEncoding("utf-8");

        String fileName = URLEncoder.encode("日报" + endDs, "UTF-8");
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

        int i = 0;
        for (ReportType sheetName : ReportType.values()) {
            sheetName.write(startDs,endDs,gameId,excelWriter,i);
            i++;
        }
        excelWriter.finish();
    }
}
