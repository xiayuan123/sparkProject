package com.xiayuan.sparkProject.util;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.xiayuan.sparkProject.conf.ConfigurationManager;
import com.xiayuan.sparkProject.constant.Constants;

/**
 * 参数工具类
 * 参数工具类具有从命令行参数中提取任务id以及从JSON对象中提取参数的功能。
 *
 * @author yeunsher
 * @date 2020-03-25 - 17:00
 */
public class ParamUtils {

    /**
     * 从命令行参数中提取任务id
     * @param args 命令行参数
     * @return 任务id
     */
    public static Long getTaskIdFromArgs(String[] args, String taskType){
        boolean local = ConfigurationManager.getBoolean(Constants.SPARK_LOCAL);
        if (local) {
            return ConfigurationManager.getLong(taskType);
        } else {
            try {
                if (args != null && args.length > 0){
                    return Long.valueOf(args[0]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return null;
    }

    /**
     * 从json对象中提取参数
     * @param jsonObject json对象
     * @return 参数
     */
    public static String getParam(JSONObject jsonObject, String field) throws JSONException {
        JSONArray jsonArray = jsonObject.getJSONArray(field);
        if (jsonArray != null && jsonArray.size() > 0){
            return jsonArray.getString(0);
        }
        return null;
    }

}
