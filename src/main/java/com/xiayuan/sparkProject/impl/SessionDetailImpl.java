package com.xiayuan.sparkProject.impl;

import com.xiayuan.sparkProject.dao.ISessionDetailDAO;
import com.xiayuan.sparkProject.domain.SessionDetail;
import com.xiayuan.sparkProject.jdbc.JDBCHelper;

/**
 * @author yeunsher
 * @date 2020-03-31 - 10:56
 */
public class SessionDetailImpl implements ISessionDetailDAO {


    @Override
    public void insert(SessionDetail sessionDetail) {
        String sql = "insert into session_detail values(?,?,?,?,?,?,?,?,?,?,?,?)";

        Object[] params = new Object[]{
                sessionDetail.getTaskid(),
                sessionDetail.getUserid(),
                sessionDetail.getSessionid(),
                sessionDetail.getPageid(),
                sessionDetail.getActionTime(),
                sessionDetail.getSearchKeyWord(),
                sessionDetail.getClickCategoryId(),
                sessionDetail.getClickProductId(),
                sessionDetail.getOrderCategoryIds(),
                sessionDetail.getOrderProductIds(),
                sessionDetail.getPayCategoryIds(),
                sessionDetail.getPayProductIds()
        };
        JDBCHelper jdbcHelper = JDBCHelper.getInstance();
        jdbcHelper.executeUpdate(sql, params);
    }
}
