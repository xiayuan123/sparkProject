package com.xiayuan.sparkProject.impl;

import com.xiayuan.sparkProject.dao.ITaskDAO;
import com.xiayuan.sparkProject.domain.Task;
import com.xiayuan.sparkProject.jdbc.JDBCHelper;

import java.sql.ResultSet;

/**
 * @author yeunsher
 * @date 2020-03-27 - 10:02
 */
public class TaskDAOImpl implements ITaskDAO {
    //根据主健查询任务
    @Override
    public Task findById(long taskid) {

        final Task task = new Task();

        String sql = "select * from task where task_id=?";
        Object[] params = new Object[]{taskid};

        JDBCHelper jdbcHelper = JDBCHelper.getInstance();
        jdbcHelper.executeQuery(sql, params, new JDBCHelper.QueryCallback() {
            @Override
            public void process(ResultSet rs) throws Exception {
                if (rs.next()) {
                    long taskid = rs.getLong(1);
                    String taskName = rs.getString(2);
                    String createTime = rs.getString(3);
                    String startTime = rs.getString(4);
                    String finishTime = rs.getString(5);
                    String taskType = rs.getString(6);
                    String taskStatus = rs.getString(7);
                    String taskParam = rs.getString(8);

                    task.setTaskid(taskid);
                    task.setTaskName(taskName);
                    task.setCreateTime(createTime);
                    task.setStartTime(startTime);
                    task.setFinishTime(finishTime);
                    task.setTaskType(taskType);
                    task.setTaskStatus(taskStatus);
                    task.setTaskParam(taskParam);
                }
            }
        });

        return task;
    }
}
