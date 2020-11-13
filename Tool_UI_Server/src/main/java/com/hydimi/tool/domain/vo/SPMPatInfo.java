package com.hydimi.tool.domain.vo;

import lombok.Data;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/27 11:58
 */
@Data
public class SPMPatInfo {

    ///既往住院情况—曾住精神专科医院/综合医院精神科次数
    public String InMentalHos;

    ///是否为家庭医师签约服务对象
    public String IfFamilyphysician;

    ///患者基本信息的主键
    public String FIELDPK;

    ///管理地区
    public String ZoneCd;

    ///管理地区
    public String ZoneNam;

    ///管理单位
    public String OrganCd;

    ///管理单位
    public String OrgNam;

    ///患者编号
    public String PatCode;

    ///患者姓名
    public String PatNam;

    ///证件类型
    public String IDTypeCd;

    ///证件号码
    public String IDCode;

    ///国籍
    public String NationalityCd;

    ///性别
    public String GenderCd;

    ///出生日期
    public String BirthDate;

    ///监护人姓名
    public String GuardianNam;

    ///监护人电话
    public String GuardianTel;

    ///监护人与患者关系
    public String RelToPatient;

    ///两系三代精神疾病家族史
    public String FamHistoryCd;

    ///婚姻状况
    public String MarriageCd;

    ///民族
    public String NationCd;

    ///就业情况(职业)
    public String OccupationCd;

    ///文化程度
    public String EducationCd;

    ///现住地类别
    public String CurrentAddressType;

    ///现住址
    public String LAddress;

    ///病人属于
    public String LAddrTpCd;

    ///现住地国标码
    public String LTownCd;

    ///户籍详细地址
    public String DAddress;

    ///户籍地址类型
    public String DAddrTpCd;

    ///户籍国标码
    public String DTownCd;

    ///户别
    public String RegistertypeCd;

    ///户别不详原因
    public String UnknownCause;

    ///目前诊断ICD10编码
    public String DiagICD;

    ///确诊医院
    public String DiagnosisHos;

    /////确诊医院不详
    //public string UnknownHospita { get; set; }
    /////<summary>
    /////确诊医院不详原因
    /////</summary>
    //public string UnknownReason { get; set; }

    ///确诊日期
    public String DiagnosisDate;

    ///确诊日期精确度
    public String DiagAccuracyCd;

    ///初次发病时间
    public String FirstOnsetDate;

    ///初次发病时间精确度
    public String AccuracyCd;

    ///是否已进行抗精神病药物治疗
    public String IfTreatCd;

    ///首次抗精神病药治疗时间
    public String FirstMedTime;

    ///首次抗精神病药治疗时间精确度
    public String MedAccuracyCd;
    ///<summary>
    ///既往住院情况—曾住精神专科医院/综合医院精神科次数
    ///

    ///既往关锁情况
    public String InCaptivity;

    ///既往危险行为
    public String RiskActCd;

    ///家庭经济状况
    public String FamEcoCd;

    ///知情同意
    public String InformedConsCd;

    ///知情同意书签字时间
    public String ICSignDate;

    ///死亡日期
    public String DeathDate;

    ///死亡原因编号

    public String DeathCauseCd;


    public String DeathCause;

    ///死亡原因明细编号
    public String DeadDetailCd;

    ///死亡原因明细
    public String DeadDetail;


    public String BaseStatus;

    ///是否为精准扶贫对象
    public String IfPoverty;

    ///是否为监护补助对象
    public String IfSubsidies;

    ///领取残疾人证情况
    public String DisabilitycardInfo;

    ///精神残疾人证等级
    public String DisabilityGrade;

    ///是否为关爱帮扶小组对象
    public String IfSupportgroup;

    ///是否参加社区康复服务
    public String IfRecovery;

    public String BaseStatusCd;
    public String DelStatus;
    public String Cd;
    public String State;
    public String CreTime;
    public String CreaCd;
    public String SyncStatus;
    public String SyncTime;
    public String SyncError;
}
