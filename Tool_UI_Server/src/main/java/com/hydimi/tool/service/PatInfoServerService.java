package com.hydimi.tool.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hydimi.tool.config.GWConfig;
import com.hydimi.tool.domain.dto.ViewPatInfoVO;
import com.hydimi.tool.domain.dto.cdc.PersonDTO;
import com.hydimi.tool.domain.info.TurnDeathInput;
import com.hydimi.tool.domain.dto.SPMPatDTO;
import com.hydimi.tool.domain.enumtion.ExchangeEnum;
import com.hydimi.tool.domain.enumtion.MessageInfoEnum.PatInfoEnum;
import com.hydimi.tool.domain.enumtion.MessageTypeEnum;
import com.hydimi.tool.domain.enumtion.SexEnum;
import com.hydimi.tool.domain.info.*;
import com.hydimi.tool.domain.vo.*;
import com.hydimi.tool.exceptclass.BusinessException;
import com.hydimi.tool.exceptclass.ClientException;
import com.hydimi.tool.mapper.PatInfoServerMapper;
import com.hydimi.tool.utils.CheckUtils;
import com.hydimi.tool.utils.HttpClientUtils;
import com.hydimi.tool.domain.dto.RepeatPersonDTO;
import com.hydimi.tool.utils.ParseUtils;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/31 17:32
 */
@Slf4j
@Service
public class PatInfoServerService {

    private Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    @Autowired
    private GWTools tools;
    @Autowired
    private ParseUtils util;

    @Autowired(required = false)
    private PatInfoServerMapper mapper;

    private Integer isDeathCode = 1;
    private Integer specialDel = 3;
    private Integer specialRecover = 4;

    /**
     * 患者查重
     */
    public List<RepeatPersonVO> getRepeatInfoByIdcode(GetRepeatInfoByIdcodeInput input) {
        List<RepeatPersonVO> result = new ArrayList<>();
        Map<String, String> params = new LinkedHashMap<>();
        params.put("idcard_code", input.getIdcode());
        params.put("idcard_type", input.getIdtype());
        String body = gson.toJson(params);
        String url = GWConfig.MHInterfaceUrl+"getRepeatInfoByIdcode";
        String wsReturnStr = null;
        try {
            wsReturnStr = tools.sendAjax(url, body);
        } catch (Exception e) {
            throw new ClientException("协同接口请求异常 接口: getRepeatInfoByIdcode \n请求数据: "+body+"\nUrl: "+url, e);
        }
        GWResultVO<List<RepeatPersonDTO>> gwResult = gson.fromJson(wsReturnStr,
                new TypeToken<GWResultVO<List<RepeatPersonDTO>>>(){}.getType());
        tools.judgeResponse(gwResult);
        for (RepeatPersonDTO rpDTO : gwResult.data){
            RepeatPersonVO rpVO = new RepeatPersonVO();
            rpVO.setDischargeInformationId(rpDTO.getID());
            String zoneNam = mapper.queryZoneNam(rpDTO.getZoneCode());
            rpVO.setZoneNam(zoneNam);
            String orgNam = mapper.queryOrgNam(rpDTO.getOrgCode());
            rpVO.setOrgNam(orgNam);
            rpVO.setPatientName(rpDTO.getPatientName());
            rpVO.setGenderCode(SexEnum.MALE.getSex().equals(rpDTO.getGenderCode())?"男":"女");
            rpVO.setIDCode(rpDTO.getIdCard());
            rpVO.setBasicInformationNumber(rpDTO.getPatientNo());
            rpVO.setLivingAddressDetails(rpDTO.getCurrentAddrDetail());
            rpVO.setContactInformation(rpDTO.getContactInformation());
            result.add(rpVO);
        }
        return result;
    }

    /**
     * 患者删除
     * 请求消息接口
     */
    @Transactional
    public String deleteBasicInformation (DeleteBaseInput input) {
        SPMPatDTO patInfo = null;
        String delReasonCd = "DelReason00" + input.deleteCause;
        try {
            patInfo = mapper.queryPatInfoByPk(input.basicInformationId);
            if (null == patInfo) {
                //库里不存在待删除患者时进行特殊处理
                patInfo = this.delPatSpecialDeal(input);
            } else
                mapper.delPatInfo(input.basicInformationId, delReasonCd);
        } catch (MyBatisSystemException e) {
            throw new ClientException("数据库请求异常, 接口: deleteBasicInformation \n请求数据: "+gson.toJson(input), e);
        }

        MessageInfo msgInfo = new MessageInfo(
                input.basicInformationId,
                patInfo.getZoneCd(),
                patInfo.getOrganCd(),
                ExchangeEnum.MESSAGE.getCate(),
                MessageTypeEnum.PATINFO.getType(),
                PatInfoEnum.DELETE.getAction());
        msgInfo.extensionInfo.setOperator(input.OperatorCd);
        if (specialDel.equals(patInfo.special)) msgInfo.setSpecial(specialDel);
        String msgReturn = null;
        try {
             msgReturn = HttpClientUtils.ajaxPostJson(GWConfig.MsgSendUrl, gson.toJson(msgInfo));
        } catch (Exception e) {
            throw new ClientException("消息接口请求异常 接口: deleteBasicInformation \n请求数据: "+gson.toJson(msgInfo) +"Url: "+GWConfig.MsgSendUrl, e);
        }
        if (!"1".equals(msgReturn)){
            return "消息接收失败";
        }
        return "患者删除成功!";
    }

    //删除患者特殊处理
    private SPMPatDTO delPatSpecialDeal(DeleteBaseInput input) {
        SPMPatDTO patDTO = mapper.queryToolPatInfo(input.basicInformationId);
        if (null == patDTO) {
            Gson gson = new GsonBuilder().disableHtmlEscaping().create();
            Map<String, String> params = new LinkedHashMap<>();
            params.put("basicInformationId", input.basicInformationId);
            GWResultVO<PersonDTO> gwResult = tools.requestCooperation(params, "getRelatedBasicInformation"
                    , new TypeToken<GWResultVO<PersonDTO>>(){}.getType());
            tools.judgeResponse(gwResult);
            PersonDTO gwData = gwResult.data;
            mapper.addSpecialDelPatInfo(gwData, gson.toJson(gwResult.data), "DelReason00" + input.deleteCause);

            patDTO = new SPMPatDTO();
            patDTO.setCd(gwData.ID);
            patDTO.setZoneCd(gwData.ZoneCode);
            patDTO.setOrganCd(gwData.OrgCode);
        } else {
            mapper.delToolPatInfo(input.basicInformationId, "DelReason00" + input.deleteCause);
        }
        patDTO.setSpecial(specialDel);
        return patDTO;
    }

    /**
     * 患者恢复
     */
    @Transactional
    public String recoveryBasicInformation(RecoveryBaseInput input) {
        SPMPatDTO patInfo = null;
        try {
            patInfo = mapper.queryPatInfoByPk(input.basicInformationId);
            if (null == patInfo) {
//                throw new BusinessException("恢复失败 无此患者数据 ID=" + input.basicInformationId);

                //库里不存在待恢复患者时进行特殊处理
                patInfo = this.recoverPatSpecialDeal(input);
            } else
                mapper.restorePatInfo(input.basicInformationId);
        } catch (MyBatisSystemException e) {
            throw new ClientException("数据库请求异常 接口: recoveryBasicInformation \n 请求数据: "+gson.toJson(input), e);
        }
        MessageInfo msgInfo = new MessageInfo(
                patInfo.getCd(),
                patInfo.getZoneCd(),
                patInfo.getOrganCd(),
                ExchangeEnum.MESSAGE.getCate(),
                MessageTypeEnum.PATINFO.getType(),
                PatInfoEnum.RESTORE.getAction());
        msgInfo.extensionInfo.setOperator(input.OperatorCd);
        if (specialRecover.equals(patInfo.special)) msgInfo.setSpecial(specialRecover);
        String msgReturn = null;
        try {
            msgReturn = HttpClientUtils.ajaxPostJson(GWConfig.MsgSendUrl, gson.toJson(msgInfo));
        } catch (Exception e) {
            throw new ClientException("消息发送异常 请求数据: "+ gson.toJson(msgInfo), e);
        }
        if (!"1".equals(msgReturn)){
            return "消息接收失败";
        }
        return "患者恢复成功!";
    }

    private SPMPatDTO recoverPatSpecialDeal(RecoveryBaseInput input) {
        SPMPatDTO patDTO = mapper.queryToolPatInfo(input.basicInformationId);
        if (null == patDTO) {
            Gson gson = new GsonBuilder().disableHtmlEscaping().create();
            Map<String, String> params = new LinkedHashMap<>();
            params.put("basicInformationId", input.basicInformationId);
            GWResultVO<PersonDTO> gwResult = tools.requestCooperation(params, "getRelatedBasicInformation"
                    , new TypeToken<GWResultVO<PersonDTO>>(){}.getType());
            tools.judgeResponse(gwResult);
            PersonDTO gwData = gwResult.data;

            mapper.addSpecialRecPatInfo(gwData, gson.toJson(gwResult.data), input.recoveryCause);

            patDTO = new SPMPatDTO();
            patDTO.setCd(gwData.ID);
            patDTO.setZoneCd(gwData.ZoneCode);
            patDTO.setOrganCd(gwData.OrgCode);
        } else {
            mapper.restoreToolPatInfo(input.basicInformationId, input.recoveryCause);
        }
        patDTO.setSpecial(specialRecover);
        return patDTO;
    }


    /**
     * 患者信息同步
     * 1.根据证件号码/主键 查找患者信息
     */
    public SPMPatDTO queryBasicInformation(QueryBaseInput input) {
        SPMPatDTO patDTO = null;
        try {
            if (isDeathCode.equals(input.isDeath)){
                if (CheckUtils.CheckIDCard18(input.CardID)) {
                    patDTO = mapper.queryDeadPatByIdcode(input.CardID);
                } else {
                    patDTO = mapper.queryDeadPatByPk(input.CardID);
                }
            } else {
                if (CheckUtils.CheckIDCard18(input.CardID)) {
                    patDTO = mapper.queryPatInfoByIdcode(input.CardID);
                } else {
                    patDTO = mapper.queryPatInfoByPk(input.CardID);
                }
            }
        } catch (Exception e) {
            throw new ClientException("数据库请求异常 接口: queryBasicInformation \n 请求数据: "+gson.toJson(input), e);
        }
        if (null == patDTO) {
            throw new BusinessException("未查找到该患者信息");
        }

        return patDTO;

    }

    /**
     * 2.患者信息同步
     */
    public String syncBasicInformation(PatInfo input) {
        MessageInfo msgInfo = new MessageInfo(
                input.Cd,
                input.ZoneCd,
                input.OrganCd,
                ExchangeEnum.MESSAGE.getCate(),
                MessageTypeEnum.PATINFO.getType(),
                PatInfoEnum.INSPECT.getAction());
        msgInfo.extensionInfo.setOperator(input.OperatorCd);
        String msgReturn = null;
        try {
            msgReturn = HttpClientUtils.ajaxPostJson(GWConfig.MsgSendUrl, gson.toJson(msgInfo));
        } catch (Exception e) {
            throw new ClientException("消息发送异常 请求数据: "+ gson.toJson(msgInfo), e);
        }
        if (!"1".equals(msgReturn)){
            return "消息接收失败";
        }
        return "患者信息同步成功!";
    }


    /**
     * 根据国网主键查询患者信息
     */
    public ViewPatInfoVO queryPatInfoMana1(QueryPatByPkInput input) {
        ViewPatInfoVO result = new ViewPatInfoVO();
        try {
            result = mapper.queryViewPatInfoByPk(input.basicInformationId);
        } catch (Exception e) {
            throw new ClientException("数据库请求异常, 接口: queryPatInfoMana \n请求数据: "+gson.toJson(input), e);
        }
        if (null == result){
            throw new BusinessException("未查询到该患者信息");
        }
        result.PastRiskHaveList = util.getListValue(result.PastRiskHave);
        result.ReceiveDisabledCardList = util.getListValue(result.ReceiveDisabledCard);

        return result;
    }

    public PersonDTO queryPatInfoMana(QueryPatByPkInput input) {
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


    /**
     * 死亡患者同步
     * @param input
     * @return
     */
    public String patTurnDeath(TurnDeathInput input) {
        MessageInfo msgInfo = new MessageInfo(
                input.PatInfoCd,
                input.ZoneCd,
                input.OrganCd,
                ExchangeEnum.MESSAGE.getCate(),
                MessageTypeEnum.PATINFO.getType(),
                PatInfoEnum.TRUNDEATH.getAction());
        msgInfo.extensionInfo.setOperator(input.OperatorCd);
        String msgReturn = null;
        try {
            msgReturn = HttpClientUtils.ajaxPostJson(GWConfig.MsgSendUrl, gson.toJson(msgInfo));
        } catch (Exception e) {
            throw new ClientException("消息发送异常 请求数据: "+ gson.toJson(msgInfo), e);
        }
        if (!"1".equals(msgReturn)){
            return "消息接收失败";
        }
        return "死亡患者同步成功!";
    }


}
