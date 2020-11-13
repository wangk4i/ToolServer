package com.hydimi.tool.domain.dto;

import lombok.Data;

@Data
public class RepeatPersonDTO {
    /**
     *患者主键
     */
    private String ID;

    /**
     *患者编号  PersonNo
     */
    private String PatientNo;

    /**
     *管理地区代码
     */
    private String ZoneCode;

    /**
     *管理机构代码
     */
    private String OrgCode;

    /**
     *患者姓名
     */
    private String PatientName;

    /**
     *身份证件类别代码
     */
    private String IdCardTypeCode;

    /**
     *身份证件号
     */
    private String IdCard;
    /**
     *性别代码
     */
    private String GenderCode;

    /**
     *出生日期
     */
    private String BirthDate;

    /**
     *现住详细地址
     */
    private String CurrentAddrDetail;

    /**
     *现住地区代码
     */
    private String CurrentAddrCode;

    /**
     *疾病诊断代码
     */
    private String DiseaseCode;

    /**
     * 患者所在机构直报的联系方式
     */
    private String ContactInformation;

}
