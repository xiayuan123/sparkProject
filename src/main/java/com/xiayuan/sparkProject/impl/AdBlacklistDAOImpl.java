package com.xiayuan.sparkProject.impl;

import com.xiayuan.sparkProject.dao.IAdBlacklistDAO;
import com.xiayuan.sparkProject.domain.AdBlacklist;
import com.xiayuan.sparkProject.jdbc.JDBCHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yeunsher
 * @date 2020-04-10 - 18:05
 */
public class AdBlacklistDAOImpl implements IAdBlacklistDAO {
    /**
     * 批量插入广告黑名单用户
     * @param adBlacklists
     */
    @Override
    public void insertBatch(List<AdBlacklist> adBlacklists) {
        String insertSQL = "insert into ad_blacklist values(?)";
        List<Object[]> paramList = new ArrayList<>();

        for (AdBlacklist adBlacklist : adBlacklists) {
            Object[] params = new Object[]{adBlacklist.getUserid()};
            paramList.add(params);
        }
        JDBCHelper jdbcHelper = JDBCHelper.getInstance();
        jdbcHelper.executeBatch(insertSQL, paramList);
    }

    /**
     * 查询所有广告黑名单用户
     * @return
     */
    @Override
    public List<AdBlacklist> findAll() {
        String selectSQL = "select * from ad_blacklist";

        final List<AdBlacklist> adBlacklists = new ArrayList<>();
        JDBCHelper jdbcHelper = JDBCHelper.getInstance();
        jdbcHelper.executeQuery(selectSQL, null, new JDBCHelper.QueryCallback() {
            @Override
            public void process(ResultSet rs) throws Exception {
                while (rs.next()) {
                    long userid = rs.getLong(1);
                    AdBlacklist adBlacklist = new AdBlacklist();
                    adBlacklist.setUserid(userid);

                    adBlacklists.add(adBlacklist);
                }
            }
        });

        return adBlacklists;
    }
}
