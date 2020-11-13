package com.hydimi.tool.domain.vo;

import lombok.Data;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/11/13 9:38
 */
@Data
public class MsgOverviewVO {
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

}
