package com.hydimi.tool.domain.dto;

import lombok.Data;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/21 16:27
 */
@Data
public class ViewEmerDealVO {
    public String LocalEmergencyID;
    public String EmerDealInfoId;
    public String BasicInformationId;
    public String ZoneCd;
    public String ZoneNam;
    public String OrgCode;
    public String OrgCodeValue;
    public String PatientName;
    public String IDType;
    public String IDTypeNam;
    public String IDCode;
    public String GenderCode;
    public String GenderNam;
    public String Nationality;
    public String NationalityNam;
    public String Age;
    public String EmrDealOrg;
    public String FirstDisposalSite;
    public String Reporter;
    public String ReportTime;
    public String ReportRoute;
    public String ReporterIdentity;
    public String ReporterIdentityValue;
    public String ReportingIdentityDesc;
    public String DealStartTime;
    public String DealEndTime;
    public String SceneDescription;
    public String SecurityOrganSignatory;
    public String SecurityOrganName;
    public String PreventionDoctor;
    public String PsychiatryDoctor;
    public String PsychiatryNurse;
    public String OtherName;
    public String DealReason;
    //,分隔缘由
    public String DealReasonList;
    public String DealReasonEx;
    public String DealMeasure;
    //,分隔措施
    public String DealMeasureList;
    public String DealMeasureEx;
    public String DiagnosticConfirmed;
    public String DiagnosticConfirmedValue;
    public String DiagnosticSuspected;
    public String DiagnosticSuspectedValue;
    public String HandlesEffect;
    public String HandlesEffectValue;
    public String HandlesObject;
    public String HandlesObjectValue;
    public String FillDoctor;
    public String FillDate;
    public String PatInfoCd;
    public String State;
    public String DelStatus;
    public String DelStatusValue;
    public String DelReason;
    public String DelResonValue;
    public String CreTime;
    public String LastModiCd;
    public String LastModTime;
    public String SyncStatus;
    public String SyncTime;
    public String SyncError;

}