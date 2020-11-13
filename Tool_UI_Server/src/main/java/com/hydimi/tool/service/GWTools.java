package com.hydimi.tool.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.hydimi.tool.config.GWConfig;
import com.hydimi.tool.exceptclass.BusinessException;
import com.hydimi.tool.exceptclass.ClientException;
import com.hydimi.tool.utils.HttpClientUtils;
import com.hydimi.tool.domain.vo.GWResultVO;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/31 18:38
 */
@Component
public class GWTools {

    private Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    public boolean judgeResponse(GWResultVO result){
        if (result.code != 1){
            if (result.code == -1){
                throw new BusinessException(result.message);
            } else {
                throw new BusinessException("接口调用失败");
            }
        }
        return true;
    }

    public <T> GWResultVO<T> requestCooperation(Map dataMap, String interfaceNam, Type typeOfT){
        String body = gson.toJson(dataMap);
        String url = GWConfig.MHInterfaceUrl + interfaceNam;
        String wsReturnStr = null;
        try {
            wsReturnStr = this.sendAjax(url, body);
        } catch (Exception e) {
            throw new ClientException("协同接口请求异常 接口: "+ interfaceNam +" \n请求数据: "+body+"\nUrl: "+url, e);
        }
        GWResultVO<T> gwResult = null;
        try {
            gwResult = gson.fromJson(wsReturnStr, typeOfT);
        } catch (JsonSyntaxException e) {
            throw new BusinessException("协同接口返回值异常 接口: "+ interfaceNam +" \n原始数据: "+wsReturnStr+"\nUrl: "+url , e);
        }
        judgeResponse(gwResult);
        return gwResult;
    }







    /**
     * 发送Ajax请求
     * @param url
     * @param
     */

    public String sendAjax(String url, Map<String, String> dataMap) throws IOException {
        return HttpClientUtils.ajaxPostJson(url, gson.toJson(dataMap));
    }

    public String sendAjax(String url, String jsonbody) throws IOException {
        return HttpClientUtils.ajaxPostJson(url, jsonbody);
    }

}
