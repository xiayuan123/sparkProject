package com.xiayuan.sparkProject.impl;

import com.xiayuan.sparkProject.dao.IAdStatDAO;
import com.xiayuan.sparkProject.domain.AdStat;
import com.xiayuan.sparkProject.jdbc.JDBCHelper;
import com.xiayuan.sparkProject.model.AdStatQueryResult;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 广告实时点击状态DAO实现类
 * @author yeunsher
 * @date 2020-04-13 - 11:19
 */
public class AdStatDAOImpl implements IAdStatDAO {
    @Override
    public void updateBatch(List<AdStat> adStats) {
        JDBCHelper jdbcHelper = JDBCHelper.getInstance();

        AdStatQueryResult queryResult = new AdStatQueryResult();

        List<AdStat> updateAdStatList = new ArrayList<>();
        List<AdStat> insertAdStatList = new ArrayList<>();

        String sql = "select count(*) from ad_stat where date=? and province=? and city=? and adid=?";

        for (AdStat adStat : adStats) {
            Object[] params = new Object[]{
                    adStat.getDate(),
                    adStat.getProvince(),
                    adStat.getCity(),
                    adStat.getAdid()
            };
            jdbcHelper.executeQuery(sql, params, new JDBCHelper.QueryCallback() {
                @Override
                public void process(ResultSet rs) throws Exception {
                    while (rs.next()){
                        int count = rs.getInt(1);
                        queryResult.setCount(count);
                    }
                }
            });
            int count = queryResult.getCount();

            if (count > 0) {
                updateAdStatList.add(adStat);
            }else {
                insertAdStatList.add(adStat);
            }
        }

        String insertSQL = "insert into ad_stat values(?,?,?,?,?)";
        List<Object[]> insertParamsList = new ArrayList<>();
        for (AdStat adStat : insertAdStatList) {
            Object[] params = new Object[]{
                    adStat.getDate(),
                    adStat.getProvince(),
                    adStat.getCity(),
                    adStat.getAdid(),
                    adStat.getClickCount()
            };
            insertParamsList.add(params);
        }
        jdbcHelper.executeBatch(insertSQL, insertParamsList);

        //对已有的信息进行更新操作
        String updateSQL = "update ad_stat set click_count=? " +
                "where date=? and province=? and city=? and adid=?";
        List<Object[]> updateParamsList = new ArrayList<>();
        for (AdStat adStat : updateAdStatList) {
            Object[] params = new Object[]{
                    adStat.getClickCount(),
                    adStat.getDate(),
                    adStat.getProvince(),
                    adStat.getCity(),
                    adStat.getAdid()
            };
            updateParamsList.add(params);
        }
        jdbcHelper.executeBatch(updateSQL, updateParamsList);
    }
}
