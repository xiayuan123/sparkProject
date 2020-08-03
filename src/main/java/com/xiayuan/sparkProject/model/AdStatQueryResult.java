package com.xiayuan.sparkProject.model;

/**
 * 广告实时点击查询结果
 * @author yeunsher
 * @date 2020-04-13 - 11:22
 */
public class AdStatQueryResult {
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "AdStatQueryResult{" +
                "count=" + count +
                '}';
    }
}
