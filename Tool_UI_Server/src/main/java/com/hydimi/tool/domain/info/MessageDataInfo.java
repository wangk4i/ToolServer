package com.hydimi.tool.domain.info;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/1 14:05
 */
@Data
public class MessageDataInfo {
    private Integer cate;

    private String zone;

    private String organ;

    private String patid;

    private String reportid;

    private String starttime;

    private String operator;

    public MessageDataInfo() {
        this.starttime = LocalDateTime.now(ZoneOffset.of("+8"))
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
    }
}
