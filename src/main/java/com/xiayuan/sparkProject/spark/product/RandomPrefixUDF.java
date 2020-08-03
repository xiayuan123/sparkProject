package com.xiayuan.sparkProject.spark.product;

import org.apache.spark.sql.api.java.UDF2;

import java.util.Random;

/**
 * 添加随机前缀
 *
 * @author yeunsher
 * @date 2020-07-21 - 16:23
 */
public class RandomPrefixUDF implements UDF2<String, Integer, String> {

    private static final long serialVersionUID = 1L;

    @Override
    public String call(String val, Integer num) throws Exception {
        Random random = new Random();
        int randNum = random.nextInt(10);
        return randNum + "_" + val;
    }

}
