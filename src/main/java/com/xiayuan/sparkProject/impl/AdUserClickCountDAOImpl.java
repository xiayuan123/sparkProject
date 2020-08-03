package com.xiayuan.sparkProject.impl;

import com.xiayuan.sparkProject.dao.IAdUserClickCountDAO;
import com.xiayuan.sparkProject.domain.AdUserClickCount;
import com.xiayuan.sparkProject.jdbc.JDBCHelper;
import com.xiayuan.sparkProject.model.AdUserClickCountQueryResult;
//import it.unimi.dsi.fastutil.shorts.ShortRBTreeSet;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yeunsher
 * @date 2020-04-10 - 16:23
 */
public class AdUserClickCountDAOImpl implements IAdUserClickCountDAO {
    @Override
    public void updateBatch(List<AdUserClickCount> adUserClickCounts) {

        JDBCHelper jdbcHelper = JDBCHelper.getInstance();

        //首先将用户广告点击量进行分类，分成待插入和已插入
        List<AdUserClickCount> insertAdUserClickCounts = new ArrayList<>();
        List<AdUserClickCount> updateAdUserClickCounts = new ArrayList<>();

        String selectSQL = "SELECT count(*) FROM ad_user_click_count " +
                "WHERE date=? AND user_id=? AND adid=?";
        Object[] selectParam = null;

        for (AdUserClickCount adUserClickCount : adUserClickCounts) {
            final AdUserClickCountQueryResult queryResult = new AdUserClickCountQueryResult();

            selectParam = new Object[]{adUserClickCount.getDate(),
            adUserClickCount.getUserid(),
            adUserClickCount.getAdid()};

            jdbcHelper.executeQuery(selectSQL, selectParam, new JDBCHelper.QueryCallback() {
                @Override
                public void process(ResultSet rs) throws Exception {
                    if (rs.next()) {
                        int count = rs.getInt(1);
                        queryResult.setCount(count);
                    }
                }
            });

            int count = queryResult.getCount();
            if (count > 0) {
                updateAdUserClickCounts.add(adUserClickCount);
            } else {
                insertAdUserClickCounts.add(adUserClickCount);
            }
        }

        //执行批量插入
        String insertSQL = "insert into ad_user_click_count values(?,?,?,?)";
        ArrayList<Object[]> insertParamList = new ArrayList<>();

        for (AdUserClickCount adUserClickCount : insertAdUserClickCounts) {
            Object[] insertParam = new Object[]{
                    adUserClickCount.getDate(),
                    adUserClickCount.getUserid(),
                    adUserClickCount.getAdid(),
                    adUserClickCount.getClickCount()
            };
            insertParamList.add(insertParam);
        }
        jdbcHelper.executeBatch(insertSQL, insertParamList);

        //执行批量更新操作
        String updateSQL = "update ad_user_click_count set click_count=click_count+? where date=? and user_id=? and adid=?";
        List<Object[]> updateParamList = new ArrayList<>();
        for (AdUserClickCount adUserClickCount : updateAdUserClickCounts) {
            Object[] params = new Object[]{
                adUserClickCount.getClickCount(),
                adUserClickCount.getDate(),
                adUserClickCount.getUserid(),
                adUserClickCount.getAdid()
            };
            updateParamList.add(params);
        }
        jdbcHelper.executeBatch(updateSQL, updateParamList);
    }

    @Override
    public int findClickCountByMultiKey(String date, long userid, long adid) {
        String sql = "select click_count from ad_user_click_count where date =? and user_id=? and ad_id=?";
        Object[] params = new Object[]{date, userid, adid};
        final AdUserClickCountQueryResult queryResult = new AdUserClickCountQueryResult();

        JDBCHelper jdbcHelper = JDBCHelper.getInstance();
        jdbcHelper.executeQuery(sql, params, new JDBCHelper.QueryCallback() {
            @Override
            public void process(ResultSet rs) throws Exception {
                while (rs.next()){
                    int clickCount = rs.getInt(1);
                    queryResult.setCount(clickCount);
                }
            }
        });
        int clickCount = queryResult.getCount();
        return clickCount;
    }
}
