package com.hoolai.bi.util;

import javax.xml.crypto.Data;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @description: 日期操作
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-09-30 14:22
 */

public class DateUtil {
    private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private static final int INCREMENT = 1;
    private static final int DECREASE = -1;

    public static String currentDate(){
        return df.format(new Date());
    }

    public static String dateCalculate(String date,int day) {
        Date ds;
        Calendar calendar = Calendar.getInstance();
        try {
            ds = df.parse(date);
            calendar.setTime(ds);
            calendar.add(Calendar.DAY_OF_MONTH,day);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return df.format(calendar.getTime());
    }


    public static int dateCompare(String endDate, String startDate) {
        Date startDs;
        Date endDs;
        long res = -1;
        try {
            startDs = df.parse(startDate);
            endDs = df.parse(endDate);
            res = (endDs.getTime() - startDs.getTime()) / (1000 * 3600 * 24);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (int) res;
    }
}
