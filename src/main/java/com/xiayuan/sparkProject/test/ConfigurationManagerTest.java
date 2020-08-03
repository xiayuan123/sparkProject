package com.xiayuan.sparkProject.test;

import com.xiayuan.sparkProject.conf.ConfigurationManager;

/**
 * 配置管理组件测试类
 *
 * @author yeunsher
 * @date 2020-03-26 - 10:02
 */
public class ConfigurationManagerTest {

    public static void main(String[] args) {
        String testk1 = ConfigurationManager.getProperty("testkey1");
        String testk2 = ConfigurationManager.getProperty("testkey2");
        System.out.println(testk1+"  "+testk2);
    }

}
