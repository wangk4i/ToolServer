package com.hydimi.tool.domain.info;

import lombok.Data;

import java.util.List;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/10/27 17:32
 */
@Data
public class BatchResendInput {
    public List<SingleResendInput> InfoList;
    public String OperatorCd;
}
