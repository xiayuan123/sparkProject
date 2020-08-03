package com.xiayuan.sparkProject.impl;

import com.xiayuan.sparkProject.dao.IAdProvinceTop3DAO;
import com.xiayuan.sparkProject.domain.AdProvinceTop3;
import com.xiayuan.sparkProject.jdbc.JDBCHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yeunsher
 * @date 2020-04-14 - 09:19
 */
public class AdProvinceTop3DAOImpl implements IAdProvinceTop3DAO {
    @Override
    public void updateBatch(List<AdProvinceTop3> adProvinceTop3List) {
        JDBCHelper jdbcHelper = JDBCHelper.getInstance();

        //先做一次去重
        List<String> dateProvinces = new ArrayList<>();

        for (AdProvinceTop3 adProvinceTop3 : adProvinceTop3List) {
            String date = adProvinceTop3.getDate();
            String province = adProvinceTop3.getProvince();
            String key = date + "_" + province;
            if (!dateProvinces.contains(key)){
                dateProvinces.add(key);
            }
        }

        //根据去重后的date和province进行批量删除操作
        String deleteSQL = "delete from ad_province_top3 where date=? and province=?";
        List<Object[]> deleteParamsList = new ArrayList<>();

        for (String dateProvince : dateProvinces) {
            String[] keySplited = dateProvince.split("_");
            String date = keySplited[0];
            String province = keySplited[1];

            Object[] params = new Object[]{date, province};
            deleteParamsList.add(params);
        }

        jdbcHelper.executeBatch(deleteSQL, deleteParamsList);

        //批量插入传入进来的所有数据
        String insertSQL = "insert into ad_province_top3 values(?,?,?,?)";
        List<Object[]> insertParamsList = new ArrayList<>();
        for (AdProvinceTop3 adProvinceTop3 : adProvinceTop3List) {
            Object[] params = new Object[]{
                    adProvinceTop3.getDate(),
                    adProvinceTop3.getProvince(),
                    adProvinceTop3.getAdid(),
                    adProvinceTop3.getClickCount()
            };
            insertParamsList.add(params);
        }
        jdbcHelper.executeBatch(insertSQL, insertParamsList);
    }
}
