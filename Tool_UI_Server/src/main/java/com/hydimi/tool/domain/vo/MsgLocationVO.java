package com.hydimi.tool.domain.vo;

import lombok.Data;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/11/12 20:22
 */
@Data
public class MsgLocationVO {
    public boolean hasMsgFile;
    public String hasMsgFileValue;
    public String MsgFileID;
    public String BuildTime;
    public String delay;  //HH:mm:ss
    public String runningState;
}
