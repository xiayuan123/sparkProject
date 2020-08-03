package com.xiayuan.sparkProject.dao;

import com.xiayuan.sparkProject.domain.AdClickTrend;

import java.util.List;

/**
 * @author yeunsher
 * @date 2020-04-14 - 10:31
 */
public interface IAdClickTrendDAO {
    //一小时内广告点击DAO接口类
    void updateBatch(List<AdClickTrend> adClickTrendList);
}
