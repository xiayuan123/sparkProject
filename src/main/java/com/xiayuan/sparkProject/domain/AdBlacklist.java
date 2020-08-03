package com.xiayuan.sparkProject.domain;

/**
 * @author yeunsher
 * @date 2020-04-10 - 18:02
 */
public class AdBlacklist {
    private long userid;

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "AdBlacklist{" +
                "userid=" + userid +
                '}';
    }
}
