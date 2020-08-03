package com.xiayuan.sparkProject.test;

import com.xiayuan.sparkProject.dao.ITaskDAO;
import com.xiayuan.sparkProject.domain.Task;
import com.xiayuan.sparkProject.impl.DAOFactory;

/**
 * @author yeunsher
 * @date 2020-03-27 - 10:22
 */
public class TaskDAOTest {
    public static void main(String[] args) {
        ITaskDAO taskDAO = DAOFactory.getTaskDAO();
        Task task = taskDAO.findById(1);
        System.out.println(task.getTaskName());
    }
}
