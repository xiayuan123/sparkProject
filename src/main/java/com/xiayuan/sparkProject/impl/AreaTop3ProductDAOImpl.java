package com.xiayuan.sparkProject.impl;

import com.xiayuan.sparkProject.dao.IAreaTop3ProductDAO;
import com.xiayuan.sparkProject.domain.AreaTop3Product;
import com.xiayuan.sparkProject.jdbc.JDBCHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yeunsher
 * @date 2020-04-07 - 11:29
 */
public class AreaTop3ProductDAOImpl implements IAreaTop3ProductDAO {
    @Override
    public void insertBatch(List<AreaTop3Product> areaTop3Products) {
        String sql = "insert into area_top3_product values(?,?,?,?,?,?,?,?)";

        List<Object[]> paramList = new ArrayList<Object[]>();
        for (AreaTop3Product areaTop3Product : areaTop3Products) {
            Object[] param = new Object[8];

            param[0] = areaTop3Product.getTaskid();
            param[1] = areaTop3Product.getArea();
            param[2] = areaTop3Product.getAreaLevel();
            param[3] = areaTop3Product.getProductid();
            param[4] = areaTop3Product.getCityInfos();
            param[5] = areaTop3Product.getClickCount();
            param[6] = areaTop3Product.getProductName();
            param[7] = areaTop3Product.getProductStatus();

            paramList.add(param);
        }

        JDBCHelper jdbcHelper = JDBCHelper.getInstance();
        jdbcHelper.executeBatch(sql, paramList);
    }
}
