package com.hydimi.tool.domain.info;

import lombok.Data;

import java.util.List;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/10/22 14:39
 */
@Data
public class FollowupBatchResendInput {
    public List<FollowupResendInput> FollowupInfoList;
    public String OperatorCd;
}
