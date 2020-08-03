package com.xiayuan.sparkProject.test;

import com.xiayuan.sparkProject.jdbc.JDBCHelper;
import scala.collection.mutable.HashMap;

import java.sql.ResultSet;
import java.util.*;

/**
 * @author yeunsher
 * @date 2020-03-26 - 12:46
 */
public class JDBCHelperTest {
    public static void main(String[] args) {

        JDBCHelper jdbcHelper = JDBCHelper.getInstance();

        //测试普通的增删改语句
//        jdbcHelper.executeUpdate("insert into test_user(name,age) values(?,?)", new Object[]{"张三",20});

//        测试查询语句
        final LinkedList<HashMap<String, Object> > user = new LinkedList<HashMap<String, Object>>();
        jdbcHelper.executeQuery("select * from test_user", new Object[]{}, new JDBCHelper.QueryCallback() {
            @Override
            public void process(ResultSet re) throws Exception {
                while (re.next()) {
                    HashMap<String, Object> testuser = new HashMap<String, Object>();
                    String name = re.getString(1);
                    int age = re.getInt(2);

                    testuser.put("name", name);
                    testuser.put("age", age);

                      //匿名内部类的使用，有一个很重要的知识点
                      //如果要访问外部类中的一些成员，比如方法内的局部变量
                      //那么，必须将局部变量声明为final类型才能访问，
                      //否则是访问不了的
                    user.add(testuser);
                    System.out.println(name + ":" + age);
                }
            }
        });
        for (int i = 0; i < user.size(); i++) {
            System.out.println(user.get(i).get("name") + ":" + user.get(i).get("age"));
        }
//        System.out.println(user);

//        测试批量执行sql语句
//        String sql = "insert into test_user(name,age) value(?,?)";
//        List<Object[]> paramsList = new ArrayList<Object[]>();
//        paramsList.add(new Object[]{"麻子", 30});
//        paramsList.add(new Object[]{"李四", 35});
//        jdbcHelper.executeBatch(sql, paramsList);

    }
}
