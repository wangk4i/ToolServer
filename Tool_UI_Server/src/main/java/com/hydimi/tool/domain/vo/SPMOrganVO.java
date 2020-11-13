package com.hydimi.tool.domain.vo;

import lombok.Data;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/3 16:05
 */
@Data
public class SPMOrganVO {
    // 当前状态
    public String Oper;

    // 编号
    public String Cd;

    // 1@机构编码
    public String OrgCode;

    // 名称
    public String Nam;

    // 2@机构类型编码
    public String OrgTpCd;

    // 2@机构类型编码 Nam
    public String OrgTpCdNam;

    // 3@机构类型名称
    public String OrgTpNam;

    // 4@所属地区编码
    public String ZoneCd;

    // 5@所属地区代码
    public String ZoneCode;

    // 6@所属地区名称
    public String ZoneNam;

    // 7@主办单位编码
    public String HostCd;

    // 8@主办单位名称
    public String HostNam;

    // 9@经济类型编码
    public String EcoTpCd;

    // 9@经济类型编码 Nam
    public String EcoTpCdNam;

    // 10@经济类型名称
    public String EcoTpNam;

    // 11@管理类型编码
    public String ManaTpCd;

    // 11@管理类型编码 Nam
    public String ManaTpCdNam;

    // 12@管理类型名称
    public String ManaTpNam;

    // 13@机构级别编码
    public String LevCd;

    // 13@机构级别编码 Nam
    public String LevCdNam;

    // 14@机构级别名称
    public String LevNam;

    // 15@是否区域所属疾控中心
    public String IsCenter;

    // 16@机构地址
    public String Address;

    // 17@机构电话
    public String Phone;

    // 18@创建单位
    public String CreOrg;

    // 19@创建人
    public String CreUser;

    /// 创建人编号
    public String CreaCd;

    /// 创建时间
    public String CreTime;

    /// 最后一次修改人编号
    public String LastModiCd;

    /// 最后一次修改时间
    public String LastModTime;

    /// 数据状态
    public String State;

    //
    public String IfPerform;

    //
    public String IfDirect;

    //
    public String TownsCd;

    //
    public String Towns;
}
