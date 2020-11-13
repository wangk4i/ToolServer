package com.hydimi.tool.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hydimi.tool.domain.dto.SPMMoveOutPatDTO;
import com.hydimi.tool.domain.info.FollowupCardInput;
import com.hydimi.tool.domain.info.PatFollowupInput;
import com.hydimi.tool.domain.info.SyncFollowupInput;
import com.hydimi.tool.domain.vo.*;
import com.hydimi.tool.exceptclass.BusinessException;
import com.hydimi.tool.exceptclass.ClientException;
import com.hydimi.tool.service.SyncPatFollowupService;
import com.hydimi.tool.utils.LogUtil;
import com.hydimi.tool.validate.SyncPatFollowupValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/7 9:27
 */
@RestController
@RequestMapping("/SyncPatFollowup")
public class SyncPatFollowupController {
    @Autowired
    private SyncPatFollowupService server;
    @Autowired
    private SyncPatFollowupValid valider;

    private Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    /**
     * 跨省迁出患者查询
     */
    @RequestMapping("/QueryOutPatForList")
    public ResultVO queryOutPatForList(@RequestBody SyncFollowupInput input) {
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.SyncFollowupInputValid(input);
        if (validresult.isError()) {
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try {
            List<SPMMoveOutPatDTO> data = server.queryOutPatForList(input);
            result.setCode(1);
            result.setData(data);
        } catch (BusinessException bex) {
            result.setMessage(bex.getMsg());
            return result;
        } catch (ClientException cex) {
            result.setMessage(cex.getMsg());
            return result;
        } catch (Exception unknown) {
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: queryOutPatForList \n请求数据: " + gson.toJson(input), unknown);
            return result;
        }
        return result;

    }

    /**
     * 查询患者
     */
    @RequestMapping("/QueryPatInfoByCdForCard")
    public ResultVO queryPatInfoByCdForCard(@RequestBody PatFollowupInput input) {
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.PatFollowupInputValid(input);
        if (validresult.isError()) {
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try {
            List<PatInfoCardVO> data = server.queryPatInfoByCdForCard(input);
            result.setCode(1);
            result.setData(data);
        } catch (BusinessException bex) {
            result.setMessage(bex.getMsg());
            return result;
        } catch (ClientException cex) {
            result.setMessage(cex.getMsg());
            return result;
        } catch (Exception unknown) {
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: queryPatInfoByCdForCard \n请求数据: " + gson.toJson(input), unknown);
            return result;
        }
        return result;

    }

    /**
     * 查询随访
     */
    @RequestMapping("/QueryPatFollowupByCdForCard")
    public ResultVO queryPatFollowupByCdForCard(@RequestBody PatFollowupInput input) {
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.PatFollowupInputValid(input);
        if (validresult.isError()) {
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try {
            List<FollowupCardVO> data = server.queryPatFollowupByCdForCard(input);
            result.setCode(1);
            result.setData(data);
        } catch (BusinessException bex) {
            result.setMessage(bex.getMsg());
            return result;
        } catch (ClientException cex) {
            result.setMessage(cex.getMsg());
            return result;
        } catch (Exception unknown) {
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: queryPatFollowupByCdForCard \n请求数据: " + gson.toJson(input), unknown);
            return result;
        }
        return result;

    }

    /**
     * 随访信息同步
     */
    @RequestMapping("/SyncPatFollowupByKey")
    public ResultVO syncPatFollowupByKey(@RequestBody FollowupCardInput input){
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.FollowupCardInputValid(input);
        if (validresult.isError()) {
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try{
            String data = server.syncPatFollowupByKey(input);
            result.setCode(1);
            result.setData(data);
        } catch (BusinessException bex) {
            result.setMessage(bex.getMsg());
            return result;
        } catch (ClientException cex) {
            result.setMessage(cex.getMsg());
            return result;
        } catch (Exception unknown) {
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: syncPatFollowupByKey \n请求数据: " + gson.toJson(input), unknown);
            return result;
        }
        return result;

    }


    /**
     * 随访信息删除
     */
    @RequestMapping("/DelPatFollowupByKey")
    public ResultVO delPatFollowupByKey(@RequestBody FollowupCardInput input){
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.FollowupCardInputValid(input);
        if (validresult.isError()) {
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try{
            String data = server.delPatFollowupByKey(input);
            result.setCode(1);
            result.setData(data);
        } catch (BusinessException bex) {
            result.setMessage(bex.getMsg());
            return result;
        } catch (ClientException cex) {
            result.setMessage(cex.getMsg());
            return result;
        } catch (Exception unknown) {
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: delPatFollowupByKey \n请求数据: " + gson.toJson(input), unknown);
            return result;
        }
        return result;

    }

}
