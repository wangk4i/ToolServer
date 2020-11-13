package com.hydimi.tool.domain.info;

import lombok.Data;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/10/20 10:08
 */
@Data
public class PatInfoResendInput {
    public String Cd;
    public String ZoneCd;
    public String OrganCd;
    public String OperatorCd;

    public PatInfoResendInput() {
    }

    public PatInfoResendInput(String cd, String zoneCd, String organCd, String operatorCd) {
        this.Cd = cd;
        this.ZoneCd = zoneCd;
        this.OrganCd = organCd;
        this.OperatorCd = operatorCd;
    }
}
