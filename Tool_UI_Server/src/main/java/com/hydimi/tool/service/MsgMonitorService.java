package com.hydimi.tool.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hydimi.tool.config.GWConfig;
import com.hydimi.tool.domain.info.MsgLocationInput;
import com.hydimi.tool.domain.vo.MsgLocationVO;
import com.hydimi.tool.domain.vo.MsgOverviewVO;
import com.hydimi.tool.domain.vo.ResultVO;
import com.hydimi.tool.exceptclass.ClientException;
import com.hydimi.tool.utils.HttpClientUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/11/12 15:09
 */
@Service
public class MsgMonitorService {


    private Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

    public MsgLocationVO getMsgLocation(MsgLocationInput input) {
        String wsReturnStr = null;
        try {
            wsReturnStr = HttpClientUtils.ajaxGet(GWConfig.MsgStoreUrl + "GetMsgLocation?msgId=" + input.MsgId);
        } catch (IOException e) {
            throw new ClientException("通信异常 接口: getMsgLocation \n请求数据: "+gson.toJson(input), e);
        }
//        MsgLocationVO result = gson.fromJson(wsReturnStr, MsgLocationVO.class);
        ResultVO<MsgLocationVO> gwResult = gson.fromJson(wsReturnStr,
                new TypeToken<ResultVO<MsgLocationVO>>(){}.getType());
        MsgLocationVO result = gwResult.getData();
        if (result.hasMsgFile){
            result.hasMsgFileValue = "有";
        } else {
            result.hasMsgFileValue = "无";
        }
        return result;
    }

    public MsgOverviewVO getMsgOverview() {
        String wsReturnStr = null;
        try {
            wsReturnStr = HttpClientUtils.ajaxGet(GWConfig.MsgStoreUrl + "GetMsgOverview");
        } catch (IOException e) {
            throw new ClientException("通信异常 接口: getMsgLocation", e);
        }
        ResultVO<MsgOverviewVO> gwResult = gson.fromJson(wsReturnStr,
                new TypeToken<ResultVO<MsgOverviewVO>>(){}.getType());
        //todo Judge gwResult.code
        MsgOverviewVO result = gwResult.getData();

        return result;
    }
}
