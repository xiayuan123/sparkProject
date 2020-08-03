package com.xiayuan.sparkProject.impl;

import com.xiayuan.sparkProject.dao.ISessionRandomExtractDAO;
import com.xiayuan.sparkProject.domain.SessionRandomExtract;
import com.xiayuan.sparkProject.jdbc.JDBCHelper;

/**
 * @author yeunsher
 * @date 2020-03-31 - 09:46
 */
public class SessionRandomExtractDAOImpl implements ISessionRandomExtractDAO {
    @Override
    public void insert(SessionRandomExtract sessionRandomExtract) {
        String sql = "insert into session_random_extract values(?,?,?,?,?)";
        Object[] params = new Object[]{
                sessionRandomExtract.getTaskid(),
                sessionRandomExtract.getSessionid(),
                sessionRandomExtract.getStartTime(),
                sessionRandomExtract.getSearchKeyWords(),
                sessionRandomExtract.getClickCategoryIds()
        };
        JDBCHelper jdbcHelper = JDBCHelper.getInstance();
        jdbcHelper.executeUpdate(sql, params);
    }
}
