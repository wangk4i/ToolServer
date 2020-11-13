package com.hydimi.tool.domain.dto;

import lombok.Data;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/27 11:51
 */
@Data
public class SPMZoneDTO {
    // 操作状态
    public String Oper;

    // 编号
    public String Cd;

    // 1@地区全称
    public String AllNam;

    // 名称
    public String Nam;

    // 2@父地区编号
    public String ParCd;

    // 3@父地区名称
    public String ParNam;

    // 4@地区编码
    public String Code;
    public String PYName;

    public String IsCityAuthorize;

    public String haveChild;

    public String id;

    public String LevCd;

    // 5@地区级别编码 Nam
    public String LevCdNam;

    // 6@地区级别名称
    public String LevNam;

    // 创建人编号
    public String CreaCd;

    // 创建时间
    public String CreTime;

    // 最后一次修改人编号
    public String LastModiCd;

    // 最后一次修改时间
    public String LastModTime;

    // 数据所属机构编号
    public String OrgCd;

    // 数据状态
    public String State;
}
