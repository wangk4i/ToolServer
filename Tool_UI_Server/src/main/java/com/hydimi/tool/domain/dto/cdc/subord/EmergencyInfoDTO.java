package com.hydimi.tool.domain.dto.cdc.subord;

import com.hydimi.tool.domain.dto.cdc.subord.code.DisposalMeasuresCodeDTO;
import com.hydimi.tool.domain.dto.cdc.subord.code.DisposalReasonCodeDTO;
import lombok.Data;

import java.util.List;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/4 14:13
 */
@Data
public class EmergencyInfoDTO {
    public String ID;

    public String ReportZoneCode;

    public String ReportOrgCode;

    public String EmergencyOrg;

    public String PatientName;

    public String IdCardTypeCode;

    public String IdCard;

    public String GenderCode;

    public String NationalityCode;

    public String Age;

    public String FirstDisposalSite;

    public String Reporter;

    public String ReportingTime;

    public String ReportingRoute;

    public String ReportingIdentityCode;

    public String ReportingIdentityDesc;

    public String DisposalStartTime;

    public String DisposalEndTime;

    public String SceneDescription;

    public String SecurityOrganSignatory;

    public String SecurityOrganName;

    public String PreventionDoctor;

    public String PsychiatryDoctor;

    public String PsychiatryNurse;

    public String OtherName;

    public List<DisposalReasonCodeDTO> DisposalReasonCodeList;
    //处置缘由列表用","连接起来
    public String DisposalReasonList;

    public String DisposalReasonOther;

    public List<DisposalMeasuresCodeDTO> DisposalMeasuresCodeList;
    //处置措施列表用","连接起来
    public String DisposalMeasuresList;

    public String DisposalMeasuresOther;

    public String DiagnosticConfirmedCode;

    public String DiagnosticSuspectedCode;

    public String HandlesEffectCode;

    public String HandlesObjectCode;

    public String FillDoctor;

    public String FillDate;
}
