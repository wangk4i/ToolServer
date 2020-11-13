package com.hydimi.tool.domain.vo;

import lombok.Data;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/11/5 17:31
 */
@Data
public class LoginVO {

    /**
     * 用户绑定Token
     */
    private String token;

    public String UserID;
    public String UserName;
    public String OrganID;
    public String OrganName;
    public String RoleID;
    public String RoleName;

}
