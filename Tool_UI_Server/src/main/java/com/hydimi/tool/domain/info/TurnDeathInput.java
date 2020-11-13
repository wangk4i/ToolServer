package com.hydimi.tool.domain.info;

import lombok.Data;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/4 15:53
 */
@Data
public class TurnDeathInput {
    public String PatInfoCd;
    public String ZoneCd;
    public String OrganCd;
    public String causeOfDeath;
    public String dateOfDeath;
    public String OperatorCd;
}
