package com.xiayuan.sparkProject.jdbc;

import com.xiayuan.sparkProject.conf.ConfigurationManager;
import com.xiayuan.sparkProject.constant.Constants;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * JDBC辅助组件
 *
 * @author yeunsher
 * @date 2020-03-26 - 10:44
 */
public class JDBCHelper {

    // 第一步：在静态代码块中，直接加载数据库的驱动
    // 加载驱动，不是直接简单的，使用com.mysql.jdbc.Driver就可以了
    // 之所以说，不要硬编码，他的原因就在于这里
    //
    // com.mysql.jdbc.Driver只代表了MySQL数据库的驱动
    // 那么，如果有一天，我们的项目底层的数据库要进行迁移，比如迁移到Oracle
    // 或者是DB2、SQLServer
    // 那么，就必须很费劲的在代码中，找，找到硬编码了com.mysql.jdbc.Driver的地方，然后改成
    // 其他数据库的驱动类的类名
    // 所以正规项目，是不允许硬编码的，那样维护成本很高
    //
    // 通常，我们都是用一个常量接口中的某个常量，来代表一个值
    // 然后在这个值改变的时候，只要改变常量接口中的常量对应的值就可以了
    //
    // 项目，要尽量做成可配置的
    // 就是说，我们的这个数据库驱动，更进一步，也不只是放在常量接口中就可以了
    // 最好的方式，是放在外部的配置文件中，跟代码彻底分离
    // 常量接口中，只是包含了这个值对应的key的名字

    /**
     * 第一步：在静态代码块中直接搭载数据库驱动
     * 加载驱动，不要硬编码
     * 在my.properties中添加
     * jdbc.driver=com.mysql.jdbc.Driver
     * 在Constants.java中添加
     * String JDBC_DRIVER = "jdbc.driver";
     */
    static {
        try {
            String driver = ConfigurationManager.getProperty(Constants.JDBC_DRIVER);
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 第二步，实现JDBCHelper的单例化
     * 因为它的内部要封装一个简单的内部的数据库连接池
     * 为了保证数据库连接池有且只有一份，所以就通过单例的方式
     * 保证JDBCHelper只有一个实例，实例中只有一份数据库连接池
     */
    private static JDBCHelper instance = null;

    //获取单例
    public static JDBCHelper getInstance() {
        if (instance == null) {
            synchronized (JDBCHelper.class) {
                if (instance == null) {
                    instance = new JDBCHelper();
                }
            }
        }
        return instance;
    }
    //数据库连接池
    private LinkedList<Connection> datasource = new LinkedList<Connection>();

    /**
     * 第三步：实现单例的过程中，创建唯一的数据库连接池
     *
     * 私有化构造方法
     *
     * JDBCHelper在整个程序运行生命周期中，只会创建一次实例
     * 在这一次创建实例的过程中，就会调用JDBCHelper（）的方法
     * 此时，就可以在构造方法中，去创建自己唯一的一个数据库连接池
     */
    private JDBCHelper() {
        /**
         * 第一步，获取数据库连接池大小，
         * 通过配置文件中配置来设定
         * my.properties中添加jdbc.datasource.size=10
         * 在Constants类中添加String JDBC_DATASOURCE_SIZE="jdbc.datasource.size";
         *
         */
        int datasourcesSize = ConfigurationManager.getInteger(Constants.JDBC_DATASOURCE_SIZE);

        //创建指定数量的数据库连接，放入数据库连接池中
        for (int i = 0; i < datasourcesSize; i++) {
            /**
             * 要先在my.properties创建jdbc url user password
             * jdbc.url=jdbc:mysql://localhost:3306/sparkproject
             * jdbc.user=root
             * jdbc.password=erik
             *
             * 然后在Constants.java中添加JDBC_URL等信息
             * String JDBC_URL="jdbc.url";
             * String JDBC_USER="jdbc.user";
             * String JDBC_PASSWORD="jdbc.password";
             * String SPARK_LOCAL = "spark.local";
             */
            boolean local = ConfigurationManager.getBoolean(Constants.SPARK_LOCAL);
            String url = null;
            String user = null;
            String password = null;

            if (local) {
                url = ConfigurationManager.getProperty(Constants.JDBC_URL);
                user = ConfigurationManager.getProperty(Constants.JDBC_USER);
                password = ConfigurationManager.getProperty(Constants.JDBC_PASSWORD);

            } else {
                url = ConfigurationManager.getProperty(Constants.JDBC_URL_PROD);
                user = ConfigurationManager.getProperty(Constants.JDBC_USER_PROD);
                password = ConfigurationManager.getProperty(Constants.JDBC_PASSWORD_PROD);

            }


            try {
                Connection conn = DriverManager.getConnection(url, user, password);
                datasource.push(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 第四步，提供获取连接数据库的方法
     * 有可能，获取的时候连接池用光了，暂时获取不到数据库连接
     * 所以要编写一个简单的等待机制，等待获取到数据库连接
     *
     * synchronized设置多线程并发访问限制
     *
     */
    public synchronized Connection getConnection() {

        try {
            while (datasource.size() == 0) {
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return datasource.poll();
    }

    /**
     * 第五步，开发增删改查的方法
     * 1.执行增删sql语句的方法
     * 2.执行查询sql语句的方法
     * 3.批量执行sql语句的方法
     */

    /**
     * 1.执行增删sql语句的方法
     * @param sql
     * @param params
     * @return 影响的行数
     */
    public int executeUpdate(String sql, Object[] params) {
        int rtn = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            pstmt = conn.prepareStatement(sql);

            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
            }
            rtn = pstmt.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                datasource.push(conn);
            }
        }
        return rtn;
    }

    //执行查询sql语句
    public void executeQuery(String sql, Object[] params, QueryCallback callback) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }

            rs = pstmt.executeQuery();

            callback.process(rs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                datasource.push(conn);
            }
        }
    }

    /**
     * 批量执行sql语句
     *
     *  * 批量执行SQL语句，是JDBC中的一个高级功能
     * 默认情况下，每次执行一条SQL语句，就会通过网络连接，向MySQL发送一次请求
     * 但是，如果在短时间内要执行多条结构完全一样的SQL，只是参数不同
     * 虽然使用PreparedStatment这种方式，可以只编译一次SQL，提高性能，
     * 但是，还是对于每次SQL都要向MySQL发送一次网络请求
     *
     * 可以通过批量执行SQL语句的功能优化这个性能
     * 一次性通过PreparedStatement发送多条SQL语句，可以几百几千甚至几万条
     * 执行的时候，也仅仅编译一次就可以
     * 这种批量执行SQL语句的方式，可以大大提升性能
     *
     * @param sql
     * @param paramsList
     * @return 每条SQL语句影响的行数
     */
    public int[] executeBatch (String sql, List<Object[]> paramsList) {
        int[] rtn = null;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            //第一步，使用Connection对象，取消自动提交
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);
            for (Object[] params : paramsList) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
                pstmt.addBatch();
            }
            //第三步，使用executeBatch()方法执行批量sql语句
            rtn = pstmt.executeBatch();
            //最后一步，提交批量sql
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                datasource.push(conn);
            }
        }

        return rtn;
    }


    //内部类：查询回调接口
    public static interface QueryCallback{
        //处理查询结果
        void process(ResultSet rs) throws Exception;
    }

}
