package com.hydimi.tool.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hydimi.tool.config.GWConfig;
import com.hydimi.tool.domain.dto.*;
import com.hydimi.tool.domain.enumtion.ExchangeEnum;
import com.hydimi.tool.domain.enumtion.MessageInfoEnum.PatInfoEnum;
import com.hydimi.tool.domain.enumtion.MessageTypeEnum;
import com.hydimi.tool.domain.info.*;
import com.hydimi.tool.domain.vo.*;
import com.hydimi.tool.exceptclass.BusinessException;
import com.hydimi.tool.exceptclass.ClientException;
import com.hydimi.tool.mapper.GWMsgResend.*;
import com.hydimi.tool.utils.HttpClientUtils;
import org.apache.logging.log4j.util.Strings;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/10/19 11:52
 */
@Service
public class GWMsgResendService {

    @Autowired(required = false)
    private PatInfoResendMapper patInfoMapper;
    @Autowired(required = false)
    private FollowupResendMapper followupMapper;
    @Autowired(required = false)
    private ReportResendMapper reportMapper;
    @Autowired(required = false)
    private DischargeResendMapper dischargeMapper;
    @Autowired(required = false)
    private EmergencyResendMapper emergencyMapper;


    private Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    public GWResendPatVO patInfoQuery(PatInfoQueryInput input) {
        GWResendPatVO result = new GWResendPatVO();
        try {
            result.patList = QueryPatInfo(input);
            if (null ==  result.patList)
                throw new BusinessException("未查询到患者信息");
        } catch (MyBatisSystemException e) {
            throw new ClientException("数据库请求异常 接口: patInfoQuery \n请求数据: "+gson.toJson(input), e);
        }
        //未同步的 时间赋值
        if ("-1".equals(input.syncStatus)){
            for (SPMPatDTO dto : result.patList){
                if (!Strings.isEmpty(dto.LastModTime)){
                    dto.setSyncTime(dto.LastModTime);
                } else {
                    dto.setSyncTime(dto.CreTime);
                }
            }
        }
        result.existErr = patInfoMapper.syncErrorQuery(input, GWConfig.ProvinceCode);
        return result;
    }

    public String patInfoBatchResend(PatInfoBatchResendInput input) {
        for (PatInfoResendInput info : input.PatInfoList){
            info.OperatorCd = input.OperatorCd;
            try {
                patInfoResend(info);
            } catch (Exception e) {
                continue;
            }
        }
        return "重发成功";
    }

    public String patInfoResend(PatInfoResendInput patInfo){
        MessageInfo msgInfo = new MessageInfo(
                patInfo.getCd(),
                patInfo.getZoneCd(),
                patInfo.getOrganCd(),
                ExchangeEnum.MESSAGE.getCate(),
                MessageTypeEnum.PATINFO.getType(),
                PatInfoEnum.UPDATE.getAction());
        msgInfo.extensionInfo.setOperator(patInfo.OperatorCd);
        String msgReturn = null;
        try {
            msgReturn = HttpClientUtils.ajaxPostJson(GWConfig.MsgSendUrl, gson.toJson(msgInfo));
        } catch (Exception e) {
            throw new ClientException("消息发送异常 请求数据: "+ gson.toJson(msgInfo), e);
        }
        if ("1".equals(msgReturn)) return "重发成功";
        else return "重发失败";
    }

    private List<SPMPatDTO> QueryPatInfo(PatInfoQueryInput input){
        List<SPMPatDTO> result = null;
        if (!Strings.isEmpty(input.IDCode)){
            result = patInfoMapper.patInfoQueryByIdcode(input, GWConfig.ProvinceCode);
        } else {
            //非未同步的
            if (!"-1".equals(input.syncStatus)) {
                result = patInfoMapper.patInfoQuery(input, GWConfig.ProvinceCode);
            } else {
                result = patInfoMapper.patNotSyncQuery(input, GWConfig.ProvinceCode);
            }
        }
        return result;
    }


    public String resetInspectYear(PatInfoResendInput input) {
        try {
            patInfoMapper.resetInspectYear(input.Cd);
        } catch (MyBatisSystemException e) {
            throw new ClientException("数据库请求异常 接口: resetInspectYear \n请求数据: "+gson.toJson(input), e);
        }
        return "重置成功";
    }

    public GWResendFollowupVO followupInfoQuery(FollowupInfoQueryInput input) {
        GWResendFollowupVO result = new GWResendFollowupVO();
        try {
            result.followupList = QueryFollowupInfo(input);
            if (null == result.followupList)
                throw new BusinessException("未查询到随访信息");
        } catch (MyBatisSystemException e){
            throw new ClientException("数据库请求异常 接口: followupInfoQuery \n请求数据: "+gson.toJson(input), e);
        }
        //知情同意 赋值
        //未同步的 时间赋值
        if ("-1".equals(input.syncStatus)){
            for (SPMFollowupDTO dto : result.followupList){
                if (!Strings.isEmpty(dto.LastModTime)) dto.setSyncTime(dto.LastModTime);
                else dto.setSyncTime(dto.CreTime);
                String informCd = dto.InformedConsCd;
                if ("1".equals(informCd.substring(informCd.length()-1))) dto.InformedConsValue = "是";
                else dto.InformedConsValue = "否";
            }
        } else {
            for (SPMFollowupDTO dto : result.followupList){
                String informCd = dto.InformedConsCd;
                if ("1".equals(informCd.substring(informCd.length()-1))) dto.InformedConsValue = "是";
                else dto.InformedConsValue = "否";
            }
        }
        result.existErr = followupMapper.followSyncErrorQuery(input, GWConfig.ProvinceCode);
        return result;
    }

    private List<SPMFollowupDTO> QueryFollowupInfo(FollowupInfoQueryInput input) {
        List<SPMFollowupDTO> result = null;
        if (!Strings.isEmpty(input.IDCode)){
            result = followupMapper.followupInfoQueryByIdcode(input, GWConfig.ProvinceCode);
        } else {
            //非未同步的
            if (!"-1".equals(input.syncStatus)){
                result = followupMapper.followupInfoQuery(input, GWConfig.ProvinceCode);
            } else {
                result = followupMapper.followupNotSyncQuery(input, GWConfig.ProvinceCode);
            }
        }
        return result;
    }

    public String followupInfoBatchResend(FollowupBatchResendInput input) {
        for (FollowupResendInput info : input.FollowupInfoList){
            info.OperatorCd = input.OperatorCd;
            try {
                followupInfoResend(info);
            } catch (Exception e) {
                continue;
            }
        }
        return "重发成功";
    }

    public String followupInfoResend(FollowupResendInput followupInfo) {
        MessageInfo msgInfo = new MessageInfo(
                followupInfo.getCd(),
                followupInfo.getZoneCd(),
                followupInfo.getOrganCd(),
                ExchangeEnum.MESSAGE.getCate(),
                MessageTypeEnum.FOLLOWUP.getType(),
                PatInfoEnum.UPDATE.getAction());
        msgInfo.extensionInfo.setOperator(followupInfo.OperatorCd);
        String msgReturn = null;
        try {
            msgReturn = HttpClientUtils.ajaxPostJson(GWConfig.MsgSendUrl, gson.toJson(msgInfo));
        } catch (Exception e) {
            throw new ClientException("消息发送异常 请求数据: "+ gson.toJson(msgInfo), e);
        }
        if ("1".equals(msgReturn)) return "重发成功";
        else return "重发失败";
    }

    public String followPatBatchResend(FollowupBatchResendInput input) {
        for (FollowupResendInput info : input.FollowupInfoList){
            PatInfoResendInput patInfo = new PatInfoResendInput(
                    info.PatInfoCd, info.ZoneCd, info.OrganCd, input.OperatorCd);
            try {
                this.patInfoResend(patInfo);
            } catch (Exception e) {
                continue;
            }
        }
        return "重发成功";
    }

    public GWResendReportVO reportInfoQuery(InfoQueryInput input) {
        GWResendReportVO result = new GWResendReportVO();
        try {
            result.reportList = QueryReportInfo(input);
            if (null ==  result.reportList)
                throw new BusinessException("未查询到报告卡信息");
        } catch (MyBatisSystemException e) {
            throw new ClientException("数据库请求异常 接口: reportInfoQuery \n请求数据: "+gson.toJson(input), e);
        }
        //未同步的 时间赋值
        if ("-1".equals(input.syncStatus)){
            for (SPMReportDTO dto : result.reportList){
                if (!Strings.isEmpty(dto.LastModTime)){
                    dto.setSyncTime(dto.LastModTime);
                } else {
                    dto.setSyncTime(dto.CreTime);
                }
            }
        }
        result.existErr = reportMapper.syncErrorQuery(input, GWConfig.ProvinceCode);
        return result;
    }

    private List<SPMReportDTO> QueryReportInfo(InfoQueryInput input) {
        List<SPMReportDTO> result = null;
        if (!Strings.isEmpty(input.IDCode)){
            result = reportMapper.reportInfoQueryByIdcode(input, GWConfig.ProvinceCode);
        } else {
            //非未同步的
            if (!"-1".equals(input.syncStatus)){
                result = reportMapper.reportInfoQuery(input, GWConfig.ProvinceCode);
            } else {
                result = reportMapper.reportNotSyncQuery(input, GWConfig.ProvinceCode);
            }
        }
        return result;
    }

    public GWResendDischargeVO dischargeInfoQuery(InfoQueryInput input) {
        GWResendDischargeVO result = new GWResendDischargeVO();
        try {
            result.dischargeList = QueryDischargeInfo(input);
            if (null ==  result.dischargeList)
                throw new BusinessException("未查询到出院单信息");
        } catch (MyBatisSystemException e) {
            throw new ClientException("数据库请求异常 接口: dischargeInfoQuery \n请求数据: "+gson.toJson(input), e);
        }
        //未同步的 时间赋值
        if ("-1".equals(input.syncStatus)){
            for (SPMDischargeDTO dto : result.dischargeList){
                if (!Strings.isEmpty(dto.LastModTime)){
                    dto.setSyncTime(dto.LastModTime);
                } else {
                    dto.setSyncTime(dto.CreTime);
                }
            }
        }
        result.existErr = dischargeMapper.syncErrorQuery(input, GWConfig.ProvinceCode);
        return result;
    }

    private List<SPMDischargeDTO> QueryDischargeInfo(InfoQueryInput input) {
        List<SPMDischargeDTO> result = null;
        if (!Strings.isEmpty(input.IDCode)){
            result = dischargeMapper.dischargeInfoQueryByIdcode(input, GWConfig.ProvinceCode);
        } else {
            //非未同步的
            if (!"-1".equals(input.syncStatus)){
                result = dischargeMapper.dischargeInfoQuery(input, GWConfig.ProvinceCode);
            } else {
                result = dischargeMapper.dischargeNotSyncQuery(input, GWConfig.ProvinceCode);
            }
        }
        return result;
    }

    public GWResendEmergencyVO emergencyInfoQuery(InfoQueryInput input) {
        GWResendEmergencyVO result = new GWResendEmergencyVO();
        try {
            result.emergencyList = QueryEmergencyInfo(input);
            if (null ==  result.emergencyList)
                throw new BusinessException("未查询到应急处置信息");
        } catch (MyBatisSystemException e) {
            throw new ClientException("数据库请求异常 接口: emergencyInfoQuery \n请求数据: "+gson.toJson(input), e);
        }
        //未同步的 时间赋值
        if ("-1".equals(input.syncStatus)){
            for (SPMEmergencyDTO dto : result.emergencyList){
                if (!Strings.isEmpty(dto.LastModTime)){
                    dto.setSyncTime(dto.LastModTime);
                } else {
                    dto.setSyncTime(dto.CreTime);
                }
            }
        }
        result.existErr = emergencyMapper.syncErrorQuery(input, GWConfig.ProvinceCode);
        return result;
    }

    private List<SPMEmergencyDTO> QueryEmergencyInfo(InfoQueryInput input) {
        List<SPMEmergencyDTO> result = null;
        if (!Strings.isEmpty(input.IDCode)){
            result = emergencyMapper.emergencyInfoQueryByIdcode(input, GWConfig.ProvinceCode);
        } else {
            //非未同步的
            if (!"-1".equals(input.syncStatus)){
                result = emergencyMapper.emergencyInfoQuery(input, GWConfig.ProvinceCode);
            } else {
                result = emergencyMapper.emergencyNotSyncQuery(input, GWConfig.ProvinceCode);
            }
        }
        return result;
    }

    public String InfoBatchResend(Integer msgType, BatchResendInput input) {
        for (SingleResendInput info : input.InfoList){
            info.OperatorCd = input.OperatorCd;
            try {
                InfoResend(msgType, info);
            } catch (Exception e) {
                continue;
            }
        }
        return "重发成功";
    }

    public String InfoResend(Integer msgType, SingleResendInput input) {
        MessageInfo msgInfo = new MessageInfo(
                input.getCd(),
                input.getZoneCd(),
                input.getOrganCd(),
                ExchangeEnum.MESSAGE.getCate(),
                msgType,
                PatInfoEnum.UPDATE.getAction());
        msgInfo.extensionInfo.setOperator(input.OperatorCd);
        String msgReturn = null;
        try {
            msgReturn = HttpClientUtils.ajaxPostJson(GWConfig.MsgSendUrl, gson.toJson(msgInfo));
        } catch (Exception e) {
            throw new ClientException("消息发送异常 请求数据: "+ gson.toJson(msgInfo), e);
        }
        if ("1".equals(msgReturn)) return "重发成功";
        else return "重发失败";
    }
}
