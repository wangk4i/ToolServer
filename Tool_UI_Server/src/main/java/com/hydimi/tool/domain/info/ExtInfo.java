package com.hydimi.tool.domain.info;

import lombok.Data;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/27 15:13
 */
@Data
public class ExtInfo {
    public String OrganCode;

    public String OrganName;

    public String ZoneCode;

    public String Dep;

    public String DepNam;

    public String PsnCd;

    public String UpdateMode;

    public String ExtendStr;

    public String OperatorCd;

    public String Operator;

    public String Token;

    public String From;

    public String To;

    public String State;

    public String Status;

    public String IsExpired;

    public String EffDateTime;

    public String ExpDateTime;

    public Integer Count;

    //---------------------新增用户名
    public String LoginAccount;
}
