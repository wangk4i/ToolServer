package com.hydimi.tool.domain.info;

import lombok.Data;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/2 15:31
 */
@Data
public class RecoveryBaseInput {
    public String basicInformationId;
    public String recoveryCause;
    public String OperatorCd;
}
