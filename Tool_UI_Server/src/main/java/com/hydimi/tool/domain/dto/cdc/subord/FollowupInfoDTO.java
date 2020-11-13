package com.hydimi.tool.domain.dto.cdc.subord;

import com.hydimi.tool.domain.dto.cdc.subord.code.*;
import lombok.Data;

import java.util.List;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/9 16:03
 */
@Data
public class FollowupInfoDTO {
    public String ID;

    public String PersonID;

    public String FollowupDate;

    public String LostReasonCode;

    public String LostReasonOther;

    public String HospitalStateCode;

    public String LastOutHospitalDate;

    public String LastOutHospitalDateAccuracyCode;

    public String FollowupModeCode;

    public List<FollowObjectCodeDTO> FollowObjectCodeList;
    //随访对象列表 ","连接
    public String FollowObjectList;

    public String ShutStatusCode;

    public String DiseaseStatusCode;

    public String PsychogenyRiskCode;

    public List<SymptomsCodeDTO> SymptomsCodeList;
    //目前症状列表 ","连接
    public String SymptomsList;

    public String SymptomsOther;

    public String InsightConditionCode;

    public String SleepConditionCode;

    public String DietConditionCode;

    public String LifeConditionCode;

    public String MenageConditionCode;

    public String WorkConditionCode;

    public String StudyConditionCode;

    public String ContactConditionCode;

    public String MildDisturbancesNumber;

    public String TroubleNumber;

    public String AccidentNumber;

    public String HarmNumber;

    public String AutolesionNumber;

    public String SuicideFailNumber;

    public String DrugComplianceCode;

    public String AdverseDrugReactionCode;

    public String TreatmentEffectCode;

    public List<DrugDTO> DrugList;

    public String IfRecipe;

    public List<DrugDTO> GuideDrugList;

    public String IfLaboratoryExam;

    public List<LaboratorySpecificCodeDTO> LaboratorySpecificCodeList;
    //实验室检查列表 ","连接
    public String LaboratorySpecificList;

    public String SuggestReferal;

    public String CauseReferal;

    public String IfReferral;

    public String ReferralOrg;

    public String IfCaseManagement;

    public String CaseAssess;

    public String CaseSocialEvaluation;

    public List<RecoveryMeasuresCodeDTO> RecoveryMeasuresCodeList;
    //康复措施列表 ","连接
    public String RecoveryMeasuresList;

    public String RecoveryMeasuresOther;
}
