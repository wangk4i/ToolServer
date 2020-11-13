package com.hyd.storage.controller;

import com.hyd.storage.domain.vo.ResultVO;
import com.hyd.storage.exceptclass.ClientException;
import com.hyd.storage.service.StorageService;
import com.hyd.storage.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author wangkai
 * @version 1.0
 * @date 2020/11/12 10:31
 */
@RestController
//@RequestMapping("")
public class StorageController {

    @Autowired
    private StorageService server;

    /*@RequestMapping("/RewriteOrigin")
    public String writeMsgOrigin(@RequestBody String folderNam){
        try{
            server.clearMsgOrigin(folderNam);
        } catch (Exception e){
            LogUtil.error("回写消息来源异常: ", e);
            return "0";
        }
        return "1";
    }*/

    @RequestMapping("/GetMsgLocation")
    public ResultVO getMsgLocation(String msgId) {
        ResultVO result = new ResultVO();
        try {
            result.setData(server.getMsgLocation(msgId));
            result.setCode(1);
        } catch (ClientException cex) {
            result.setMessage(cex.getMsg());
        } catch (Exception unknown) {
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: getMsgLocation \n请求数据: " + msgId, unknown);
        }
        return result;
    }

    @RequestMapping("/GetMsgOverview")
    public ResultVO getMsgOverview(){
        ResultVO result = new ResultVO();
        try {
            result.setData(server.getMsgOverview());
            result.setCode(1);
        } catch (ClientException cex) {
            result.setMessage(cex.getMsg());
        } catch (Exception unknown) {
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: gtMsgOverview", unknown);
        }
        return result;
    }

}
