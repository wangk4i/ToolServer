package com.hydimi.tool.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hydimi.tool.config.GWConfig;
import com.hydimi.tool.domain.dto.SPMMoveOutPatDTO;
import com.hydimi.tool.domain.enumtion.ExchangeEnum;
import com.hydimi.tool.domain.enumtion.MessageInfoEnum.FollowupEnum;
import com.hydimi.tool.domain.enumtion.MessageTypeEnum;
import com.hydimi.tool.domain.info.FollowupCardInput;
import com.hydimi.tool.domain.info.MessageInfo;
import com.hydimi.tool.domain.info.PatFollowupInput;
import com.hydimi.tool.domain.info.SyncFollowupInput;
import com.hydimi.tool.domain.vo.FollowupCardVO;
import com.hydimi.tool.domain.vo.PatInfoCardVO;
import com.hydimi.tool.exceptclass.BusinessException;
import com.hydimi.tool.exceptclass.ClientException;
import com.hydimi.tool.mapper.SyncPatFollowupMapper;
import com.hydimi.tool.utils.CheckUtils;
import com.hydimi.tool.utils.HttpClientUtils;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/7 9:55
 */
@Service
public class SyncPatFollowupService {
    @Autowired(required = false)
    private SyncPatFollowupMapper mapper;
    @Value("${msg.province-code}")
    private String provinceCode;

    private Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();


    public List<SPMMoveOutPatDTO> queryOutPatForList(SyncFollowupInput input) {
        List<SPMMoveOutPatDTO> result = null;
        try{
            result = mapper.QueryOutPatList(input.Year, provinceCode);
        } catch (MyBatisSystemException e){
            throw new ClientException("数据库请求异常, 接口: queryOutPatForList \n请求数据: "+gson.toJson(input), e);
        }
        if (null == result || result.size() == 0){
            throw new BusinessException("未查找到跨省迁出患者信息");
        }
        return result;
    }

    public List<PatInfoCardVO> queryPatInfoByCdForCard(PatFollowupInput input) {
        List<PatInfoCardVO> result = null;
        try {
            if (CheckUtils.CheckIDCard18(input.Cd)){
                result = mapper.QueryPatInfoCardByIdcode(input.Cd);
            } else {
                result = mapper.QueryPatInfoCardByCd(input.Cd);
            }
        } catch (MyBatisSystemException e) {
            throw new ClientException("数据库请求异常, 接口: queryPatInfoByCdForCard \n请求数据: "+gson.toJson(input), e);
        }
        if(null == result || result.size() == 0){
            throw new BusinessException("未查找到患者信息");
        }
        return result;
    }

    public List<FollowupCardVO> queryPatFollowupByCdForCard(PatFollowupInput input) {
        List<FollowupCardVO> result = null;
        try {
            if (CheckUtils.CheckIDCard18(input.Cd)){
                result = mapper.QueryPatFollowupCardByIdcode(input.Cd);
            } else {
                result = mapper.QueryPatFollowupCardByCd(input.Cd);
            }
        } catch (MyBatisSystemException e) {
            throw new ClientException("数据库请求异常, 接口: queryPatFollowupByCdForCard \n请求数据: "+gson.toJson(input), e);
        }
        if (null == result || result.size() == 0){
            throw new BusinessException("未查找到患者随访信息");
        }
        return result;
    }

    public String syncPatFollowupByKey(FollowupCardInput input) {
        MessageInfo msgInfo = new MessageInfo(
                input.Cd,
                input.ZoneCd,
                input.OrganCd,
                ExchangeEnum.MESSAGE.getCate(),
                MessageTypeEnum.FOLLOWUP.getType(),
                FollowupEnum.UPDATE.getAction());
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
        return "随访信息同步成功!";

    }

    @Transactional
    public String delPatFollowupByKey(FollowupCardInput input) {
        MessageInfo msgInfo = new MessageInfo(
                input.Cd,
                input.ZoneCd,
                input.OrganCd,
                ExchangeEnum.MESSAGE.getCate(),
                MessageTypeEnum.FOLLOWUP.getType(),
                FollowupEnum.DELETE.getAction());
        msgInfo.extensionInfo.setOperator(input.OperatorCd);
        try {
            mapper.delFollowupInfo(input.Cd);
        } catch (MyBatisSystemException e) {
            throw new ClientException("数据库请求异常, 接口: delPatFollowupByKey \n请求数据: "+gson.toJson(input), e);
        }
        String msgReturn = null;
        try {
            msgReturn = HttpClientUtils.ajaxPostJson(GWConfig.MsgSendUrl, gson.toJson(msgInfo));
        } catch (Exception e) {
            throw new ClientException("消息发送异常 请求数据: "+ gson.toJson(msgInfo), e);
        }
        if (!"1".equals(msgReturn)){
            return "消息接收失败";
        }
        return "随访信息删除成功!";
    }



}
