package com.xiayuan.sparkProject.dao;

import com.xiayuan.sparkProject.domain.SessionDetail;

/**
 * @author yeunsher
 * @date 2020-03-31 - 10:53
 */
public interface ISessionDetailDAO {
    /**
     * 插入一条session明细
     * @param sessionDetail
     */
    void insert(SessionDetail sessionDetail);
}
