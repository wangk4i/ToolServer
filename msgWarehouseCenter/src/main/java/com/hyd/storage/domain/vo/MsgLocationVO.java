package com.hyd.storage.domain.vo;

import lombok.Data;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/11/12 18:02
 */
@Data
public class MsgLocationVO {
    public boolean hasMsgFile;
    public String MsgFileID;
    public String BuildTime;
    public String delay;  //HH:mm:ss
    public String runningState;

    public MsgLocationVO() {
        this.hasMsgFile = false;
        this.MsgFileID = "";
        this.BuildTime = "";
        this.delay = "";
        this.runningState = "";
    }
}
