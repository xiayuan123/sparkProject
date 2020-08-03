package com.xiayuan.sparkProject.dao;

import com.xiayuan.sparkProject.domain.AdStat;

import java.util.List;

/**
 * 广告点击实时状态DAO接口
 * @author yeunsher
 * @date 2020-04-13 - 11:20
 */
public interface IAdStatDAO {
    //批量插入广告实时状态信息
    void updateBatch(List<AdStat> adStats);
}
