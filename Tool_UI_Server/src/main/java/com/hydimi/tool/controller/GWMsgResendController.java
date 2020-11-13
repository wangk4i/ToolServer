package com.hydimi.tool.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hydimi.tool.domain.enumtion.MessageTypeEnum;
import com.hydimi.tool.domain.info.*;
import com.hydimi.tool.domain.vo.*;
import com.hydimi.tool.exceptclass.BusinessException;
import com.hydimi.tool.exceptclass.ClientException;
import com.hydimi.tool.service.GWMsgResendService;
import com.hydimi.tool.utils.LogUtil;
import com.hydimi.tool.validate.GWMsgResendValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/10/19 11:52
 */
@RestController
@RequestMapping("/GWMsgResend")
public class GWMsgResendController {
    @Autowired
    private GWMsgResendService server;
    @Autowired
    private GWMsgResendValid valider;

    private Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    /**
     * 患者信息查询
     */
    @RequestMapping("/PatInfoQuery")
    public ResultVO patInfoQuery(@RequestBody PatInfoQueryInput input){
        ResultVO result= new ResultVO();
        ValidateVO validresult = valider.PatInfoQueryInputValid(input);
        if (validresult.isError()) {
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try{
            GWResendPatVO data = server.patInfoQuery(input);
            result.setCode(1);
            result.setData(data);
        } catch (BusinessException bex) {
            result.setMessage(bex.getMsg());
        } catch (ClientException cex) {
            result.setMessage(cex.getMsg());
        } catch (Exception unknown) {
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: patInfoQuery \n请求数据: "+gson.toJson(input), unknown);
        }
        return result;
    }


    /**
     * 患者信息批量重发
     */
    @RequestMapping("/PatInfoBatchResend")
    public ResultVO PatInfoBatchResend(@RequestBody PatInfoBatchResendInput input){
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.PatInfoBatchResendInputValid(input);
        if (validresult.isError()) {
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try{
            String data = server.patInfoBatchResend(input);
            result.setCode(1);
            result.setData(data);
        } catch (BusinessException bex) {
            result.setMessage(bex.getMsg());
        } catch (ClientException cex) {
            result.setMessage(cex.getMsg());
        } catch (Exception unknown) {
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: PatInfoBatchResend \n请求数据: "+gson.toJson(input), unknown);
        }
        return result;
    }


    /**
     * 患者信息单条重发
     */
    @RequestMapping("/PatInfoResend")
    public ResultVO patInfoResend(@RequestBody PatInfoResendInput input){
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.PatInfoResendInputValid(input);
        if (validresult.isError()) {
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try{
            String data = server.patInfoResend(input);
            result.setCode(1);
            result.setData(data);
        } catch (BusinessException bex) {
            result.setMessage(bex.getMsg());
        } catch (ClientException cex) {
            result.setMessage(cex.getMsg());
        } catch (Exception unknown) {
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: patInfoResend \n请求数据: "+gson.toJson(input), unknown);
        }
        return result;
    }

    /**
     * 患者国网年审重置
     */
    @RequestMapping("/ResetInspectYear")
    public ResultVO resetInspectYear(@RequestBody PatInfoResendInput input){
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.ResetInspectYearValid(input);
        if (validresult.isError()) {
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try{
            String data = server.resetInspectYear(input);
            result.setCode(1);
            result.setData(data);
        } catch (BusinessException bex) {
            result.setMessage(bex.getMsg());
        } catch (ClientException cex) {
            result.setMessage(cex.getMsg());
        } catch (Exception unknown) {
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: resetInspectYear \n请求数据: "+gson.toJson(input), unknown);
        }
        return result;
    }

    /**
     * 随访信息查询
     */
    @RequestMapping("/FollowupInfoQuery")
    public ResultVO followupInfoQuery(@RequestBody FollowupInfoQueryInput input){
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.FollowupInfoQueryInputValid(input);
        if (validresult.isError()) {
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try{
            GWResendFollowupVO data = server.followupInfoQuery(input);
            result.setCode(1);
            result.setData(data);
        } catch (BusinessException bex) {
            result.setMessage(bex.getMsg());
        } catch (ClientException cex) {
            result.setMessage(cex.getMsg());
        } catch (Exception unknown) {
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: followupInfoQuery \n请求数据: "+gson.toJson(input), unknown);
        }
        return result;
    }

    /**
     * 随访信息批量重发
     */
    @RequestMapping("/FollowupInfoBatchResend")
    public ResultVO followupInfoBatchResend(@RequestBody FollowupBatchResendInput input){
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.FollowupBatchResendInputValid(input);
        if (validresult.isError()) {
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try{
            String data = server.followupInfoBatchResend(input);
            result.setCode(1);
            result.setData(data);
        } catch (BusinessException bex) {
            result.setMessage(bex.getMsg());
        } catch (ClientException cex) {
            result.setMessage(cex.getMsg());
        } catch (Exception unknown) {
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: followupInfoBatchResend \n请求数据: "+gson.toJson(input), unknown);
        }
        return result;
    }

    /**
     * 随访信息单条重发
     */
    @RequestMapping("/FollowupInfoResend")
    public ResultVO followupInfoResend(@RequestBody FollowupResendInput input){
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.FollowupResendInputValid(input);
        if (validresult.isError()) {
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try{
            String data = server.followupInfoResend(input);
            result.setCode(1);
            result.setData(data);
        } catch (BusinessException bex) {
            result.setMessage(bex.getMsg());
        } catch (ClientException cex) {
            result.setMessage(cex.getMsg());
        } catch (Exception unknown) {
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: followupInfoResend \n请求数据: "+gson.toJson(input), unknown);
        }
        return result;
    }

    /**
     * 批量重发随访患者
     */
    @RequestMapping("/FollowPatBatchResend")
    public ResultVO followPatBatchResend(@RequestBody FollowupBatchResendInput input){
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.FollowupBatchResendInputValid(input);
        if (validresult.isError()) {
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try{
            String data = server.followPatBatchResend(input);
            result.setCode(1);
            result.setData(data);
        } catch (BusinessException bex) {
            result.setMessage(bex.getMsg());
        } catch (ClientException cex) {
            result.setMessage(cex.getMsg());
        } catch (Exception unknown) {
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: followPatBatchResend \n请求数据: "+gson.toJson(input), unknown);
        }
        return result;
    }

    /**
     * 报告卡信息查询
     */
    @RequestMapping("/ReportInfoQuery")
    public ResultVO reportInfoQuery(@RequestBody InfoQueryInput input){
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.InfoQueryInputValid(input);
        if (validresult.isError()) {
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try{
            GWResendReportVO data = server.reportInfoQuery(input);
            result.setCode(1);
            result.setData(data);
        } catch (BusinessException bex) {
            result.setMessage(bex.getMsg());
        } catch (ClientException cex) {
            result.setMessage(cex.getMsg());
        } catch (Exception unknown) {
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: reportInfoQuery \n请求数据: "+gson.toJson(input), unknown);
        }
        return result;
    }

    /**
     * 报告卡批量重发
     */
    @RequestMapping("/ReportInfoBatchResend")
    public ResultVO reportInfoBatchResend(@RequestBody BatchResendInput input){
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.BatchResendInputValid(input);
        if (validresult.isError()) {
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try{
            String data = server.InfoBatchResend(MessageTypeEnum.REPORTCARD.getType(), input);
            result.setCode(1);
            result.setData(data);
        } catch (BusinessException bex) {
            result.setMessage(bex.getMsg());
        } catch (ClientException cex) {
            result.setMessage(cex.getMsg());
        } catch (Exception unknown) {
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: reportInfoBatchResend \n请求数据: "+gson.toJson(input), unknown);
        }
        return result;
    }

    /**
     * 报告卡单条重发
     */
    @RequestMapping("/ReportInfoResend")
    public ResultVO reportInfoResend(@RequestBody SingleResendInput input){
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.SingleResendInputValid(input);
        if (validresult.isError()) {
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try{
            String data = server.InfoResend(MessageTypeEnum.REPORTCARD.getType(), input);
            result.setCode(1);
            result.setData(data);
        } catch (BusinessException bex) {
            result.setMessage(bex.getMsg());
        } catch (ClientException cex) {
            result.setMessage(cex.getMsg());
        } catch (Exception unknown) {
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: reportInfoResend \n请求数据: "+gson.toJson(input), unknown);
        }
        return result;
    }

    /**
     * 出院信息信息查询
     */
    @RequestMapping("/DischargeInfoQuery")
    public ResultVO dischargeInfoQuery(@RequestBody InfoQueryInput input){
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.InfoQueryInputValid(input);
        if (validresult.isError()) {
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try{
            GWResendDischargeVO data = server.dischargeInfoQuery(input);
            result.setCode(1);
            result.setData(data);
        } catch (BusinessException bex) {
            result.setMessage(bex.getMsg());
        } catch (ClientException cex) {
            result.setMessage(cex.getMsg());
        } catch (Exception unknown) {
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: dischargeInfoQuery \n请求数据: "+gson.toJson(input), unknown);
        }
        return result;
    }

    /**
     * 出院信息批量重发
     */
    @RequestMapping("/DischargeInfoBatchResend")
    public ResultVO dischargeInfoBatchResend(@RequestBody BatchResendInput input){
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.BatchResendInputValid(input);
        if (validresult.isError()) {
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try{
            String data = server.InfoBatchResend(MessageTypeEnum.LEFTCARD.getType(), input);
            result.setCode(1);
            result.setData(data);
        } catch (BusinessException bex) {
            result.setMessage(bex.getMsg());
        } catch (ClientException cex) {
            result.setMessage(cex.getMsg());
        } catch (Exception unknown) {
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: dischargeInfoBatchResend \n请求数据: "+gson.toJson(input), unknown);
        }
        return result;
    }

    /**
     * 出院信息单条重发
     */
    @RequestMapping("/DischargeInfoResend")
    public ResultVO dischargeInfoResend(@RequestBody SingleResendInput input){
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.SingleResendInputValid(input);
        if (validresult.isError()) {
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try{
            String data = server.InfoResend(MessageTypeEnum.LEFTCARD.getType(), input);
            result.setCode(1);
            result.setData(data);
        } catch (BusinessException bex) {
            result.setMessage(bex.getMsg());
        } catch (ClientException cex) {
            result.setMessage(cex.getMsg());
        } catch (Exception unknown) {
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: dischargeInfoResend \n请求数据: "+gson.toJson(input), unknown);
        }
        return result;
    }

    /**
     * 应急处置信息查询
     */
    @RequestMapping("/EmergencyInfoQuery")
    public ResultVO emergencyInfoQuery(@RequestBody InfoQueryInput input){
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.InfoQueryInputValid(input);
        if (validresult.isError()) {
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try{
            GWResendEmergencyVO data = server.emergencyInfoQuery(input);
            result.setCode(1);
            result.setData(data);
        } catch (BusinessException bex) {
            result.setMessage(bex.getMsg());
        } catch (ClientException cex) {
            result.setMessage(cex.getMsg());
        } catch (Exception unknown) {
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: emergencyInfoQuery \n请求数据: "+gson.toJson(input), unknown);
        }
        return result;
    }

    /**
     * 应急处置批量重发
     */
    @RequestMapping("/EmergencyInfoBatchResend")
    public ResultVO emergencyInfoBatchResend(@RequestBody BatchResendInput input){
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.BatchResendInputValid(input);
        if (validresult.isError()) {
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try{
            String data = server.InfoBatchResend(MessageTypeEnum.EMERDEAL.getType(), input);
            result.setCode(1);
            result.setData(data);
        } catch (BusinessException bex) {
            result.setMessage(bex.getMsg());
        } catch (ClientException cex) {
            result.setMessage(cex.getMsg());
        } catch (Exception unknown) {
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: emergencyInfoBatchResend \n请求数据: "+gson.toJson(input), unknown);
        }
        return result;
    }

    /**
     * 应急处置单条重发
     */
    @RequestMapping("/EmergencyInfoResend")
    public ResultVO emergencyInfoResend(@RequestBody SingleResendInput input){
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.SingleResendInputValid(input);
        if (validresult.isError()) {
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try{
            String data = server.InfoResend(MessageTypeEnum.EMERDEAL.getType(), input);
            result.setCode(1);
            result.setData(data);
        } catch (BusinessException bex) {
            result.setMessage(bex.getMsg());
        } catch (ClientException cex) {
            result.setMessage(cex.getMsg());
        } catch (Exception unknown) {
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: emergencyInfoResend \n请求数据: "+gson.toJson(input), unknown);
        }
        return result;
    }


}
