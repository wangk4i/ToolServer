package com.hydimi.tool.domain.dto.cdc.subord;

import com.hydimi.tool.domain.dto.cdc.subord.code.DrugDTO;
import com.hydimi.tool.domain.dto.cdc.subord.code.PsychogenyPastDangerousCodeDTO;
import com.hydimi.tool.domain.dto.cdc.subord.code.PsychogenySendDiagnosisCodeDTO;
import lombok.Data;

import java.util.List;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/9 15:46
 */
@Data
public class CaseReportDTO {
    public String ID;

    public String PersonID;

    public String ReportZoneCode;

    public String ReportOrgCode;

    public String PatientRegistrationNumber;

    public String BaseinfoSource;

    public String PatientName;

    public String IdCardTypeCode;

    public String IdCard;

    public String GenderCode;

    public String NationalityCode;

    public String BirthDate;

    public String NationCode;

    public String MaritalStatusCode;

    public String EducationCode;

    public String ProfessionCode;

    public String FamilyHistory;

    public String Contacts;

    public String ContactsTel;

    public String ContactsTelTwo;

    public String ContactsRelCode;

    public String PermanentAddrTypeCode;

    public String PermanentAddrCode;

    public String PermanentAddrDetail;

    public String PsychogenyPermanentAddrCode;

    public String PermanentAddrTypeUnknownCause;

    public String CurrentAddrTypeCode;

    public String CurrentAddrCode;

    public String CurrentAddrDetail;

    public String OnsetDate;

    public String OnsetDateAccuracyCode;

    public String SignsMedicationCode;

    public String PsychogenyFirstCureDate;

    public String PsychogenyFirstCureDateAccuracyCode;

    public String PsychogenyPastHospital;

    public String PsychogenyShutStatusHistoryCode;

    public List<PsychogenyPastDangerousCodeDTO> PsychogenyPastDangerousCodeList;
    //既往危险行为列表 ","连接
    public String PsychogenyPastDangerousList;

    public String PsychogenyPastRiskCode;

    public List<PsychogenySendDiagnosisCodeDTO> PsychogenySendDiagnosisCodeList;
    //送诊主体列表 ","连接
    public String PsychogenySendDiagnosisList;

    public String PsychogenySendDiagnosisOther;

    public String DiseaseCode;

    public String DiagnoseOrg;

    public String DiagnoseTime;

    public String IfNoMedication;

    public List<DrugDTO> DrugList;

    public String PsychogenyInformedCode;

    public String PsychogenyInformedDate;

    public String FillDoctor;

    public String FillDate;

    public String Department;

    public String DepartmentPhone;
}
