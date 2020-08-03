package com.xiayuan.sparkProject.impl;

import com.xiayuan.sparkProject.dao.ITop10CategoryDAO;
import com.xiayuan.sparkProject.domain.Top10Category;
import com.xiayuan.sparkProject.jdbc.JDBCHelper;

/**
 * @author yeunsher
 * @date 2020-04-01 - 13:26
 */
public class Top10CategoryDAOImpl implements ITop10CategoryDAO {

    @Override
    public void insert(Top10Category top10Category) {
        String sql = "insert into top10_category values(?,?,?,?,?)";
        Object[] params = new Object[] {
                top10Category.getTaskid(),
                top10Category.getCategoryid(),
                top10Category.getClickCount(),
                top10Category.getOrderCount(),
                top10Category.getPayCount()
        };

        JDBCHelper jdbcHelper = JDBCHelper.getInstance();
        jdbcHelper.executeUpdate(sql, params);

    }
}
