package com.hydimi.tool.domain.info;

import lombok.Data;

import java.util.List;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/10/19 16:46
 */
@Data
public class PatInfoBatchResendInput {
    public List<PatInfoResendInput> PatInfoList;
    public String OperatorCd;

}
