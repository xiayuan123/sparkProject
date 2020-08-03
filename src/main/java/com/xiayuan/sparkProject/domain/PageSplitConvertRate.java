package com.xiayuan.sparkProject.domain;

/**
 * @author yeunsher
 * @date 2020-04-04 - 19:39
 */
public class PageSplitConvertRate {
    private long taskid;
    private String convertRate;

    public long getTaskid() {
        return taskid;
    }

    public void setTaskid(long taskid) {
        this.taskid = taskid;
    }

    public String getConvertRate() {
        return convertRate;
    }

    public void setConvertRate(String convertRate) {
        this.convertRate = convertRate;
    }

    @Override
    public String toString() {
        return "PageSplitConvertRate{" +
                "taskid=" + taskid +
                ", convertRate='" + convertRate + '\'' +
                '}';
    }
}
