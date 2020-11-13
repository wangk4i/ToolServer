package com.hydimi.tool.domain.info;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/11/11 9:59
 */
@Data
public class GetMsgFileInput {
    @NotBlank(message = "文件ID不能为空")
    public String MsgFileID;

    public Integer islocal;
}
