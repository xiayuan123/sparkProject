package com.xiayuan.sparkProject.dao;

import com.xiayuan.sparkProject.domain.AdUserClickCount;

import java.util.List;

/**
 * @author yeunsher
 * @date 2020-04-10 - 16:21
 */
public interface IAdUserClickCountDAO {
    /**
     * 批量更新广告用户点击量
     */
    void updateBatch(List<AdUserClickCount> adUserClickCounts);

    int findClickCountByMultiKey(String date, long userid, long adid);
}
