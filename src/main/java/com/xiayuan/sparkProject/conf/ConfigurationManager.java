package com.xiayuan.sparkProject.conf;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author yeunsher
 * @date 2020-03-26 - 09:42
 */
public class ConfigurationManager {
    private static Properties prop = new Properties();

    //静态代码块
    static {
        try {
            InputStream in = ConfigurationManager.class.getClassLoader().getResourceAsStream("my.properties");
            prop.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取指定key对应的value
     *
     * @param key
     * @return
     */
    public static String getProperty(String key){
        return prop.getProperty(key);
    }

    public static Integer getInteger(String key) {
        String value = prop.getProperty(key);

        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return 0;
    }


    public static boolean getBoolean(String key) {
        String value = prop.getProperty(key);
        try {
            return Boolean.valueOf(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Long getLong(String key) {
        String value = getProperty(key);
        try {
            return Long.valueOf(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }
}
