package com.xiayuan.sparkProject.dao;

import com.xiayuan.sparkProject.domain.AdBlacklist;

import java.util.List;

/**
 * @author yeunsher
 * @date 2020-04-10 - 18:03
 */
public interface IAdBlacklistDAO {
    //批量插入广告黑名单用户
    void insertBatch(List<AdBlacklist> adBlacklists);

    //查找所有广告黑名单用户
    List<AdBlacklist> findAll();
}
