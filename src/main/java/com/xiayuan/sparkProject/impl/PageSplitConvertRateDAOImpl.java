package com.xiayuan.sparkProject.impl;

import com.xiayuan.sparkProject.dao.IPageSplitConvertRateDAO;
import com.xiayuan.sparkProject.domain.PageSplitConvertRate;
import com.xiayuan.sparkProject.jdbc.JDBCHelper;

/**
 * @author yeunsher
 * @date 2020-04-04 - 19:42
 */
public class PageSplitConvertRateDAOImpl implements IPageSplitConvertRateDAO {
    @Override
    public void insert(PageSplitConvertRate pageSplitConvertRate) {
        String sql = "insert into page_split_convert_rate values(?,?)";
        Object[] params = new Object[]{
                pageSplitConvertRate.getTaskid(),
                pageSplitConvertRate.getConvertRate()
        };

        JDBCHelper jdbcHelper = JDBCHelper.getInstance();
        jdbcHelper.executeUpdate(sql, params);
    }
}
