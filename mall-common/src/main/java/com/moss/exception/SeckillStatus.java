package com.moss.exception;

public enum SeckillStatus {

    NOT_STARTED(1, "未开始"),
    ON_GOING(2, "进行中"),
    COMPLETED(3, "已结束");

    private int  statusId;
    private String statusDesc;

    SeckillStatus(int statusId, String statusDesc) {
        this.statusId = statusId;
        this.statusDesc = statusDesc;
    }

    public String getStatusDesc(){
        return statusDesc;
    }
}
