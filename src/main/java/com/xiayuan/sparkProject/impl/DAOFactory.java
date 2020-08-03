package com.xiayuan.sparkProject.impl;

import com.xiayuan.sparkProject.dao.*;

/**
 * @author yeunsher
 * @date 2020-03-27 - 10:20
 */
public class DAOFactory {
    //获取任务管理DAO
    public static ITaskDAO getTaskDAO() {
        return new TaskDAOImpl();
    }

    //获取session聚合统计DAO
    public static ISessionAggrStatDAO getSessionAggrStatDAO() {
        return new SessionAggrStatDAOImpl();
    }

    //获取session随机抽取DAO
    public static ISessionRandomExtractDAO getSessionRandomExtractDAO() {
        return new SessionRandomExtractDAOImpl();
    }

    //获取session信息DAO
    public static ISessionDetailDAO getSessionDetailDAO() {
        return new SessionDetailImpl();
    }

    //获取top10品类管理DAO
    public static ITop10CategoryDAO getTop10CategoryDAO() {
        return new Top10CategoryDAOImpl();
    }

    //获取top10活跃session管理DAO
    public static ITop10SessionDAO getTop10SessionDAO() {
        return new Top10SessionDAOImpl();
    }

    //获取页面切片转化率管理DAO
    public static IPageSplitConvertRateDAO getPageSplitConvertRateDAO() {
        return new PageSplitConvertRateDAOImpl();
    }

    //获取top3热门商品管理DAO
    public static IAreaTop3ProductDAO getAreaTop3ProductDAO() {
        return new AreaTop3ProductDAOImpl();
    }

    //获取广告用户点击量管理DAO
    public static IAdUserClickCountDAO getAdUserClickCountDAO(){
        return new AdUserClickCountDAOImpl();
    }

    //广告黑名单信息
    public static IAdBlacklistDAO getAdBlacklistDAO(){
        return new AdBlacklistDAOImpl();
    }

    //广告实时点击管理DAO
    public static IAdStatDAO getAdStatDAO(){
        return new AdStatDAOImpl();
    }

    //各省份热门广告top3管理DAO
    public static IAdProvinceTop3DAO getAdProvinceTop3DAO(){
        return new AdProvinceTop3DAOImpl();
    }

    //一小时广告点击趋势管理DAO
    public static IAdClickTrendDAO getAdClickTrendDAO(){
        return new AdClickTrendDAOImpl();
    }
}
