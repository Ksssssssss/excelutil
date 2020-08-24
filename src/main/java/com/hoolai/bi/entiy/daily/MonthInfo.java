package com.hoolai.bi.entiy.daily;

import com.hoolai.bi.context.GameContext;
import com.hoolai.bi.entiy.GameInfo;
import lombok.Data;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2020-01-16 20:17
 * 
 */

@Data
public class MonthInfo extends GameInfo {
    private int mdu;
    private int mnu;
    private int monthPayment;

}
