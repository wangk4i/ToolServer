package com.hydimi.tool.domain.info;

import lombok.Data;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/1 14:05
 */
@Data
public class MessageInfo {
    public String id;

    public String zone;

    public String organ;

    public Integer msgcate;

    public Integer msgtype;

    public Integer msgaction;

    //国网有 本地无的特殊处理
    public Integer special;

    //是否Tool工具发出的信息
    public Integer origin;

    public MessageDataInfo extensionInfo;

    public MessageInfo() {
    }

    public MessageInfo(String id, String zone, String organ, Integer msgcate, Integer msgtype, Integer msgaction) {
        this.id = id;
        this.zone = zone;
        this.organ = organ;
        this.msgcate = msgcate;
        this.msgtype = msgtype;
        this.msgaction = msgaction;
        this.extensionInfo = new MessageDataInfo();
        this.origin = 1;
    }

}
