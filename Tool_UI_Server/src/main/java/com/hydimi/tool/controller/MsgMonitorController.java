package com.hydimi.tool.controller;

import com.hydimi.tool.domain.info.MsgLocationInput;
import com.hydimi.tool.domain.vo.ResultVO;
import com.hydimi.tool.service.MsgMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/11/9 16:39
 */
@RestController
@RequestMapping("/MsgMonitor")
public class MsgMonitorController {

    @Autowired
    private MsgMonitorService server;

    /**
     * 查询消息文件位置
     */
    @RequestMapping("/GetMsgLocation")
    public ResultVO getMsgLocation(@RequestBody MsgLocationInput input){
        return ResultVO.success(server.getMsgLocation(input));
    }

    /**
     * 监控消息文件概况
     */
    @RequestMapping("/GetMsgOverview")
    public ResultVO getMsgOverview(){
        return ResultVO.success(server.getMsgOverview());
    }


}
