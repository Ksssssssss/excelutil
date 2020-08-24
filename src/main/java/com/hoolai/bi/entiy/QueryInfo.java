package com.hoolai.bi.entiy;
/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2019-10-16 20:32
 * 
 */
 
public class QueryInfo {
    private int gameid;
    private String startDs;
    private String endDs;
    private int snid;

    public QueryInfo(String startDs, String endDs, int gameid,int snid) {
        this.gameid = gameid;
        this.startDs = startDs;
        this.endDs = endDs;
        this.snid = snid;
    }

    public int getGameid() {
        return gameid;
    }

    public void setGameid(int gameid) {
        this.gameid = gameid;
    }

    public String getStartDs() {
        return startDs;
    }

    public void setStartDs(String startDs) {
        this.startDs = startDs;
    }

    public String getEndDs() {
        return endDs;
    }

    public void setEndDs(String endDs) {
        this.endDs = endDs;
    }

    public int getSnid() {
        return snid;
    }

    public void setSnid(int snid) {
        this.snid = snid;
    }
}
