//package com.xiayuan.sparkProject.test;
////
////import java.util.ArrayList;
////import java.util.Arrays;
////import java.util.List;
////import java.util.Random;
////import java.util.UUID;
////
////import org.apache.spark.api.java.JavaRDD;
////import org.apache.spark.api.java.JavaSparkContext;
////
//////import org.apache.spark.sql.DataFrame;
////import org.apache.spark.sql.Dataset;
//////import org.apache.spark.sql.DataFrame;
//////import org.apache.spark.sql.Dataset;
////import org.apache.spark.sql.Row;
////import org.apache.spark.sql.RowFactory;
////import org.apache.spark.sql.SQLContext;
////import org.apache.spark.sql.types.DataTypes;
////import org.apache.spark.sql.types.StructType;
////
////import com.xiayuan.sparkProject.util.DateUtils;
////import com.xiayuan.sparkProject.util.StringUtils;
////
/////**
//// * 模拟数据程序
//// * @author Administrator
//// *
//// */
////public class MockData {
////
////    /**
////     * 模拟数据
////     * @param sc
////     * @param sqlContext
////     */
////    public static void mock(JavaSparkContext sc,
////                            SQLContext sqlContext) {
////        List<Row> rows = new ArrayList<Row>();
////
////        String[] searchKeywords = new String[] {"火锅", "蛋糕", "重庆辣子鸡", "重庆小面",
////                "呷哺呷哺", "新辣道鱼火锅", "国贸大厦", "太古商场", "日本料理", "温泉"};
////
////        //生成的数据日期为当天日期，设置搜索参数时应注意搜索时间范围
////        String date = DateUtils.getTodayDate();
////        String[] actions = new String[]{"search", "click", "order", "pay"};
////        Random random = new Random();
////
////        for(int i = 0; i < 100; i++) {
////            long userid = random.nextInt(100);
////
////            for(int j = 0; j < 10; j++) {
////                String sessionid = UUID.randomUUID().toString().replace("-", "");
////                String baseActionTime = date + " " + random.nextInt(23);
////
////                Long clickCategoryId = null;
////
////                for(int k = 0; k < random.nextInt(100); k++) {
////                    long pageid = random.nextInt(10);
////                    String actionTime = baseActionTime + ":" + StringUtils.fulfuill(
////                            String.valueOf(random.nextInt(59))) + ":"
////                            + StringUtils.fulfuill(String.valueOf(random.nextInt(59)));
////                    String searchKeyword = null;
////                    Long clickProductId = null;
////                    String orderCategoryIds = null;
////                    String orderProductIds = null;
////                    String payCategoryIds = null;
////                    String payProductIds = null;
////
////                    String action = actions[random.nextInt(4)];
////                    if("search".equals(action)) {
////                        searchKeyword = searchKeywords[random.nextInt(10)];
////                    } else if("click".equals(action)) {
////                        if(clickCategoryId == null) {
//////                            clickCategoryId = Long.valueOf(String.valueOf(random.nextInt(100)));
////                            clickCategoryId = Long.valueOf(random.nextInt(100));
////                        }
//////                        clickProductId = Long.valueOf(String.valueOf(random.nextInt(100)));
////                        clickProductId = Long.valueOf(random.nextInt(100));
////                    } else if("order".equals(action)) {
////                        orderCategoryIds = String.valueOf(random.nextInt(100));
////                        orderProductIds = String.valueOf(random.nextInt(100));
////                    } else if("pay".equals(action)) {
////                        payCategoryIds = String.valueOf(random.nextInt(100));
////                        payProductIds = String.valueOf(random.nextInt(100));
////                    }
////
////                    Row row = RowFactory.create(date, userid, sessionid,
////                            pageid, actionTime, searchKeyword,
////                            clickCategoryId, clickProductId,
////                            orderCategoryIds, orderProductIds,
////                            payCategoryIds, payProductIds,
////                            Long.valueOf((random.nextInt(10))));
////
//////                    Row row = RowFactory.create(date, userid, sessionid,
//////                            pageid, actionTime, searchKeyword,
//////                            clickCategoryId, clickProductId,
//////                            orderCategoryIds, orderProductIds,
//////                            payCategoryIds, payProductIds);
////
////                    rows.add(row);
////                }
////            }
////        }
////
////        JavaRDD<Row> rowsRDD = sc.parallelize(rows);
////
////        StructType schema = DataTypes.createStructType(Arrays.asList(
////                DataTypes.createStructField("date", DataTypes.StringType, true),                //行为日期
////                DataTypes.createStructField("user_id", DataTypes.LongType, true),               //用户id
////                DataTypes.createStructField("session_id", DataTypes.StringType, true),  //唯一标识了用户一个访问session
////                DataTypes.createStructField("page_id", DataTypes.LongType, true),               //页面的id
////                DataTypes.createStructField("action_time", DataTypes.StringType, true),         //行为发生的时间
////                DataTypes.createStructField("search_keyword", DataTypes.StringType, true),      //搜索行为
////                DataTypes.createStructField("click_category_id", DataTypes.LongType, true),     //点击某品类，例如美食
////                DataTypes.createStructField("click_product_id", DataTypes.LongType, true),      //点击某个具体产品
////                DataTypes.createStructField("order_category_ids", DataTypes.StringType, true),  //对某些品类的下单行为
////                DataTypes.createStructField("order_product_ids", DataTypes.StringType, true),   //对某些商品的下单行为
////                DataTypes.createStructField("pay_category_ids", DataTypes.StringType, true),    //对某些品类的支付行为
////                DataTypes.createStructField("pay_product_ids", DataTypes.StringType, true),     //对某些商品的支付行为
////                DataTypes.createStructField("city_id", DataTypes.LongType, true)));
////
//////        StructType schema = DataTypes.createStructType(Arrays.asList(
//////                DataTypes.createStructField("date", DataTypes.StringType, true),
//////                DataTypes.createStructField("user_id", DataTypes.LongType, true),
//////                DataTypes.createStructField("session_id", DataTypes.StringType, true),
//////                DataTypes.createStructField("page_id", DataTypes.LongType, true),
//////                DataTypes.createStructField("action_time", DataTypes.StringType, true),
//////                DataTypes.createStructField("search_keyword", DataTypes.StringType, true),
//////                DataTypes.createStructField("click_category_id", DataTypes.LongType, true),
//////                DataTypes.createStructField("click_product_id", DataTypes.LongType, true),
//////                DataTypes.createStructField("order_category_ids", DataTypes.StringType, true),
//////                DataTypes.createStructField("order_product_ids", DataTypes.StringType, true),
//////                DataTypes.createStructField("pay_category_ids", DataTypes.StringType, true),
//////                DataTypes.createStructField("pay_product_ids", DataTypes.StringType, true)));
////
////
////        Dataset df = sqlContext.createDataFrame(rowsRDD, schema);
//////        Dataset<Row> df = sqlContext.createDataFrame(rowsRDD, schema);
////
////        df.registerTempTable("user_visit_action");
//////        df.show(10);
////
////
////        /**
////         * ==================================================================
////         */
////
////        rows.clear();
////        String[] sexes = new String[]{"male", "female"};
////        for(int i = 0; i < 100; i ++) {
////            long userid = i;
////            String username = "user" + i;
////            String name = "name" + i;
////            int age = random.nextInt(60);
////            String professional = "professional" + random.nextInt(100);
////            String city = "city" + random.nextInt(100);
////            String sex = sexes[random.nextInt(2)];
////
////            Row row = RowFactory.create(userid, username, name, age,
////                    professional, city, sex);
////            rows.add(row);
////        }
////
////        rowsRDD = sc.parallelize(rows);
////
////        StructType schema2 = DataTypes.createStructType(Arrays.asList(
////                DataTypes.createStructField("user_id", DataTypes.LongType, true),           //用户id
////                DataTypes.createStructField("username", DataTypes.StringType, true),        //用户登录名
////                DataTypes.createStructField("name", DataTypes.StringType, true),            //用户昵称
////                DataTypes.createStructField("age", DataTypes.IntegerType, true),            //用户年龄
////                DataTypes.createStructField("professional", DataTypes.StringType, true),    //用户职业
////                DataTypes.createStructField("city", DataTypes.StringType, true),            //用户所在城市
////                DataTypes.createStructField("sex", DataTypes.StringType, true)));           //用户性别
////
////        Dataset df2 = sqlContext.createDataFrame(rowsRDD, schema2);
//////        Dataset<Row> df2 = sqlContext.createDataFrame(rowsRDD, schema2);
////        df2.registerTempTable("user_info");
////
////        /**
////         * ==================================================================
////         */
////        rows.clear();
////
////        int[] productStatus = new int[]{0, 1};
////
////        for(int i = 0; i < 100; i ++) {
////            long productId = i;
////            String productName = "product" + i;
////            String extendInfo = "{\"product_status\": " + productStatus[random.nextInt(2)] + "}";
////
////            Row row = RowFactory.create(productId, productName, extendInfo);
////            rows.add(row);
////        }
////
////        rowsRDD = sc.parallelize(rows);
////
////        StructType schema3 = DataTypes.createStructType(Arrays.asList(
////                DataTypes.createStructField("product_id", DataTypes.LongType, true),
////                DataTypes.createStructField("product_name", DataTypes.StringType, true),
////                DataTypes.createStructField("extend_info", DataTypes.StringType, true)));
////
////        Dataset df3 = sqlContext.createDataFrame(rowsRDD, schema3);
////        System.out.println("df3");
////        df3.show();
////        df3.registerTempTable("product_info");
////
////    }
////
////}









package com.xiayuan.sparkProject.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.xiayuan.sparkProject.util.DateUtils;
import com.xiayuan.sparkProject.util.StringUtils;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.types.*;
import org.apache.spark.*;



/**
 * 模拟数据程序
 * @author Administrator
 *
 */
public class MockData {

    /**
     * 模拟数据
     * @param sc
     * @param sqlContext
     */
    public static void mock(JavaSparkContext sc,
                            SQLContext sqlContext) {
        List<Row> rows = new ArrayList<Row>();

        String[] searchKeywords = new String[] {"火锅", "蛋糕", "重庆辣子鸡", "重庆小面",
                "呷哺呷哺", "新辣道鱼火锅", "国贸大厦", "太古商场", "日本料理", "温泉"};
        String date = DateUtils.getTodayDate();
        String[] actions = new String[]{"search", "click", "order", "pay"};
        Random random = new Random();

        for(int i = 0; i < 100; i++) {
            long userid = random.nextInt(100);

            for(int j = 0; j < 10; j++) {
                String sessionid = UUID.randomUUID().toString().replace("-", "");
                String baseActionTime = date + " " + random.nextInt(23);

                Long clickCategoryId = null;

                for(int k = 0; k < random.nextInt(100); k++) {
                    long pageid = random.nextInt(10);
                    String actionTime = baseActionTime + ":" + StringUtils.fulfuill(String.valueOf(random.nextInt(59))) + ":" + StringUtils.fulfuill(String.valueOf(random.nextInt(59)));
                    String searchKeyword = null;
                    Long clickProductId = null;
                    String orderCategoryIds = null;
                    String orderProductIds = null;
                    String payCategoryIds = null;
                    String payProductIds = null;

                    String action = actions[random.nextInt(4)];
                    if("search".equals(action)) {
                        searchKeyword = searchKeywords[random.nextInt(10)];
                    } else if("click".equals(action)) {
                        if(clickCategoryId == null) {
                            clickCategoryId = Long.valueOf(String.valueOf(random.nextInt(100)));
                        }
                        clickProductId = Long.valueOf(String.valueOf(random.nextInt(100)));
                    } else if("order".equals(action)) {
                        orderCategoryIds = String.valueOf(random.nextInt(100));
                        orderProductIds = String.valueOf(random.nextInt(100));
                    } else if("pay".equals(action)) {
                        payCategoryIds = String.valueOf(random.nextInt(100));
                        payProductIds = String.valueOf(random.nextInt(100));
                    }

                    Row row = RowFactory.create(date, userid, sessionid,
                            pageid, actionTime, searchKeyword,
                            clickCategoryId, clickProductId,
                            orderCategoryIds, orderProductIds,
                            payCategoryIds, payProductIds,
                            Long.valueOf(String.valueOf(random.nextInt(10))));
                    rows.add(row);
                }
            }
        }

        JavaRDD<Row> rowsRDD = sc.parallelize(rows);

        StructType schema = DataTypes.createStructType(Arrays.asList(
                DataTypes.createStructField("date", DataTypes.StringType, true),
                DataTypes.createStructField("user_id", DataTypes.LongType, true),
                DataTypes.createStructField("session_id", DataTypes.StringType, true),
                DataTypes.createStructField("page_id", DataTypes.LongType, true),
                DataTypes.createStructField("action_time", DataTypes.StringType, true),
                DataTypes.createStructField("search_keyword", DataTypes.StringType, true),
                DataTypes.createStructField("click_category_id", DataTypes.LongType, true),
                DataTypes.createStructField("click_product_id", DataTypes.LongType, true),
                DataTypes.createStructField("order_category_ids", DataTypes.StringType, true),
                DataTypes.createStructField("order_product_ids", DataTypes.StringType, true),
                DataTypes.createStructField("pay_category_ids", DataTypes.StringType, true),
                DataTypes.createStructField("pay_product_ids", DataTypes.StringType, true),
                DataTypes.createStructField("city_id", DataTypes.LongType, true)));

        Dataset df = sqlContext.createDataFrame(rowsRDD, schema);


        df.registerTempTable("user_visit_action");
        df.show();



        /**
         * ==================================================================
         */

        rows.clear();
        String[] sexes = new String[]{"male", "female"};
        for(int i = 0; i < 100; i ++) {
            long userid = i;
            String username = "user" + i;
            String name = "name" + i;
            int age = random.nextInt(60);
            String professional = "professional" + random.nextInt(100);
            String city = "city" + random.nextInt(100);
            String sex = sexes[random.nextInt(2)];

            Row row = RowFactory.create(userid, username, name, age,
                    professional, city, sex);
            rows.add(row);
        }

        rowsRDD = sc.parallelize(rows);

        StructType schema2 = DataTypes.createStructType(Arrays.asList(
                DataTypes.createStructField("user_id", DataTypes.LongType, true),
                DataTypes.createStructField("username", DataTypes.StringType, true),
                DataTypes.createStructField("name", DataTypes.StringType, true),
                DataTypes.createStructField("age", DataTypes.IntegerType, true),
                DataTypes.createStructField("professional", DataTypes.StringType, true),
                DataTypes.createStructField("city", DataTypes.StringType, true),
                DataTypes.createStructField("sex", DataTypes.StringType, true)));

        Dataset df2 = sqlContext.createDataFrame(rowsRDD, schema2);


        df2.registerTempTable("user_info");

        /**
         * ==================================================================
         */
        rows.clear();

        int[] productStatus = new int[]{0, 1};

        for(int i = 0; i < 100; i ++) {
            long productId = i;
            String productName = "product" + i;
            String extendInfo = "{\"product_status\": " + productStatus[random.nextInt(2)] + "}";

            Row row = RowFactory.create(productId, productName, extendInfo);
            rows.add(row);
        }

        rowsRDD = sc.parallelize(rows);

        StructType schema3 = DataTypes.createStructType(Arrays.asList(
                DataTypes.createStructField("product_id", DataTypes.LongType, true),
                DataTypes.createStructField("product_name", DataTypes.StringType, true),
                DataTypes.createStructField("extend_info", DataTypes.StringType, true)));

        Dataset df3 = sqlContext.createDataFrame(rowsRDD, schema3);


        df3.registerTempTable("product_info");
    }

}
