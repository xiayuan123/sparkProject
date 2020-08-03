package com.xiayuan.sparkProject.spark.product;

import org.apache.spark.sql.api.java.UDF1;

/**
 * 去除随机前缀
 * @author yeunsher
 * @date 2020-07-21 - 16:24
 */
public class RemoveRandomPrefixUDF implements UDF1<String, String> {

    private static final long serialVersionUID = 1L;

    @Override
    public String call(String val) throws Exception {
        String[] valSplited = val.split("_");
        return valSplited[1];
    }

}