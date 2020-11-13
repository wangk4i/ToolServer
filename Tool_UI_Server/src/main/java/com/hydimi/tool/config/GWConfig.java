package com.hydimi.tool.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/31 17:41
 */
@Component
public class GWConfig {

    public static String MHInterfaceUrl;

    public static String MsgSendUrl;

    public static String MsgHistoryUrl;

    public static String MsgStoreUrl;

    public static String ProvinceCode;

    @Value("${WebService.MentalHealthInterfaceUrl}")
    public void setMHInterfaceUrl(String MHInterfaceUrl) {
        GWConfig.MHInterfaceUrl = MHInterfaceUrl;
    }

    @Value("${msg.url}")
    public void setMsgSendUrl(String msgUrl) {
        MsgSendUrl = msgUrl;
    }

    @Value("${msg.HistoryMsgUrl}")
    public void setMsgHistoryUrl(String msgHistoryUrl) {
        MsgHistoryUrl = msgHistoryUrl;
    }

    @Value("${msg.MsgStoreUrl}")
    public void setMsgStoreUrl(String msgStoreUrl) {
        MsgStoreUrl = msgStoreUrl;
    }

    @Value("${msg.province-code}")
    public void setProvinceCode(String provinceCode) {
        ProvinceCode = provinceCode;
    }
}
