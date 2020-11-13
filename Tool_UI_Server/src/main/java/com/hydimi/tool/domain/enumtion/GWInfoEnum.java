package com.hydimi.tool.domain.enumtion;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/16 17:43
 */

public class GWInfoEnum {
    public final static Map<String, String> patientMap = new LinkedHashMap<>();

    public final static Map<String, String> patientMap2 = new LinkedHashMap<>();

    public final static Map<String, String> followupMap = new LinkedHashMap<>();

    public final static Map<String, String> medicineMap = new LinkedHashMap<>();

    public final static Map<String, String> reportMap = new LinkedHashMap<>();

    public final static Map<String, String> leftcardMap = new LinkedHashMap<>();

    public final static Map<String, String> emergencyMap = new LinkedHashMap<>();


    static {
        patientMap.put("ID", "患者主键");
        patientMap.put("PatientNo", "患者编号");
        patientMap.put("ZoneCode", "管理地区");
        patientMap.put("OrgCode", "管理单位");
        patientMap.put("PatientName", "姓名");
        patientMap.put("IdCardTypeCode", "证件类别");
        patientMap.put("IdCard", "证件号码");
        patientMap.put("NationalityCode", "国籍");
        patientMap.put("GenderCode", "性别");
        patientMap.put("BirthDate", "出生日期");
        patientMap.put("Contacts", "监护人姓名");
        patientMap.put("ContactsTel", "监护人电话号码");
        patientMap.put("ContactsTelTwo", "监护人电话号码(副)");
        patientMap.put("ContactsRelCode", "监护人关系");
        patientMap.put("FamilyHistory", "两系三代家族史");
        patientMap.put("NationCode", "民族");
        patientMap.put("EducationCode", "文化程度");
        patientMap.put("ProfessionCode", "就业情况");
        patientMap.put("MaritalStatusCode", "婚姻状况");
        patientMap.put("PsychogenyEconomyLevelCode", "经济状况");
        patientMap.put("PermanentAddrTypeCode", "户籍所在地类型");
        patientMap.put("PermanentAddrCode", "户籍国标码");
        patientMap.put("PermanentAddrDetail", "户籍详细地址");
        patientMap.put("PsychogenyPermanentAddrCode", "户别");
        patientMap.put("PsychogenyPermanentAddrTypeUnknownCause", "户别不详原因");
        patientMap.put("CurrentAddrTypeCode", "现住址地区类型");
        patientMap.put("CurrentAddrCode", "现住地国标码");
        patientMap.put("CurrentAddrDetail", "现住址详细地址");
        patientMap.put("PsychogenyAddrTypeCode", "现住地类别");
        patientMap.put("DiseaseCode", "目前诊断");
        patientMap.put("DiagnoseOrg", "确诊医院");
        patientMap.put("DiagnoseOrgUnknown", "确诊医院不详标志");
        patientMap.put("DiagnoseOrgUnknownReason", "确诊医院不详原因");
        patientMap.put("DiagnoseTime", "确诊日期");
        patientMap.put("DiagnoseTimeAccuracyCode", "确诊日期精确度");
        patientMap.put("OnsetDate", "初次发病时间");
        patientMap.put("OnsetDateAccuracyCode", "初次发病时间精确度");
        patientMap.put("SignsMedicationCode", "是否已进行药物治疗");
        patientMap.put("PsychogenyFirstCureDate", "首次药物治疗时间");
        patientMap.put("PsychogenyFirstCureDateAccuracyCode", "首次治疗时间精确度");
        patientMap.put("PsychogenyPastHospital", "既往住院次数");
        patientMap.put("PsychogenyShutStatusHistoryCode", "既往关锁情况");
        patientMap.put("PsychogenyPastDangerousList", "既往危险行为");
        patientMap.put("IfSupportCode", "精准扶贫对象标志");
        patientMap.put("IfSubsidyCode", "监护补助对象标志");
        patientMap.put("ReceiveDisableList", "领取残疾人证情况");
        patientMap.put("DisabledLevelCode", "精神残疾人证等级");
        patientMap.put("IfCarefor", "关爱帮扶小组服务对象标志");
        patientMap.put("IfFamilyPhysician", "家庭医师签约服务对象标志");
        patientMap.put("IfRecoveryMeasures", "参加社区康复服务标志");
        patientMap.put("PsychogenyInformedCode", "知情同意");
        patientMap.put("PsychogenyInformedDate", "知情同意书签字时间");
        patientMap.put("DeadDate", "死亡日期 ");
        patientMap.put("DeadReason", "死亡原因");
        patientMap.put("DeadDetail", "死亡原因明细");
    }

    static {
        patientMap2.put("ID", "患者主键");
        patientMap2.put("PatientNo", "患者编号");
        patientMap2.put("ZoneCode", "管理地区");
        patientMap2.put("OrgCode", "管理单位");
        patientMap2.put("PatientName", "姓名");
        patientMap2.put("IdCardTypeCode", "证件类别");
        patientMap2.put("IdCard", "证件号码");
        patientMap2.put("NationalityCode", "国籍");
        patientMap2.put("GenderCode", "性别");
        patientMap2.put("BirthDate", "出生日期");
        patientMap2.put("Contacts", "监护人姓名");
        patientMap2.put("ContactsTel", "监护人电话号码");
        patientMap2.put("ContactsTelTwo", "监护人电话号码(副)");
        patientMap2.put("ContactsRelCode", "监护人关系");
        patientMap2.put("FamilyHistory", "两系三代家族史");
        patientMap2.put("NationCode", "民族");
        patientMap2.put("EducationCode", "文化程度");
        patientMap2.put("ProfessionCode", "就业情况");
        patientMap2.put("MaritalStatusCode", "婚姻状况");
        patientMap2.put("PsychogenyEconomyLevelCode", "经济状况");
        patientMap2.put("PermanentAddrTypeCode", "户籍所在地类型");
        patientMap2.put("PermanentAddrCode", "户籍国标码");
        patientMap2.put("PermanentAddrDetail", "户籍详细地址");
        patientMap2.put("PsychogenyPermanentAddrCode", "户别");
        patientMap2.put("PsychogenyPermanentAddrTypeUnknownCause", "户别不详原因");
        patientMap2.put("CurrentAddrTypeCode", "现住址地区类型");
        patientMap2.put("CurrentAddrCode", "现住地国标码");
        patientMap2.put("CurrentAddrDetail", "现住址详细地址");
        patientMap2.put("PsychogenyAddrTypeCode", "现住地类别");
        patientMap2.put("DiseaseCode", "目前诊断");
        patientMap2.put("DiagnoseOrg", "确诊医院");
        patientMap2.put("DiagnoseOrgUnknown", "确诊医院不详标志");
        patientMap2.put("DiagnoseOrgUnknownReason", "确诊医院不详原因");
        patientMap2.put("DiagnoseTime", "确诊日期");
        patientMap2.put("DiagnoseTimeAccuracyCode", "确诊日期精确度");
        patientMap2.put("OnsetDate", "初次发病时间");
        patientMap2.put("OnsetDateAccuracyCode", "初次发病时间精确度");
        patientMap2.put("SignsMedicationCode", "是否已进行药物治疗");
        patientMap2.put("PsychogenyFirstCureDate", "首次药物治疗时间");
        patientMap2.put("PsychogenyFirstCureDateAccuracyCode", "首次治疗时间精确度");
        patientMap2.put("PsychogenyPastHospital", "既往住院次数");
        patientMap2.put("PsychogenyShutStatusHistoryCode", "既往关锁情况");
        patientMap2.put("PsychogenyPastDangerousList", "既往危险行为");
        patientMap2.put("IfSupportCode", "精准扶贫对象标志");
        patientMap2.put("IfSubsidyCode", "监护补助对象标志");
        patientMap2.put("ReceiveDisableList", "领取残疾人证情况");
        patientMap2.put("DisabledLevelCode", "精神残疾人证等级");
        patientMap2.put("IfCarefor", "关爱帮扶小组服务对象标志");
        patientMap2.put("IfFamilyPhysician", "家庭医师签约服务对象标志");
        patientMap2.put("IfRecoveryMeasures", "参加社区康复服务标志");
        patientMap2.put("PsychogenyInformedCode", "知情同意");
        patientMap2.put("PsychogenyInformedDate", "知情同意书签字时间");
    }

    static {
        followupMap.put("ID","随访信息主键");
        followupMap.put("PersonID","患者主键");
        followupMap.put("FollowupDate","随访日期");
        followupMap.put("LostReasonCode","失访原因");
        followupMap.put("LostReasonOther","失访原因其他说明");
        followupMap.put("HospitalStateCode","住院情况");
        followupMap.put("LastOutHospitalDate","末次出院时间");
        followupMap.put("LastOutHospitalDateAccuracyCode","末次出院时间精确度");
        followupMap.put("FollowupModeCode","本次随访形式");
        followupMap.put("FollowObjectList","本次随访对象");
        followupMap.put("ShutStatusCode","随访期间关锁情况");
        followupMap.put("DiseaseStatusCode","随访病情分类");
        followupMap.put("PsychogenyRiskCode","危险性评估");
        followupMap.put("SymptomsList","目前症状列表");
        followupMap.put("SymptomsOther","目前症状其他说明");
        followupMap.put("InsightConditionCode","自知力");
        followupMap.put("SleepConditionCode","睡眠情况");
        followupMap.put("DietConditionCode","饮食情况");
        followupMap.put("LifeConditionCode","个人生活料理");
        followupMap.put("MenageConditionCode","家务劳动");
        followupMap.put("WorkConditionCode","生产劳动及工作");
        followupMap.put("StudyConditionCode","学习能力");
        followupMap.put("ContactConditionCode","社会人际交往");
        followupMap.put("MildDisturbancesNumber","轻度滋事(次)");
        followupMap.put("TroubleNumber","肇事(次)");
        followupMap.put("AccidentNumber","肇祸(次)");
        followupMap.put("HarmNumber","其他危害他人行为(次)");
        followupMap.put("AutolesionNumber","自伤(次)");
        followupMap.put("SuicideFailNumber","自杀未遂(次)");
        followupMap.put("DrugComplianceCode","用药依从性");
        followupMap.put("AdverseDrugReactionCode","药物不良反应");
        followupMap.put("TreatmentEffectCode","治疗效果");
        followupMap.put("IfRecipe","是否有精神科药物处方");
        followupMap.put("IfLaboratoryExam","有否进行实验室检查");
        followupMap.put("LaboratorySpecificList","实验室检查具体项");
        followupMap.put("SuggestReferal","建议转诊");
        followupMap.put("CauseReferal","建议转诊原因");
        followupMap.put("IfReferral","是否已转诊");
        followupMap.put("ReferralOrg","转至机构名称");
        followupMap.put("IfCaseManagement","患者个案管理的情况");
        followupMap.put("CaseAssess","个案管理病情评估");
        followupMap.put("CaseSocialEvaluation","个案管理社会功能总评情况");
        followupMap.put("RecoveryMeasuresList","康复措施列表");
        followupMap.put("RecoveryMeasuresOther","康复措施其他说明");
    }

    static {
        medicineMap.put("DrugID","药品主键");
        medicineMap.put("Sort","排序号");
        medicineMap.put("LongActingDrugFlagCode","长效药物标志");
        medicineMap.put("DrugCode","药品代码");
        medicineMap.put("DrugSpecifications","药物规格");
        medicineMap.put("DrugDoseCode","剂量单位");
        medicineMap.put("LongActingDrugFrequency","长效药物用药频率");
        medicineMap.put("LongActingDrugFrequencyUnitCode","长效药物频率单位");
        medicineMap.put("LongActingDrugDose","长效药物服用次剂量");
        medicineMap.put("NonLongActingDrugDoseMorning","非长效药物晨间服用次剂量");
        medicineMap.put("NonLongActingDrugDoseNoon","非长效药物午间服用次剂量");
        medicineMap.put("NonLongActingDrugDoseEvening","非长效药物晚间服用次剂量");
    }

    static {
        reportMap.put("ID","报告卡主键");
        reportMap.put("PersonID","患者主键");
        reportMap.put("ReportZoneCode","报告地区");
        reportMap.put("ReportOrgCode","报告单位");
        reportMap.put("PatientRegistrationNumber","报告卡编号");
        reportMap.put("BaseinfoSource","患者来源");
        reportMap.put("PatientName","姓名");
        reportMap.put("GenderCode","性别");
        reportMap.put("IdCardTypeCode","证件类型");
        reportMap.put("IdCard","证件号码");
        reportMap.put("NationalityCode","国籍");
        reportMap.put("BirthDate","出生日期");
        reportMap.put("NationCode","民族");
        reportMap.put("Contacts","监护人姓名");
        reportMap.put("ContactsTel","监护人电话");
        reportMap.put("ContactsTelTwo","监护人电话(副)");
        reportMap.put("ContactsRelCode","监护人与患者关系");
        reportMap.put("PermanentAddrTypeCode","户籍所在地类型");
        reportMap.put("PermanentAddrCode","户籍国标码");
        reportMap.put("PermanentAddrDetail","户籍详细地址");
        reportMap.put("PsychogenyPermanentAddrCode","户别");
        reportMap.put("PermanentAddrTypeUnknownCause","户别不详原因");
        reportMap.put("CurrentAddrTypeCode","现住址地区类型(病人属于)");
        reportMap.put("CurrentAddrCode","现住地国标码");
        reportMap.put("CurrentAddrDetail","现住址详细地址");
        reportMap.put("MaritalStatusCode","婚姻状况");
        reportMap.put("EducationCode","文化程度");
        reportMap.put("ProfessionCode","就业情况(职业)");
        reportMap.put("FamilyHistory","两系三代精神疾病家族史");
        reportMap.put("OnsetDate","初次发病时间");
        reportMap.put("OnsetDateAccuracyCode","初次发病时间精确度");
        reportMap.put("SignsMedicationCode","是否已进行精神科药物治疗");
        reportMap.put("PsychogenyFirstCureDate","首次精神科药物治疗时间");
        reportMap.put("PsychogenyFirstCureDateAccuracyCode","首次抗精神病药治疗时间精确度");
        reportMap.put("PsychogenyPastHospital","既往住院次数");
        reportMap.put("PsychogenyShutStatusHistoryCode","既往关锁情况");
        reportMap.put("PsychogenyPastDangerousList","既往危险行为");
        reportMap.put("PsychogenyPastRiskCode","既往危险性评估");
        reportMap.put("PsychogenySendDiagnosisList","送诊主体列表");
        reportMap.put("PsychogenySendDiagnosisOther","送诊主体其他说明");
        reportMap.put("DiseaseCode","确诊医院");
        reportMap.put("DiagnoseOrg","确诊日期");
        reportMap.put("DiagnoseTime","疾病名称");
        reportMap.put("IfNoMedication","勿需用药标志");
        reportMap.put("PsychogenyInformedCode","知情同意");
        reportMap.put("PsychogenyInformedDate","知情同意书签字时间");
        reportMap.put("FillDoctor","填卡医师");
        reportMap.put("FillDate","填卡日期");
        reportMap.put("Department","报告单位及科室");
        reportMap.put("DepartmentPhone","科室联系电话");

    }

    static {
        leftcardMap.put("ID","出院单主键");
        leftcardMap.put("ReportID","报告卡主键");
        leftcardMap.put("PersonID","患者主键");
        leftcardMap.put("ReportZoneCode","报告地区");
        leftcardMap.put("ReportOrgCode","报告单位");
        leftcardMap.put("PatientRegistrationNumber","报告卡编号");
        leftcardMap.put("InhospitalNo","病案号");
        leftcardMap.put("PatientName","姓名");
        leftcardMap.put("IdCardTypeCode","证件类型");
        leftcardMap.put("IdCard","证件号码");
        leftcardMap.put("GenderCode","性别");
        leftcardMap.put("NationalityCode","国籍");
        leftcardMap.put("BirthDate","出生日期");
        leftcardMap.put("NationCode","民族");
        leftcardMap.put("Age","年龄");
        leftcardMap.put("AdmissionDate","入院日期");
        leftcardMap.put("DischargeDate","出院日期");
        leftcardMap.put("PsychogenyPastHospital","既往住院次数");
        leftcardMap.put("DiseaseCode","出院诊断");
        leftcardMap.put("DiagnoseTime","确诊日期");
        leftcardMap.put("PsychogenyPastDangerousList","既往危险行为");
        leftcardMap.put("PsychogenyPastRiskCode","既往危险性评估");
        leftcardMap.put("TreatmentEffectCode","住院疗效");
        leftcardMap.put("IfNoMedication","目前用药勿需用药");
        leftcardMap.put("GuideIfNoMedication","用药指导勿需用药");
        leftcardMap.put("NextMeasuresList","康复措施");
        leftcardMap.put("NextMeasuresCodeOther","康复措施其他注明");
        leftcardMap.put("NextMeasuresOther","其他注意事项");
        leftcardMap.put("DoctorName","经治医师");
        leftcardMap.put("DoctorPhone","联系电话");
        leftcardMap.put("FillDate","填表日期");
    }

    static {
        emergencyMap.put("ID","应急处置主键：");
        emergencyMap.put("ReportZoneCode","报告地区：");
        emergencyMap.put("ReportOrgCode","报告单位：");
        emergencyMap.put("EmergencyOrg","处置单位：");
        emergencyMap.put("PatientName","姓名：");
        emergencyMap.put("IdCardTypeCode","证件类型");
        emergencyMap.put("IdCard","证件号码");
        emergencyMap.put("GenderCode","性别");
        emergencyMap.put("NationalityCode","国籍");
        emergencyMap.put("Age","年龄");
        emergencyMap.put("FirstDisposalSite","第一处置地点");
        emergencyMap.put("Reporter","报告人");
        emergencyMap.put("ReportingTime","报告时间");
        emergencyMap.put("ReportingRoute","报告途径");
        emergencyMap.put("ReportingIdentityCode","报告人身份");
        emergencyMap.put("ReportingIdentityDesc","报告人身份其他说明");
        emergencyMap.put("DisposalStartTime","处置开始时间");
        emergencyMap.put("DisposalEndTime","处置结束时间");
        emergencyMap.put("SceneDescription","现场情况描述");
        emergencyMap.put("SecurityOrganSignatory","公安机关人员");
        emergencyMap.put("SecurityOrganName","公安机关单位名称");
        emergencyMap.put("PreventionDoctor","精防医生");
        emergencyMap.put("PsychiatryDoctor","精神科医师");
        emergencyMap.put("PsychiatryNurse","精神科护士");
        emergencyMap.put("OtherName","其他人员");
        emergencyMap.put("DisposalReasonList","处置缘由");
        emergencyMap.put("DisposalReasonOther","处置缘由其他说明");
        emergencyMap.put("DisposalMeasuresList","主要处置措施");
        emergencyMap.put("DisposalMeasuresOther","处置措施其他说明");
        emergencyMap.put("DiagnosticConfirmedCode","确定诊断编码");
        emergencyMap.put("DiagnosticSuspectedCode","疑似诊断编码");
        emergencyMap.put("HandlesEffectCode","处置效果");
        emergencyMap.put("HandlesObjectCode","处置对象类别");
        emergencyMap.put("FillDoctor","填表人");
        emergencyMap.put("FillDate","填表日期");
    }
}
