package com.hydimi.tool.service;

import com.google.gson.reflect.TypeToken;
import com.hydimi.tool.domain.dto.cdc.*;
import com.hydimi.tool.domain.dto.cdc.subord.*;
import com.hydimi.tool.domain.info.*;
import com.hydimi.tool.domain.vo.GWResultVO;
import com.hydimi.tool.utils.ParseUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/9 9:32
 */
@Service
public class GWInfoQueryService {

    @Autowired
    private GWTools tools;
    @Autowired
    private ParseUtils util;


    public PersonDTO getBasicInfoById(PatIdInfo input) {
        PersonDTO result = null;
        Map<String, String> params = new LinkedHashMap<>();
        params.put("basicInformationId", input.basicInformationId);
        GWResultVO<PersonDTO> gwResult = tools.requestCooperation(params, "getRelatedBasicInformation"
                , new TypeToken<GWResultVO<PersonDTO>>(){}.getType());
        //既往危险行为列表
        gwResult.data.PsychogenyPastDangerousList = util.linkList(gwResult.data.PsychogenyPastDangerousCodeList);
        //领取残疾人证列表
        gwResult.data.ReceiveDisableList = util.linkList(gwResult.data.ReceiveDisabledList);
        result = gwResult.data;
        return result;
    }

    public FollowupInfoListDTO getRelatedFollowupInfo(PatIdInfo input) {
        FollowupInfoListDTO result = null;
        Map<String, String> params = new LinkedHashMap<>();
        params.put("basicInformationId", input.basicInformationId);
        params.put("pageNum", Strings.isNotEmpty(input.pageNum)?input.pageNum:"1");
        GWResultVO<FollowupInfoListDTO> gwResult = tools.requestCooperation(params, "getRelatedFollowupInformation"
                , new TypeToken<GWResultVO<FollowupInfoListDTO>>(){}.getType());

        for (FollowupInfoDTO infoDTO : gwResult.data.FollowupInfoList){
            //随访对象列表
            infoDTO.FollowObjectList = util.linkList(infoDTO.FollowObjectCodeList);
            //目前症状列表
            infoDTO.SymptomsList = util.linkList(infoDTO.SymptomsCodeList);
            //实验室检查列表
            infoDTO.LaboratorySpecificList = util.linkList(infoDTO.LaboratorySpecificCodeList);
            //康复措施列表
            infoDTO.RecoveryMeasuresList = util.linkList(infoDTO.RecoveryMeasuresCodeList);
        }

        result = gwResult.data;
        return result;
    }

    public CaseReportDTO getNewCaseReportById(CaseReportInput input){
        CaseReportDTO result = null;
        Map<String, String> params = new LinkedHashMap<>();
        params.put("newCaseReportId", input.getNewCaseReportId());
        GWResultVO<CaseReportDTO> gwResult = tools.requestCooperation(params, "getNewCaseReportByID"
                , new TypeToken<GWResultVO<CaseReportDTO>>(){}.getType());
        //既往危险行为列表
        gwResult.data.PsychogenyPastDangerousList = util.linkList(gwResult.data.PsychogenyPastDangerousCodeList);
        //送诊主体列表
        gwResult.data.PsychogenySendDiagnosisList = util.linkList(gwResult.data.PsychogenySendDiagnosisCodeList);

        result = gwResult.data;
        return result;
    }

    public DischargeInfoDTO getDischargeInfoById(DischargeInfoInput input){
        DischargeInfoDTO result = null;
        Map<String, String> params = new LinkedHashMap<>();
        params.put("dischargeInformationId", input.dischargeInformationId);
        GWResultVO<DischargeInfoDTO> gwResult = tools.requestCooperation(params, "getDischargeInformationByID"
                , new TypeToken<GWResultVO<DischargeInfoDTO>>(){}.getType());
        //既往危险行为列表
        gwResult.data.PsychogenyPastDangerousList = util.linkList(gwResult.data.PsychogenyPastDangerousCodeList);
        //康复措施列表
        gwResult.data.NextMeasuresList = util.linkList(gwResult.data.NextMeasuresCodeList);

        result = gwResult.data;
        return result;
    }

    public EmergencyInfoDTO GetEmerDealById(EmergencyInput input){
        EmergencyInfoDTO result = null;
        Map<String, String> params = new LinkedHashMap<>();
        params.put("emergencyId", input.emergencyId);
        GWResultVO<EmergencyInfoDTO> gwResult = tools.requestCooperation(params, "getEmergencyInformationByID"
                , new TypeToken<GWResultVO<EmergencyInfoDTO>>(){}.getType());
        //处置缘由列表
        gwResult.data.DisposalReasonList = util.linkList(gwResult.data.DisposalReasonCodeList);
        //处置措施列表
        gwResult.data.DisposalMeasuresList = util.linkList(gwResult.data.DisposalMeasuresCodeList);
        result = gwResult.data;
        return result;
    }


    public CaseReportListDTO getRelatedNewCaseReport(PatIdInfo input) {
        CaseReportListDTO result = null;
        Map<String, String> params = new LinkedHashMap<>();
        params.put("basicInformationId", input.basicInformationId);
        params.put("pageNum", Strings.isNotEmpty(input.pageNum)?input.pageNum:"1");
        GWResultVO<CaseReportListDTO> gwResult = tools.requestCooperation(params, "getRelatedNewCaseReport"
                , new TypeToken<GWResultVO<CaseReportListDTO>>(){}.getType());
        for (CaseReportDTO dto : gwResult.data.DiseaseInfoList){
            //既往危险行为列表
            dto.PsychogenyPastDangerousList = util.linkList(dto.PsychogenyPastDangerousCodeList);
            //送诊主体列表
            dto.PsychogenySendDiagnosisList = util.linkList(dto.PsychogenySendDiagnosisCodeList);
        }
        result = gwResult.data;
        return result;
    }

    public DischargeInfoListDTO getRelatedDischargeInformation(PatIdInfo input) {
        DischargeInfoListDTO result = null;
        Map<String, String> params = new LinkedHashMap<>();
        params.put("basicInformationId", input.basicInformationId);
        params.put("pageNum", Strings.isNotEmpty(input.pageNum)?input.pageNum:"1");
        GWResultVO<DischargeInfoListDTO> gwResult = tools.requestCooperation(params, "getRelatedDischargeInformation"
                , new TypeToken<GWResultVO<DischargeInfoListDTO>>(){}.getType());
        for (DischargeInfoDTO dto : gwResult.data.TreatmentInfoList){
            //既往危险行为列表
            dto.PsychogenyPastDangerousList = util.linkList(dto.PsychogenyPastDangerousCodeList);
            //康复措施列表
            dto.NextMeasuresList = util.linkList(dto.NextMeasuresCodeList);
        }
        result = gwResult.data;
        return result;
    }

    public EmergencyInfoListDTO getRelatedEmergencyInformation(PatIdInfo input) {
        EmergencyInfoListDTO result = null;
        Map<String, String> params = new LinkedHashMap<>();
        params.put("IdCard", input.IdCard);
        params.put("pageNum", Strings.isNotEmpty(input.pageNum)?input.pageNum:"1");
        GWResultVO<EmergencyInfoListDTO> gwResult = tools.requestCooperation(params, "getRelatedEmergencyInformation"
                , new TypeToken<GWResultVO<EmergencyInfoListDTO>>(){}.getType());
        for (EmergencyInfoDTO dto : gwResult.data.EmergencyInfoList){
            //处置缘由列表
            dto.DisposalReasonList = util.linkList(dto.DisposalReasonCodeList);
            //处置措施列表
            dto.DisposalMeasuresList = util.linkList(dto.DisposalMeasuresCodeList);
        }
        result = gwResult.data;
        return result;
    }
}
