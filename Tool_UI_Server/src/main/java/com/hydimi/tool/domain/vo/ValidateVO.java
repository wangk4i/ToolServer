package com.hydimi.tool.domain.vo;

import lombok.Data;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/28 18:54
 */
@Data
public class ValidateVO {
    private boolean isError;
    private String errMessage;
}
