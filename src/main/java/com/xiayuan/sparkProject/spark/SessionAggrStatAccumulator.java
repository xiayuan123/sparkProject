package com.xiayuan.sparkProject.spark;

import com.xiayuan.sparkProject.constant.Constants;
import com.xiayuan.sparkProject.util.StringUtils;
import org.apache.spark.AccumulatorParam;

/**
 * session聚合统计Accumulator
 *
 * 使用自己定义的一些数据格式，比如String，甚至说，我们可以自定义model，自己定义的类（必须可序列化）
 * 然后可以基于这种特殊的数据格式实现复杂的分布式计算逻辑
 * 各个task分布式在运行，可以根据自己的需求，task给Accumulator传入不同的值
 * 根据不同的值，做复杂的逻辑
 *
 * @author yeunsher
 * @date 2020-03-29 - 15:54
 */
public class SessionAggrStatAccumulator implements AccumulatorParam<String> {
    private static final long serialVersionUID = -2113961376143864034L;

    //zero方法，主要用于数据初始化
    //这里就返回一个值，就是初始化中，所有范围区间的数值都是0
    //各个范围区间的统计数量的拼接，还是采用key=value|key=value的连接串格式
    //在Constants.java中添加
    //String SESSION_COUNT = "session_count";
    //String TIME_PERIOD_1s_3s = "1s_3s";
    //String TIME_PERIOD_4s_6s = "4s_6s";
    //String TIME_PERIOD_7s_9s = "7s_9s";
    //String TIME_PERIOD_10s_30s = "10s_30s";
    //String TIME_PERIOD_30s_60s = "30s_60s";
    //String TIME_PERIOD_1m_3m = "1m_3m";
    //String TIME_PERIOD_3m_10m = "3m_10m";
    //String TIME_PERIOD_10m_30m = "10m_30m";
    //String TIME_PERIOD_30m = "30m";
    //String STEP_PERIOD_1_3 = "1_3";
    //String STEP_PERIOD_4_6 = "4_6";
    //String STEP_PERIOD_7_9 = "7_9";
    //String STEP_PERIOD_10_30 = "10_30";
    //String STEP_PERIOD_30_60 = "30_60";
    //String STEP_PERIOD_60 = "60";
    public String zero(String v) {
        return Constants.SESSION_COUNT + "=0|"
                + Constants.TIME_PERIOD_1s_3s + "=0|"
                + Constants.TIME_PERIOD_4s_6s + "=0|"
                + Constants.TIME_PERIOD_7s_9s + "=0|"
                + Constants.TIME_PERIOD_10s_30s + "=0|"
                + Constants.TIME_PERIOD_30s_60s + "=0|"
                + Constants.TIME_PERIOD_1m_3m + "=0|"
                + Constants.TIME_PERIOD_3m_10m + "=0|"
                + Constants.TIME_PERIOD_10m_30m + "=0|"
                + Constants.TIME_PERIOD_30m + "=0|"
                + Constants.STEP_PERIOD_1_3 + "=0|"
                + Constants.STEP_PERIOD_4_6 + "=0|"
                + Constants.STEP_PERIOD_7_9 + "=0|"
                + Constants.STEP_PERIOD_10_30 + "=0|"
                + Constants.STEP_PERIOD_30_60 + "=0|"
                + Constants.STEP_PERIOD_60 + "=0";
    }

    //addInPlace和addAccumulator可以理解为是一样的
    //这两个方法，主要实现，v1可能就是我们初始化的那个连接串
    //v2就是在遍历session的时候，判断出某个session对应的区间，然后用Constants.TIME_PERIOD_1S_3S
    //所以，我们要做的事情就是在v1中，找到对应的v2对应的value，累加1，然后再更新到连接串里面去
    public String addInPlace(String v1, String v2) {
        return add(v1, v2);
    }

    public String addAccumulator(String r1, String r2) {

        if(StringUtils.isEmpty(r1)) {
            return r2;
        }
        // 使用StringUtils工具类，从r1 r2对应的值，并累加
        int old_count = Integer.parseInt(StringUtils.getFieldFromConcatString(r1,"\\|", Constants.SESSION_COUNT));
        int old_TIME_PERIOD_1s_3s = Integer.parseInt(StringUtils.getFieldFromConcatString(r1,"\\|",Constants.TIME_PERIOD_1s_3s));
        int old_TIME_PERIOD_4s_6s = Integer.parseInt(StringUtils.getFieldFromConcatString(r1,"\\|",Constants.TIME_PERIOD_4s_6s));
        int old_TIME_PERIOD_7s_9s = Integer.parseInt(StringUtils.getFieldFromConcatString(r1,"\\|",Constants.TIME_PERIOD_7s_9s));
        int old_TIME_PERIOD_10s_30s = Integer.parseInt(StringUtils.getFieldFromConcatString(r1,"\\|",Constants.TIME_PERIOD_10s_30s));
        int old_TIME_PERIOD_30s_60s = Integer.parseInt(StringUtils.getFieldFromConcatString(r1,"\\|",Constants.TIME_PERIOD_30s_60s));
        int old_TIME_PERIOD_1m_3m = Integer.parseInt(StringUtils.getFieldFromConcatString(r1,"\\|",Constants.TIME_PERIOD_1m_3m));
        int old_TIME_PERIOD_3m_10m = Integer.parseInt(StringUtils.getFieldFromConcatString(r1,"\\|",Constants.TIME_PERIOD_3m_10m));
        int old_TIME_PERIOD_10m_30m = Integer.parseInt(StringUtils.getFieldFromConcatString(r1,"\\|",Constants.TIME_PERIOD_10m_30m));
        int old_TIME_PERIOD_30m = Integer.parseInt(StringUtils.getFieldFromConcatString(r1,"\\|",Constants.TIME_PERIOD_30m));

        int old_STEP_PERIOD_1_3 = Integer.parseInt(StringUtils.getFieldFromConcatString(r1,"\\|",Constants.STEP_PERIOD_1_3));
        int old_STEP_PERIOD_4_6 = Integer.parseInt(StringUtils.getFieldFromConcatString(r1,"\\|",Constants.STEP_PERIOD_4_6));
        int old_STEP_PERIOD_7_9 = Integer.parseInt(StringUtils.getFieldFromConcatString(r1,"\\|",Constants.STEP_PERIOD_7_9));
        int old_STEP_PERIOD_10_30 = Integer.parseInt(StringUtils.getFieldFromConcatString(r1,"\\|",Constants.STEP_PERIOD_10_30));
        int old_STEP_PERIOD_30_60 = Integer.parseInt(StringUtils.getFieldFromConcatString(r1,"\\|",Constants.STEP_PERIOD_30_60));
        int old_STEP_PERIOD_60 = Integer.parseInt(StringUtils.getFieldFromConcatString(r1,"\\|",Constants.STEP_PERIOD_60));

        int new_count = Integer.parseInt(StringUtils.getFieldFromConcatString(r2,"\\|", Constants.SESSION_COUNT));
        int new_TIME_PERIOD_1s_3s = Integer.parseInt(StringUtils.getFieldFromConcatString(r2,"\\|",Constants.TIME_PERIOD_1s_3s));
        int new_TIME_PERIOD_4s_6s = Integer.parseInt(StringUtils.getFieldFromConcatString(r2,"\\|",Constants.TIME_PERIOD_4s_6s));
        int new_TIME_PERIOD_7s_9s = Integer.parseInt(StringUtils.getFieldFromConcatString(r2,"\\|",Constants.TIME_PERIOD_7s_9s));
        int new_TIME_PERIOD_10s_30s = Integer.parseInt(StringUtils.getFieldFromConcatString(r2,"\\|",Constants.TIME_PERIOD_10s_30s));
        int new_TIME_PERIOD_30s_60s = Integer.parseInt(StringUtils.getFieldFromConcatString(r2,"\\|",Constants.TIME_PERIOD_30s_60s));
        int new_TIME_PERIOD_1m_3m = Integer.parseInt(StringUtils.getFieldFromConcatString(r2,"\\|",Constants.TIME_PERIOD_1m_3m));
        int new_TIME_PERIOD_3m_10m = Integer.parseInt(StringUtils.getFieldFromConcatString(r2,"\\|",Constants.TIME_PERIOD_3m_10m));
        int new_TIME_PERIOD_10m_30m = Integer.parseInt(StringUtils.getFieldFromConcatString(r2,"\\|",Constants.TIME_PERIOD_10m_30m));
        int new_TIME_PERIOD_30m = Integer.parseInt(StringUtils.getFieldFromConcatString(r2,"\\|",Constants.TIME_PERIOD_30m));

        int new_STEP_PERIOD_1_3 = Integer.parseInt(StringUtils.getFieldFromConcatString(r2,"\\|",Constants.STEP_PERIOD_1_3));
        int new_STEP_PERIOD_4_6 = Integer.parseInt(StringUtils.getFieldFromConcatString(r2,"\\|",Constants.STEP_PERIOD_4_6));
        int new_STEP_PERIOD_7_9 = Integer.parseInt(StringUtils.getFieldFromConcatString(r2,"\\|",Constants.STEP_PERIOD_7_9));
        int new_STEP_PERIOD_10_30 = Integer.parseInt(StringUtils.getFieldFromConcatString(r2,"\\|",Constants.STEP_PERIOD_10_30));
        int new_STEP_PERIOD_30_60 = Integer.parseInt(StringUtils.getFieldFromConcatString(r2,"\\|",Constants.STEP_PERIOD_30_60));
        int new_STEP_PERIOD_60 = Integer.parseInt(StringUtils.getFieldFromConcatString(r2,"\\|",Constants.STEP_PERIOD_60));



        return Constants.SESSION_COUNT+"="+(old_count+new_count)+"|"
                + Constants.TIME_PERIOD_1s_3s+"="+(old_TIME_PERIOD_1s_3s+new_TIME_PERIOD_1s_3s)+"|"
                + Constants.TIME_PERIOD_4s_6s+"="+(old_TIME_PERIOD_4s_6s+new_TIME_PERIOD_4s_6s)+"|"
                + Constants.TIME_PERIOD_7s_9s+"="+(old_TIME_PERIOD_7s_9s+new_TIME_PERIOD_7s_9s)+"|"
                + Constants.TIME_PERIOD_10s_30s+"="+(old_TIME_PERIOD_10s_30s+new_TIME_PERIOD_10s_30s)+"|"
                + Constants.TIME_PERIOD_30s_60s+"="+(old_TIME_PERIOD_30s_60s+new_TIME_PERIOD_30s_60s)+"|"
                + Constants.TIME_PERIOD_1m_3m+"="+(old_TIME_PERIOD_1m_3m+new_TIME_PERIOD_1m_3m)+"|"
                + Constants.TIME_PERIOD_3m_10m+"="+(old_TIME_PERIOD_3m_10m+new_TIME_PERIOD_3m_10m)+"|"
                + Constants.TIME_PERIOD_10m_30m+"="+(old_TIME_PERIOD_10m_30m+new_TIME_PERIOD_10m_30m)+"|"
                + Constants.TIME_PERIOD_30m+"="+(old_TIME_PERIOD_30m+new_TIME_PERIOD_30m)+"|"
                + Constants.STEP_PERIOD_1_3+"="+(old_STEP_PERIOD_1_3+new_STEP_PERIOD_1_3)+"|"
                + Constants.STEP_PERIOD_4_6+"="+(old_STEP_PERIOD_4_6+new_STEP_PERIOD_4_6)+"|"
                + Constants.STEP_PERIOD_7_9+"="+(old_STEP_PERIOD_7_9+new_STEP_PERIOD_7_9)+"|"
                + Constants.STEP_PERIOD_10_30+"="+(old_STEP_PERIOD_10_30+new_STEP_PERIOD_10_30)+"|"
                + Constants.STEP_PERIOD_30_60+"="+(old_STEP_PERIOD_30_60+new_STEP_PERIOD_30_60)+"|"
                + Constants.STEP_PERIOD_60+"="+(old_STEP_PERIOD_60+new_STEP_PERIOD_60);


    }

    /**
     * session统计计算逻辑
     *
     * @param v1 连接串
     * @param v2 更新范围
     * @return 更新以后的连接串
     */
    private String add(String v1, String v2) {
        //如果v1为空，直接返回v2
        if (StringUtils.isEmpty(v2)) {
            return v1;
        }

        //从v1中提取出来v2作为key对应的value值，并累加1
        String new_v1 = v1;
        String old_count = StringUtils.getFieldFromConcatString(v1, "\\|", Constants.SESSION_COUNT);
        String oldValue = StringUtils.getFieldFromConcatString(v1, "\\|", v2);
        if (oldValue != null) {
            //将范围空间原有的值累加1
            int newValue = Integer.valueOf(oldValue) + 1;
            int newCount = Integer.valueOf(old_count) + 1;

            new_v1 = StringUtils.setFieldInConcatString(v1, "\\|", Constants.SESSION_COUNT, String.valueOf(newCount));
            return StringUtils.setFieldInConcatString(new_v1, "\\|", v2, String.valueOf(newValue));
        }

        return new_v1;
    }


}
