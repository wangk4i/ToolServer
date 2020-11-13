package com.hyd.storage.domain.info;

import lombok.Data;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/10/29 18:45
 */
@Data
public class MessageInfo {

    public String id;

    public String zone;

    public String organ;

    public Integer msgcate;

    public Integer msgtype;

    public Integer msgaction;

    public Integer gwaction;

    public Integer special;

    public Integer islocal;

    public Integer origin;

    public String fileNam;

    public String fileTime;

    public String gwReceiveTime;

    public String writeTime;

    public MessageDataInfo extensionInfo;
}