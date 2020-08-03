package com.xiayuan.sparkProject.dao;

import com.xiayuan.sparkProject.domain.Top10Category;

/**
 * @author yeunsher
 * @date 2020-04-01 - 13:25
 */
public interface ITop10CategoryDAO {
    /**
     * 插入点击、下单、支付前十的品类
     * @param top10Category
     */
    void insert(Top10Category top10Category);
}
