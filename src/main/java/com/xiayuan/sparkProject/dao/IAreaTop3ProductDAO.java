package com.xiayuan.sparkProject.dao;

import com.xiayuan.sparkProject.domain.AreaTop3Product;

import java.util.List;

/**
 * @author yeunsher
 * @date 2020-04-07 - 11:27
 */
public interface IAreaTop3ProductDAO {

    /**
     * 各区域top3热门商品插入mysql DAO
     * @param areaTop3Products
     */
    void insertBatch(List<AreaTop3Product> areaTop3Products);
}
