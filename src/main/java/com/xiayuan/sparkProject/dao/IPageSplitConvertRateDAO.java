package com.xiayuan.sparkProject.dao;

import com.xiayuan.sparkProject.domain.PageSplitConvertRate;

/**
 * @author yeunsher
 * @date 2020-04-04 - 19:40
 */
public interface IPageSplitConvertRateDAO {
    /**
     * 插入页面切片转化率的计算结果
     * @param pageSplitConvertRate
     */
    void insert(PageSplitConvertRate pageSplitConvertRate);
}
