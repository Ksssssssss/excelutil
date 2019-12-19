package com.hoolai.bi.excel.info;

import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-10-15 19:41
 */
public class ExcelStyleStrategy {
    private WriteCellStyle headStyle = new WriteCellStyle();
    private WriteCellStyle contentStyle = new WriteCellStyle();

    private HorizontalCellStyleStrategy horizontalCellStyleStrategy;

    public HorizontalCellStyleStrategy customCellStyle() {
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setBold(false);
        headStyle.setWriteFont(headWriteFont);
        cellStyle(headStyle);

        WriteFont contentFont = new WriteFont();
        contentFont.setFontHeightInPoints((short) 12);
        contentStyle.setWriteFont(contentFont);
        cellStyle(contentStyle);
        return new HorizontalCellStyleStrategy(headStyle, contentStyle);
    }

    private void cellStyle(WriteCellStyle cellStyle) {
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

    }
}
