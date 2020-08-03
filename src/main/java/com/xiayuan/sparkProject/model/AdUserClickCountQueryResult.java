package com.xiayuan.sparkProject.model;

/**
 * 用户广告点击量查询结果
 *
 * @author yeunsher
 * @date 2020-04-10 - 16:29
 */
public class AdUserClickCountQueryResult {
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "AdUserClickCountQueryResult{" +
                "count=" + count +
                '}';
    }
}
