package com.hydimi.tool.domain.dto.cdc.subord;

import com.hydimi.tool.domain.dto.cdc.subord.code.DrugDTO;
import com.hydimi.tool.domain.dto.cdc.subord.code.NextMeasuresCodeDTO;
import com.hydimi.tool.domain.dto.cdc.subord.code.PsychogenyPastDangerousCodeDTO;
import lombok.Data;

import java.util.List;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/9 15:56
 */
@Data
public class DischargeInfoDTO {
    public String ID;

    public String ReportID;

    public String PersonID;

    public String ReportZoneCode;

    public String ReportOrgCode;

    public String PatientRegistrationNumber;

    public String InhospitalNo;

    public String PatientName;

    public String IdCardTypeCode;

    public String IdCard;

    public String GenderCode;

    public String NationalityCode;

    public String BirthDate;

    public String Age;

    public String AdmissionDate;

    public String DischargeDate;

    public String PsychogenyPastHospital;

    public List<PsychogenyPastDangerousCodeDTO> PsychogenyPastDangerousCodeList;
    // 既往危险行为列表 ","连接
    public String PsychogenyPastDangerousList;

    public String PsychogenyPastRiskCode;

    public String DiseaseCode;

    public String DiagnoseTime;

    public String IfNoMedication;

    public List<DrugDTO> DrugList;

    public String TreatmentEffectCode;

    public String GuideIfNoMedication;

    public List<DrugDTO> GuideDrugList;

    public List<NextMeasuresCodeDTO> NextMeasuresCodeList;
    // 康复措施列表 ","连接
    public String NextMeasuresList;

    public String NextMeasuresCodeOther;

    public String NextMeasuresOther;

    public String DoctorName;

    public String DoctorPhone;

    public String FillDate;
}
