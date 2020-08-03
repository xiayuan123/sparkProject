package com.xiayuan.sparkProject.util;

import com.alibaba.fastjson.JSONObject;
import com.xiayuan.sparkProject.conf.ConfigurationManager;
import com.xiayuan.sparkProject.constant.Constants;
import com.xiayuan.sparkProject.test.MockData;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
//import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.hive.HiveContext;

/**
 * @author yeunsher
 * @date 2020-04-04 - 10:55
 */
public class SparkUtils {
    /**
     * 根据当前是否是本地配置模式来决定
     * 如果是设置sparkConf的master
     * @param conf
     */
    public static void setMaster(SparkConf conf) {
        boolean local = ConfigurationManager.getBoolean(Constants.SPARK_LOCAL);
        if (local) {
            conf.setMaster("local");
        }
    }

    /**
     * 获取SQLContext
     * 如果在本地测试环境下，生产SQLContext对象
     * 如果在生产环境下，生产HiveContext对象
     * @param sc
     * @return SQLContext
     */
    public static SQLContext getSQLContext(SparkContext sc) {
        boolean local = ConfigurationManager.getBoolean(Constants.SPARK_LOCAL);
        if (local) {
            return new SQLContext(sc);
        } else {
            return new HiveContext(sc);
        }
    }

    /**
     * 生产模拟数据（只有在本地模式才会生成）
     * @param sc
     * @param sqlContext
     */
    public static void mockData(JavaSparkContext sc, SQLContext sqlContext) {
        boolean local = ConfigurationManager.getBoolean(Constants.SPARK_LOCAL);
        if (local) {
            MockData.mock(sc, sqlContext);
        }
    }

    /**
     * 获取指定日期范围内的用户访问行为数据
     * @param sqlContext SQLContext
     * @param taskParam 任务参数
     * @return 行为数据RDD
     */
    public static JavaRDD<Row> getActionRDDByDateRange(
            SQLContext sqlContext, JSONObject taskParam) {
        String startDate = ParamUtils.getParam(taskParam, Constants.PARAM_START_DATE);
        String endDate = ParamUtils.getParam(taskParam, Constants.PARAM_END_DATE);

        String sql = "select * "
                + "from user_visit_action "
                + "where date >='" + startDate + "' "
                + "and date <='" + endDate + "'";
        Dataset actionDF = sqlContext.sql(sql);
        actionDF.show(10);
        /**
         * 这里就很有可能发生上面说的问题
         * 比如说，Spark SQl默认就给第一个stage设置了20个task，但是根据你的数据量以及算法的复杂度
         * 实际上，你需要1000个task去并行执行
         *
         * 所以说，在这里，就可以对Spark SQL刚刚查询出来的RDD执行repartition重分区操作
         */
        //return  actionDF.javaRDD().repartition(100);

        return actionDF.javaRDD();

    }
}
