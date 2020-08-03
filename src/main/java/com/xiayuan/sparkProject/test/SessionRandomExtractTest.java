package com.xiayuan.sparkProject.test;

import com.alibaba.fastjson.JSONObject;
import com.xiayuan.sparkProject.dao.ISessionRandomExtractDAO;
import com.xiayuan.sparkProject.dao.ITaskDAO;
import com.xiayuan.sparkProject.domain.SessionRandomExtract;
import com.xiayuan.sparkProject.domain.Task;
import com.xiayuan.sparkProject.impl.DAOFactory;
import com.xiayuan.sparkProject.util.ParamUtils;

/**
 * @author yeunsher
 * @date 2020-03-31 - 09:48
 */
public class SessionRandomExtractTest {
    public static void main(String[] args) {
        SessionRandomExtract sessionRandomExtract = new SessionRandomExtract();
        sessionRandomExtract.setTaskid(1);
        sessionRandomExtract.setSessionid("123");
        sessionRandomExtract.setStartTime("2020-03-31");
        sessionRandomExtract.setSearchKeyWords("milk");
        sessionRandomExtract.setClickCategoryIds("1,34,5");

        ISessionRandomExtractDAO sessionRandomExtractDAO = DAOFactory.getSessionRandomExtractDAO();
        sessionRandomExtractDAO.insert(sessionRandomExtract);


    }
}
