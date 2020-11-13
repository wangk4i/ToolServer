package com.hydimi.tool.config;

import org.springframework.stereotype.Component;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/11/12 15:56
 */
@Component
public class FolderConfig {
    public static String build;
    public static String sending;
    public static String waiting;
    public static String receive;
    public static String done;
    public static String MessageView;
    public static String Backup;
    public static String WaitReturn;
    public static String Finish;
    public static String Save;

    public void setBuild(String build) {
        FolderConfig.build = build;
    }

    public void setSending(String sending) {
        FolderConfig.sending = sending;
    }

    public void setWaiting(String waiting) {
        FolderConfig.waiting = waiting;
    }

    public void setReceive(String receive) {
        FolderConfig.receive = receive;
    }

    public void setDone(String done) {
        FolderConfig.done = done;
    }

    public void setMessageView(String messageView) {
        MessageView = messageView;
    }

    public void setBackup(String backup) {
        Backup = backup;
    }

    public void setWaitReturn(String waitReturn) {
        WaitReturn = waitReturn;
    }

    public void setFinish(String finish) {
        Finish = finish;
    }

    public void setSave(String save) {
        Save = save;
    }
}
