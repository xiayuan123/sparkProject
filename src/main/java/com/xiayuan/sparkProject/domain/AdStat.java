package com.xiayuan.sparkProject.domain;

/**
 * @author yeunsher
 * @date 2020-04-13 - 11:18
 */
public class AdStat {
    private String date;
    private String province;
    private String city;
    private long adid;
    private long clickCount;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getAdid() {
        return adid;
    }

    public void setAdid(long adid) {
        this.adid = adid;
    }

    public long getClickCount() {
        return clickCount;
    }

    public void setClickCount(long clickCount) {
        this.clickCount = clickCount;
    }

    @Override
    public String toString() {
        return "AdStat{" +
                "date='" + date + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", adid=" + adid +
                ", clickCount=" + clickCount +
                '}';
    }
}
