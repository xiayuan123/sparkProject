package com.xiayuan.sparkProject.dao;

import com.xiayuan.sparkProject.domain.SessionRandomExtract;

/**
 * @author yeunsher
 * @date 2020-03-31 - 09:40
 */
public interface ISessionRandomExtractDAO {
    /**
     * 插入随机抽取的session
     * @param sessionRandomExtract
     */
    void insert(SessionRandomExtract sessionRandomExtract);
}
