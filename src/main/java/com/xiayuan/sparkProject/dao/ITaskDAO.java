package com.xiayuan.sparkProject.dao;

import com.xiayuan.sparkProject.domain.Task;

/**
 * 任务管理DAO接口
 *
 * @author yeunsher
 * @date 2020-03-27 - 09:58
 */
public interface ITaskDAO {
    //根据主健查询业务
    Task findById(long taskid);

}
