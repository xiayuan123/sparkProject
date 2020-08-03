package com.xiayuan.sparkProject.domain;

/**
 * @author yeunsher
 * @date 2020-04-07 - 11:14
 */
public class AreaTop3Product {
    private long taskid;
    private String area;
    private String areaLevel;
    private long productid;
    private String cityInfos;
    private long clickCount;
    private String productName;
    private String productStatus;

    public long getTaskid() {
        return taskid;
    }

    public void setTaskid(long taskid) {
        this.taskid = taskid;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(String areaLevel) {
        this.areaLevel = areaLevel;
    }

    public long getProductid() {
        return productid;
    }

    public void setProductid(long productid) {
        this.productid = productid;
    }

    public String getCityInfos() {
        return cityInfos;
    }

    public void setCityInfos(String cityInfos) {
        this.cityInfos = cityInfos;
    }

    public long getClickCount() {
        return clickCount;
    }

    public void setClickCount(long clickCount) {
        this.clickCount = clickCount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    @Override
    public String toString() {
        return "AreaTop3Product{" +
                "taskid=" + taskid +
                ", area='" + area + '\'' +
                ", areaLevel='" + areaLevel + '\'' +
                ", productid=" + productid +
                ", cityInfos='" + cityInfos + '\'' +
                ", clickCount=" + clickCount +
                ", productName='" + productName + '\'' +
                ", productStatus='" + productStatus + '\'' +
                '}';
    }
}
