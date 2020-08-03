package com.xiayuan.sparkProject.test;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiayuan.sparkProject.constant.Constants;
import com.xiayuan.sparkProject.domain.Task;
import com.xiayuan.sparkProject.util.ParamUtils;

/**
 * @author yeunsher
 * @date 2020-03-27 - 10:31
 */
public class FastjsonTest {
    public static void main(String[] args) {
        String json = "[{\"学生\":\"张三\",'班级':'一班','年级':'大一','科目':'高数','成绩':90}," +
                "{\"学生\":\"李四\",'班级':'二班','年级':'大一','科目':'高数','成绩':80}]";

        String COMPLEX_JSON_STR = "{\"teacherName\":\"crystall\",\"teacherAge\":27,\"course\":{\"courseName\":\"english\",\"code\":1270},\"students\":[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]}";

        String COMPLEX_JSON_STR2 = "{\"teacherName\":\"crystall\",\"teacherAge\":27,\"course\":{\"courseName\":\"english\",\"code\":1270},\"students\":[\"sss\"]}";


//        long taskid = ParamUtils.getTaskIdFromArgs(args);
//        Task task = taskDAO.findById(taskid);
//        JSONObject taskParam = JSONObject.parseObject(task.getTaskName());
//        JSONArray jsonArray = JSONArray.parseArray(json);
        JSONObject jsonObject = JSONObject.parseObject(COMPLEX_JSON_STR);
//        JSONArray jsonArray = jsonObject.getJSONArray("");
        String param = ParamUtils.getParam(jsonObject, "students");

        System.out.println(param);


    }
}
