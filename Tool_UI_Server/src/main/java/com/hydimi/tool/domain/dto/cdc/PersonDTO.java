package com.hydimi.tool.domain.dto.cdc;

import com.hydimi.tool.domain.dto.cdc.subord.code.PsychogenyPastDangerousCodeDTO;
import com.hydimi.tool.domain.dto.cdc.subord.ReceiveDisabledDTO;
import lombok.Data;

import java.util.List;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/2 18:08
 */
@Data
public class PersonDTO {
    public String ID;

    public String PatientNo;

    public String ZoneCode;

    public String OrgCode;

    public String PatientName;

    public String IdCardTypeCode;

    public String IdCard;

    public String NationalityCode;

    public String GenderCode;

    public String BirthDate;

    public String Contacts;

    public String ContactsTel;

    public String ContactsTelTwo;

    public String ContactsRelCode;

    public String FamilyHistory;

    public String NationCode;

    public String EducationCode;

    public String ProfessionCode;

    public String MaritalStatusCode;

    public String PsychogenyEconomyLevelCode;

    public String PermanentAddrTypeCode;

    public String PermanentAddrCode;

    public String PermanentAddrDetail;

    public String PsychogenyPermanentAddrCode;

    public String PsychogenyPermanentAddrTypeUnknownCause;

    public String CurrentAddrTypeCode;

    public String CurrentAddrCode;

    public String CurrentAddrDetail;

    public String PsychogenyAddrTypeCode;

    public String DiseaseCode;

    public String DiagnoseOrg;

    public String DiagnoseOrgUnknown;

    public String DiagnoseOrgUnknownReason;

    public String DiagnoseTime;

    public String DiagnoseTimeAccuracyCode;

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

    public String IfSupportCode;

    public String IfSubsidyCode;

    public List<ReceiveDisabledDTO> ReceiveDisabledList;
    //领取残疾人证情况 ","连接
    public String ReceiveDisableList;

    public String DisabledLevelCode;

    public String IfCarefor;

    public String IfFamilyPhysician;

    public String IfRecoveryMeasures;

    public String PsychogenyInformedCode;

    public String PsychogenyInformedDate;

    public String DeadDate;

    public String DeadReason;

    public String DeadDetail;

}
