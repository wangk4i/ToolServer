package com.hydimi.tool.domain.info;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/11/9 16:42
 */
@Data
public class TrackMsgInput {
    @NotNull(message = "消息类型不能为空")
    public Integer MsgType;
    @NotBlank(message = "消息ID不能为空")
    public String MsgID;
}
