package com.xiayuan.sparkProject.domain;

/**
 * 随机抽取session表实体类
 *
 * @author yeunsher
 * @date 2020-03-31 - 09:42
 */
public class SessionRandomExtract {
    private long taskid;
    private String sessionid;
    private String startTime;
    private String searchKeyWords;
    private String clickCategoryIds;

    public long getTaskid() {
        return taskid;
    }

    public void setTaskid(long taskid) {
        this.taskid = taskid;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getSearchKeyWords() {
        return searchKeyWords;
    }

    public void setSearchKeyWords(String searchKeyWords) {
        this.searchKeyWords = searchKeyWords;
    }

    public String getClickCategoryIds() {
        return clickCategoryIds;
    }

    public void setClickCategoryIds(String clickCategoryIds) {
        this.clickCategoryIds = clickCategoryIds;
    }

    public String toString() {
        return "SessionRandomExtract{" +
                "taskid=" + taskid +
                ", sessionid='" + sessionid + '\'' +
                ", startTime='" + startTime + '\'' +
                ", serachKeyWords='" + searchKeyWords + '\'' +
                ", clickCategoryIds='" + clickCategoryIds + '\'' +
                '}';
    }
}
