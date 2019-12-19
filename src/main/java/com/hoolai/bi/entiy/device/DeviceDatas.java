package com.hoolai.bi.entiy.device;

import com.hoolai.bi.entiy.ReportType;
import com.hoolai.bi.entiy.daily.DailyStats;
import com.hoolai.bi.excel.ExcelDatas;

import java.util.List;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-11-28 17:49
 * 
 */
 
public class DeviceDatas implements ExcelDatas {

    private ReportType type;
    private List<DeviceDistribution> deviceDistributions;

    public DeviceDatas( List<DeviceDistribution> deviceDistributions,ReportType type) {
        this.type = type;
        this.deviceDistributions = deviceDistributions;
    }

    public ReportType getType() {
        return type;
    }

    public void setType(ReportType type) {
        this.type = type;
    }

    public List<DeviceDistribution> getDeviceDistributions() {
        return deviceDistributions;
    }

    public void setDeviceDistributions(List<DeviceDistribution> deviceDistributions) {
        this.deviceDistributions = deviceDistributions;
    }
}
