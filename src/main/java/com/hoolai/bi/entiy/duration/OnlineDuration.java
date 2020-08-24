package com.hoolai.bi.entiy.duration;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.hoolai.bi.entiy.GameInfo;
import lombok.Data;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-11-28 19:47
 */

@Data
public class OnlineDuration extends GameInfo{

    @ExcelProperty(value = "在线时长")
    private String duration;
    @ExcelProperty(value = "安装人数")
    private int numbers;
    @ExcelProperty(value = "次日人数")
    private int retentionNumbers;

    @NumberFormat("0.00%")
    @ExcelProperty(value = "次留")
    private float retentionRatio;

    private static Pattern pattern = Pattern.compile("\\d+");

    public static Comparator<String> comparator = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            Matcher matcher = pattern.matcher(o1);
            Matcher matcherTo = pattern.matcher(o2);
            int num1 = 0;
            int num2 = 0;
            while (matcher.find() && matcherTo.find()) {
                num1 = Integer.parseInt(matcher.group(0));
                num2 = Integer.parseInt(matcherTo.group(0));
                break;
            }
            return num1 - num2;
        }
    };
}

