package com.xiayuan.sparkProject.dao;

import com.xiayuan.sparkProject.domain.AdProvinceTop3;

import java.util.List;

/**
 * @author yeunsher
 * @date 2020-04-14 - 09:17
 */
public interface IAdProvinceTop3DAO {
    //批量更新各省份热门top3广告
    void updateBatch(List<AdProvinceTop3> adProvinceTop3List);
}
