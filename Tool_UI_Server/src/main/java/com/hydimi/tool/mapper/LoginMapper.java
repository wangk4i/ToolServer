package com.hydimi.tool.mapper;

import com.hydimi.tool.domain.vo.LoginVO;
import com.hydimi.tool.domain.info.LoginInfo;
import org.apache.ibatis.annotations.*;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/11/5 17:38
 */
@Mapper
public interface LoginMapper {

    @Select("select top 100 u.Cd AS UserID,u.Nam AS UserName,u.OrganCd AS OrganID, u.OrgNam AS OrganName,ur.RoleCd AS RoleID,r.Nam AS RoleName  from SPM_SPMUser (nolock) u\n" +
            "left join SPM_SPMUserRole (nolock) ur on ur.UserCd = u.Cd\n" +
            "left join SPM_SPMRole (nolock) r on r.Cd=ur.RoleCd\n" +
            "where (RoleCd='Role000' or RoleCd='Role001' or RoleCd='Role002' or RoleCd='Role003') \n" +
            "and UserStatus='未过期' and ActiveStatus='可用' and u.State=1 and ZoneCd is not null and OrganCd is not null " +
            "and u.LoginAccount=#{input.LoginAccount} and u.Pwd=#{input.LoginPass} ")
    LoginVO userQuery(@Param("input") LoginInfo input);

}
