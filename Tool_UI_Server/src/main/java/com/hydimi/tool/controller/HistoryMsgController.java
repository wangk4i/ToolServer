package com.hydimi.tool.controller;

import com.hydimi.tool.domain.info.GetMsgFileInput;
import com.hydimi.tool.domain.info.TrackMsgInput;
import com.hydimi.tool.domain.vo.ResultVO;
import com.hydimi.tool.service.HistoryMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/11/9 16:39
 */
@RestController
@RequestMapping("/HistoryMsg")
public class HistoryMsgController {
    @Autowired
    private HistoryMsgService server;

    @RequestMapping("/HistoricalMsgQuery")
    public ResultVO trackHistoricalMsg(@Valid @RequestBody TrackMsgInput input){
        return ResultVO.success(server.trackHistoricalMsg(input));
    }

    @RequestMapping("/HistoricalMsgFileGet")
    public ResultVO historicalMsgFileGet(@Valid @RequestBody GetMsgFileInput input){
        return ResultVO.success(server.historicalMsgFileGet(input));
    }




}
