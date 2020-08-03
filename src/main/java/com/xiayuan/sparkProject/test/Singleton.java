package com.xiayuan.sparkProject.test;

/**
 * 单例设计模式。
 * 在整个程序运行期间，只有一个实例，任何外界代码都不能随意创建实例
 *
 * @author yeunsher
 * @date 2020-03-26 - 10:26
 */
public class Singleton {

    //首先有一个私有静态变量引用自己将被创建出来的单例
    private static Singleton instance = null;

    //其次对自己构造方法私有化
    private Singleton(){}

    //最后需要一个公有的静态方法负责创建唯一实例，并且返回这个唯一实例
    //必须考虑到多线程并发访问安全的控制
    public static Singleton getInstance() {
        //两步检查机制，第一步，多个线程过来的时候判断instance是否为null
        //如果为null再往下走
        if (instance == null) {
//            这里进行多线程的同步，同一时间只有一个线程能够获取到Singleton
//            对象的锁来进行后续代码，其他线程只能等待锁
            synchronized (Singleton.class) {
                //只有第一个获取到锁的线程，进入到这里会发现instance是null
                //然后才会去创建这个单例
                //此后，线程哪怕是走到了这一步，也会发现instance已经不是null了
                //就不会反复创建一个单例
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

}
