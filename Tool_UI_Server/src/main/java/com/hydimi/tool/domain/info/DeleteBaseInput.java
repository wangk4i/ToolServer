package com.hydimi.tool.domain.info;

import lombok.Data;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/31 18:48
 */
@Data
public class DeleteBaseInput {
    public String basicInformationId;
    public String deleteCause;
    public String undeleteCause;

    public String OperatorCd;

}
