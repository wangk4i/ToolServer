package com.hyd.storage.domain.enumtion;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/11/12 19:02
 */
public enum MsgSituationEnum {

    Build("交换文件已生成")
    ,Sending("交换文件等待发送")
    ,Waiting("等待国网交换结果")
    ,Receive("收到国网交换结果")
    ,Done("交换文件处理完成")
    ,Store("交换消息入库完成")
    ;

    private final String locate;

    MsgSituationEnum(String locate) {
        this.locate = locate;
    }

    public String getLocate() {
        return locate;
    }
}
