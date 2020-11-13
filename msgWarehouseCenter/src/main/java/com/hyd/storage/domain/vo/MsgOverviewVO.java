package com.hyd.storage.domain.vo;

import lombok.Data;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/11/13 10:00
 */
@Data
public class MsgOverviewVO {
    public boolean existMsgFile;
    public Integer buildNum;
    public Integer sendingNum;
    public Integer waitingNum;
    public Integer receiveNum;
    public Integer doneNum;
    public Integer messageViewNum;
    public Integer backupNum;
    public Integer p51boxNum;
    public Integer waitReturnNum;
    public Integer finishNum;
    public String earliestBuildTime;
    public String earliestDelay;
    public String latestBuildTime;
    public String latestDelay;

    public MsgOverviewVO() {
        this.buildNum = 0;
        this.sendingNum = 0;
        this.waitingNum = 0;
        this.receiveNum = 0;
        this.doneNum = 0;
        this.messageViewNum = 0;
        this.backupNum = 0;
        this.p51boxNum = 0;
        this.waitReturnNum = 0;
        this.finishNum = 0;
        this.earliestBuildTime = "";
        this.earliestDelay = "";
        this.latestBuildTime = "";
        this.latestDelay = "";
    }
}