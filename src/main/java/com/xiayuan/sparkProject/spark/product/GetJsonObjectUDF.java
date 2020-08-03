package com.xiayuan.sparkProject.spark.product;

import com.alibaba.fastjson.JSONObject;
import org.apache.spark.sql.api.java.UDF2;

/**
 * 自定义udf函数
 * @author yeunsher
 * @date 2020-04-07 - 10:21
 */
public class GetJsonObjectUDF implements UDF2<String, String, String> {
    private static final long serialVersionUID = 1L;
    @Override
    public String call(String json, String field) throws Exception {
        try {
            JSONObject jsonObject = JSONObject.parseObject(json);
            return jsonObject.getString(field);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
