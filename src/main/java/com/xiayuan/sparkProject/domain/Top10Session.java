package com.xiayuan.sparkProject.domain;

/**
 * @author yeunsher
 * @date 2020-04-02 - 10:37
 */
public class Top10Session {
    private long taskid;
    private long categoryid;
    private String sessionid;
    private long clickCount;

    public long getTaskid() {
        return taskid;
    }

    public void setTaskid(long taskid) {
        this.taskid = taskid;
    }

    public long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(long categoryid) {
        this.categoryid = categoryid;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public long getClickCount() {
        return clickCount;
    }

    public void setClickCount(long clickCount) {
        this.clickCount = clickCount;
    }

    @Override
    public String toString() {
        return "Top10Session{" +
                "taskid=" + taskid +
                ", categoryid=" + categoryid +
                ", sessionid='" + sessionid + '\'' +
                ", clickCount=" + clickCount +
                '}';
    }
}
