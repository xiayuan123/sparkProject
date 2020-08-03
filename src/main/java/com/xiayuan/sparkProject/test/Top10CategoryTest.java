package com.xiayuan.sparkProject.test;

import com.xiayuan.sparkProject.dao.ITop10CategoryDAO;
import com.xiayuan.sparkProject.domain.Top10Category;
import com.xiayuan.sparkProject.impl.DAOFactory;

/**
 * @author yeunsher
 * @date 2020-04-01 - 13:31
 */
public class Top10CategoryTest {
    public static void main(String[] args) {

        Top10Category top10Category = new Top10Category();
        ITop10CategoryDAO top10CategoryDAO = DAOFactory.getTop10CategoryDAO();

        top10Category.setTaskid(10L);
        top10Category.setCategoryid(132L);
        top10Category.setClickCount(1000L);
        top10Category.setOrderCount(100L);
        top10Category.setPayCount(19L);

        top10CategoryDAO.insert(top10Category);
    }
}
