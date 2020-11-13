package com.hyd.storage.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/10/29 18:34
 */
@Component
public class FolderPath {

    // 处理完成文件夹
    public static String done;

    // 入库存储文件夹
    public static String MessageView;

    public static String build;

    public static String sending;

    public static String waiting;

    public static String receive;

    public static String MsgOverviewUrl;

    @Value("${config.filePath.done}")
    public void setDone(String done) {
        FolderPath.done = done;
    }

    @Value("${config.filePath.messageView}")
    public void setMessageView(String messageView) {
        MessageView = messageView;
    }

    @Value("${config.filePath.build}")
    public void setBuild(String build) {
        FolderPath.build = build;
    }

    @Value("${config.filePath.sending}")
    public void setSending(String sending) {
        FolderPath.sending = sending;
    }

    @Value("${config.filePath.waiting}")
    public void setWaiting(String waiting) {
        FolderPath.waiting = waiting;
    }

    @Value("${config.filePath.receive}")
    public void setReceive(String receive) {
        FolderPath.receive = receive;
    }

    @Value("${config.MsgOverviewUrl}")
    public void setMsgOverviewUrl(String msgOverviewUrl) {
        MsgOverviewUrl = msgOverviewUrl;
    }
}
