package com.hydimi.tool.domain.info;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/11/12 15:04
 */
@Data
public class MsgLocationInput {
    @NotBlank(message = "消息主键不能为空")
    public String MsgId;
    public String FileId;
    public String MsgType;
    public String BuildTime;

}
