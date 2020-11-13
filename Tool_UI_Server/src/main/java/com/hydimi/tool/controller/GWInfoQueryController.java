package com.hydimi.tool.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hydimi.tool.domain.dto.cdc.*;
import com.hydimi.tool.domain.dto.cdc.subord.CaseReportDTO;
import com.hydimi.tool.domain.dto.cdc.subord.DischargeInfoDTO;
import com.hydimi.tool.domain.dto.cdc.subord.EmergencyInfoDTO;
import com.hydimi.tool.domain.enumtion.GWInfoEnum;
import com.hydimi.tool.domain.info.*;
import com.hydimi.tool.domain.vo.ResultVO;
import com.hydimi.tool.domain.vo.ValidateVO;
import com.hydimi.tool.exceptclass.BusinessException;
import com.hydimi.tool.exceptclass.ClientException;
import com.hydimi.tool.service.GWInfoQueryService;
import com.hydimi.tool.utils.LogUtil;
import com.hydimi.tool.validate.GWInfoQueryValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/9 9:29
 * 查询协同服务国网数据
 */
@RestController
@RequestMapping("/GWInfoQuery")
public class GWInfoQueryController {

    private Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    @Autowired
    private GWInfoQueryService server;
    @Autowired
    private GWInfoQueryValid valider;

    /**
     * 患者信息查询
     */
    @RequestMapping("/GetBasicInfoById")
    public ResultVO getBasicInfoById(@RequestBody PatIdInfo input){
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.PatIdInputValid(input);
        if (validresult.isError()) {
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try{
            PersonDTO data = server.getBasicInfoById(input);
            result.setCode(1);
            result.setData(data);
            result.setInfoMap(GWInfoEnum.patientMap);
        } catch (BusinessException bex) {
            result.setMessage(bex.getMsg());
            return result;
        } catch (ClientException cex) {
            result.setMessage(cex.getMsg());
            return result;
        } catch (Exception unknown) {
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: getBasicInfoById \n请求数据: "+gson.toJson(input), unknown);
            return result;
        }
        return result;
    }

    /**
     * 随访信息查询
     */
    @RequestMapping("/GetRelatedFollowupInfo")
    public ResultVO getRelatedFollowupInfo(@RequestBody PatIdInfo input){
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.PatIdInputValid(input);
        if (validresult.isError()) {
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try{
            FollowupInfoListDTO data = server.getRelatedFollowupInfo(input);
            result.setCode(1);
            result.setData(data);
            result.setInfoMap(GWInfoEnum.followupMap);
        } catch (BusinessException bex) {
            result.setMessage(bex.getMsg());
            return result;
        } catch (ClientException cex) {
            result.setMessage(cex.getMsg());
            return result;
        } catch (Exception unknown) {
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: getRelatedFollowupInfo \n请求数据: "+gson.toJson(input), unknown);
            return result;
        }
        return result;
    }

    /**
     * 报告卡信息查询
     */
    @RequestMapping("/GetNewCaseReportById")
    public ResultVO getNewCaseReportById(@RequestBody CaseReportInput input) {
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.CaseReportInputValid(input);
        if (validresult.isError()) {
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try{
            CaseReportDTO data = server.getNewCaseReportById(input);
            result.setCode(1);
            result.setData(data);
            result.setInfoMap(GWInfoEnum.reportMap);
        } catch (BusinessException bex) {
            result.setMessage(bex.getMsg());
            return result;
        } catch (ClientException cex) {
            result.setMessage(cex.getMsg());
            return result;
        } catch (Exception unknown) {
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: getNewCaseReportById \n请求数据: "+gson.toJson(input), unknown);
            return result;
        }
        return result;

    }

        /**
     * 出院信息查询
     */
    @RequestMapping("/GetDischargeInfoById")
    public ResultVO getDischargeInfoById(@RequestBody DischargeInfoInput input) {
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.DischargeInfoInputValid(input);
        if (validresult.isError()) {
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try{
            DischargeInfoDTO data = server.getDischargeInfoById(input);
            result.setCode(1);
            result.setData(data);
            result.setInfoMap(GWInfoEnum.leftcardMap);
        } catch (BusinessException bex) {
            result.setMessage(bex.getMsg());
            return result;
        } catch (ClientException cex) {
            result.setMessage(cex.getMsg());
            return result;
        } catch (Exception unknown) {
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: getDischargeInfoById \n请求数据: "+gson.toJson(input), unknown);
            return result;
        }
        return result;
    }

    /**
     * 应急处置查询
     */
    @RequestMapping("/GetEmerDealById")
    public ResultVO getEmerDealById(@RequestBody EmergencyInput input) {
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.EmergencyInputValid(input);
        if (validresult.isError()) {
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try{
            EmergencyInfoDTO data = server.GetEmerDealById(input);
            result.setCode(1);
            result.setData(data);
            result.setInfoMap(GWInfoEnum.emergencyMap);
        } catch (BusinessException bex) {
            result.setMessage(bex.getMsg());
            return result;
        } catch (ClientException cex) {
            result.setMessage(cex.getMsg());
            return result;
        } catch (Exception unknown) {
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: getEmerDealById \n请求数据: "+gson.toJson(input), unknown);
            return result;
        }
        return result;
    }

    /**
     * 根据患者主键查询报告卡信息
     */
    @RequestMapping("/GetRelatedNewCaseReport")
    public ResultVO getRelatedNewCaseReport(@RequestBody PatIdInfo input){
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.PatIdInputValid(input);
        if (validresult.isError()) {
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try{
            CaseReportListDTO data = server.getRelatedNewCaseReport(input);
            result.setCode(1);
            result.setData(data);
            result.setInfoMap(GWInfoEnum.reportMap);
        } catch (BusinessException bex) {
            result.setMessage(bex.getMsg());
            return result;
        } catch (ClientException cex) {
            result.setMessage(cex.getMsg());
            return result;
        } catch (Exception unknown) {
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: getRelatedNewCaseReport \n请求数据: "+gson.toJson(input), unknown);
            return result;
        }
        return result;
    }

    /**
     * 根据患者主键查询出院单信息
     */
    @RequestMapping("/GetRelatedDischargeInformation")
    public ResultVO getRelatedDischargeInformation(@RequestBody PatIdInfo input){
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.PatIdInputValid(input);
        if (validresult.isError()) {
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try{
            DischargeInfoListDTO data = server.getRelatedDischargeInformation(input);
            result.setCode(1);
            result.setData(data);
            result.setInfoMap(GWInfoEnum.leftcardMap);
        } catch (BusinessException bex) {
            result.setMessage(bex.getMsg());
            return result;
        } catch (ClientException cex) {
            result.setMessage(cex.getMsg());
            return result;
        } catch (Exception unknown) {
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: getRelatedDischargeInformation \n请求数据: "+gson.toJson(input), unknown);
            return result;
        }
        return result;
    }

    /**
     * 根据患者身份证号查询应急处置信息
     */
    @RequestMapping("/GetRelatedEmergencyInformation")
    public ResultVO getRelatedEmergencyInformation(@RequestBody PatIdInfo input){
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.PatEmerInputValid(input);
        if (validresult.isError()) {
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try{
            EmergencyInfoListDTO data = server.getRelatedEmergencyInformation(input);
            result.setCode(1);
            result.setData(data);
            result.setInfoMap(GWInfoEnum.emergencyMap);
        } catch (BusinessException bex) {
            result.setMessage(bex.getMsg());
            return result;
        } catch (ClientException cex) {
            result.setMessage(cex.getMsg());
            return result;
        } catch (Exception unknown) {
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: getRelatedEmergencyInformation \n请求数据: "+gson.toJson(input), unknown);
            return result;
        }
        return result;
    }


}
