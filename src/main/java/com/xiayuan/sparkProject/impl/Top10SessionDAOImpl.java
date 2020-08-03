package com.xiayuan.sparkProject.impl;

import com.xiayuan.sparkProject.dao.ITop10SessionDAO;
import com.xiayuan.sparkProject.domain.Top10Session;
import com.xiayuan.sparkProject.jdbc.JDBCHelper;

/**
 * @author yeunsher
 * @date 2020-04-02 - 10:39
 */
public class Top10SessionDAOImpl implements ITop10SessionDAO {


    @Override
    public void insert(Top10Session top10Session) {
        String sql = "insert into top10_session values(?,?,?,?)";
        Object[] params = new Object[]{
                top10Session.getTaskid(),
                top10Session.getCategoryid(),
                top10Session.getSessionid(),
                top10Session.getClickCount()
        };

        JDBCHelper jdbcHelper = JDBCHelper.getInstance();
        jdbcHelper.executeUpdate(sql, params);

    }
}
