package com.hoolai.bi.entiy;

import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Collection;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2020-02-26 18:04
 * 
 */
 
public class GameCreative {

    @TableId
    private int id;

    private int gameid;
    private int snid;
    private String creative;
    private String creativeName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGameid() {
        return gameid;
    }

    public void setGameid(int gameid) {
        this.gameid = gameid;
    }

    public int getSnid() {
        return snid;
    }

    public void setSnid(int snid) {
        this.snid = snid;
    }

    public String getCreative() {
        return creative;
    }

    public void setCreative(String creative) {
        this.creative = creative;
    }

    public String getCreativeName() {
        return creativeName;
    }

    public void setCreativeName(String creativeName) {
        this.creativeName = creativeName;
    }
}
