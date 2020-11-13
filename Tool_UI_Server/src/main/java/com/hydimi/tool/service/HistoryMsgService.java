package com.hydimi.tool.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hydimi.tool.config.GWConfig;
import com.hydimi.tool.domain.dto.HistoricalMsgDTO;
import com.hydimi.tool.domain.enumtion.MessageActionEnum;
import com.hydimi.tool.domain.info.GetMsgFileInput;
import com.hydimi.tool.domain.info.TrackMsgInput;
import com.hydimi.tool.domain.vo.HistoryMsgVO;
import com.hydimi.tool.exceptclass.BusinessException;
import com.hydimi.tool.exceptclass.ClientException;
import com.hydimi.tool.mapper.HistoryMsgMapper;
import com.hydimi.tool.utils.HttpClientUtils;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/11/9 16:48
 */
@Service
public class HistoryMsgService {
    @Autowired(required = false)
    private HistoryMsgMapper mapper;

    private Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

    private Integer IsLocalCode = 1;


    public List<HistoricalMsgDTO> trackHistoricalMsg(TrackMsgInput input) {
        List<HistoricalMsgDTO> result = null;
        try {
            result = this.queryHistoricalMsg(input);
            if (null == result || result.size() == 0){
                throw new BusinessException("未查询到历史消息");
            }
        } catch (MyBatisSystemException e) {
            throw new ClientException("数据库请求异常 接口: trackHistoricalMsg \n请求数据: "+gson.toJson(input), e);
        }
        String suc = "1";
        for (HistoricalMsgDTO dto : result){
            if (suc.equals(dto.Status)){
                dto.ErrMsg = "";
                dto.setStatus("成功");
            } else {
                dto.setStatus("失败: "+ dto.ErrMsg);
            }
            if (null == dto.WriteTime){
                dto.setWriteTime(dto.ReceivedTime);
            }
            if (!IsLocalCode.equals(dto.IsLocal)){
                dto.setIsLocal(0);
            }

            dto.ActionNam = MessageActionEnum.getValue(dto.GWAction);
        }
        return result;
    }

    private List<HistoricalMsgDTO> queryHistoricalMsg(TrackMsgInput input) {
        List<HistoricalMsgDTO> result = null;
        switch (input.MsgType){
            case 1 :
                result = mapper.queryPatInfoMsg(input.MsgID);
                break;
            case 2 :
                result = mapper.queryFollowupMsg(input.MsgID);
                break;
            case 3 :
                result = mapper.queryReportMsg(input.MsgID);
                break;
            case 4 :
                result = mapper.queryDischargeMsg(input.MsgID);
                break;
            case 5 :
                result = mapper.queryEmergencyMsg(input.MsgID);
                break;
            default:
                break;
        }
        return result;
    }

    public HistoryMsgVO historicalMsgFileGet(GetMsgFileInput input) {
        HistoryMsgVO result = new HistoryMsgVO();
        String day = input.MsgFileID.substring(input.MsgFileID.lastIndexOf("-")+1
                , input.MsgFileID.lastIndexOf("-")+9);
        String url = GWConfig.MsgHistoryUrl + day + "/" + input.MsgFileID;
        try {
            result.msgJson = HttpClientUtils.ajaxGet(url + ".json");
            result.msgXml = HttpClientUtils.ajaxGet(url + ".xml");
            if (IsLocalCode.equals(input.islocal)){
                result.msgTxt = "本地消息 无国网交换txt";
            } else {
                result.msgTxt = HttpClientUtils.ajaxGet(url + ".txt");
            }
        } catch (IOException e) {
            throw new ClientException("文件请求异常, 接口: historicalMsgFileGet \n请求数据: "+gson.toJson(input), e);
        }
        return result;
    }

}
