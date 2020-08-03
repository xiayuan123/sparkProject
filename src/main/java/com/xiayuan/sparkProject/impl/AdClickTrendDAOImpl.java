package com.xiayuan.sparkProject.impl;

import com.xiayuan.sparkProject.dao.IAdClickTrendDAO;
import com.xiayuan.sparkProject.domain.AdClickTrend;
import com.xiayuan.sparkProject.jdbc.JDBCHelper;
import com.xiayuan.sparkProject.model.AdClickTrendQueryResult;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 一小时广告点击趋势DAO实现类
 * @author yeunsher
 * @date 2020-04-14 - 10:39
 */
public class AdClickTrendDAOImpl implements IAdClickTrendDAO {
    @Override
    public void updateBatch(List<AdClickTrend> adClickTrendList) {
        JDBCHelper jdbcHelper = JDBCHelper.getInstance();
        // 区分出来哪些数据是要插入的，哪些数据是要更新的
        // 提醒一下，比如说，通常来说，同一个key的数据（比如rdd，包含了多条相同的key）
        // 通常是在一个分区内的
        // 一般不会出现重复插入的

        // 但是根据业务需求来
        // 各位自己在实际做项目的时候，一定要自己思考，不要生搬硬套
        // 如果说可能会出现key重复插入的情况
        // 给一个create_time字段

        // j2ee系统在查询的时候，直接查询最新的数据即可（规避掉重复插入的问题）
        List<AdClickTrend> updateAdClickTrendList = new ArrayList<>();
        List<AdClickTrend> insertAdClickTrendList = new ArrayList<>();

        String selectSQL = "select count(*) from ad_click_trend " +
                "where date=? and hour=? and minute=? and ad_id=?";

        for (AdClickTrend adClickTrend : adClickTrendList) {
            AdClickTrendQueryResult queryResult = new AdClickTrendQueryResult();
            Object[] params = new Object[]{
                    adClickTrend.getDate(),
                    adClickTrend.getHour(),
                    adClickTrend.getMinute(),
                    adClickTrend.getAdid()
            };
            jdbcHelper.executeQuery(selectSQL, params, new JDBCHelper.QueryCallback() {
                @Override
                public void process(ResultSet rs) throws Exception {
                    while (rs.next()){
                        int clickCount = rs.getInt(1);
                        queryResult.setCount(clickCount);
                    }
                }
            });
            int count = queryResult.getCount();
            if (count > 0) {
                updateAdClickTrendList.add(adClickTrend);
            }else {
                insertAdClickTrendList.add(adClickTrend);
            }

        }

        //执行批量更新操作
        String updateSQL = "update ad_click_trend set click_count=? " +
                "where date=? and hour=? and minute=? and ad_id=?";
        List<Object[]> updateParamsList = new ArrayList<>();

        for (AdClickTrend adClickTrend : updateAdClickTrendList) {
            Object[] params = new Object[]{
                    adClickTrend.getClickCount(),
                    adClickTrend.getDate(),
                    adClickTrend.getHour(),
                    adClickTrend.getMinute(),
                    adClickTrend.getAdid()
            };
            updateParamsList.add(params);
        }
        jdbcHelper.executeBatch(updateSQL, updateParamsList);

        //执行批量插入操作
        String insertSQL = "insert into ad_click_count values(?,?,?,?,?)";
        List<Object[]> insertParamsList = new ArrayList<>();

        for (AdClickTrend adClickTrend : insertAdClickTrendList) {
            Object[] params = new Object[]{
                    adClickTrend.getDate(),
                    adClickTrend.getHour(),
                    adClickTrend.getMinute(),
                    adClickTrend.getAdid(),
                    adClickTrend.getClickCount()
            };

            insertParamsList.add(params);
        }
        jdbcHelper.executeBatch(insertSQL, insertParamsList);
    }
}
