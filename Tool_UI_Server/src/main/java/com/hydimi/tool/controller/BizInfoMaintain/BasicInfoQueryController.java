package com.hydimi.tool.controller.BizInfoMaintain;

import com.hydimi.tool.domain.info.OrgcodeInput;
import com.hydimi.tool.domain.info.QueryZoneByLvInput;
import com.hydimi.tool.domain.info.ZoneInfoInput;
import com.hydimi.tool.exceptclass.BusinessException;
import com.hydimi.tool.domain.info.QueryManOrgByZoneInput;
import com.hydimi.tool.exceptclass.ClientException;
import com.hydimi.tool.service.BasicInfoQueryService;
import com.hydimi.tool.validate.BasicInfoQueryValid;
import com.hydimi.tool.domain.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/27 11:19
 */
@Slf4j
@RestController
@RequestMapping("/BasicInfoQuery")
public class BasicInfoQueryController {
    @Autowired
    private BasicInfoQueryService server;
    @Autowired
    private BasicInfoQueryValid valider;

    @RequestMapping("/GetAllZone")
    public ResultVO getAllZone(){
        ResultVO result = new ResultVO();
        try {
            List<ZoneVO> data = server.getAllZone();
            result.setCode(1);
            result.setData(data);
        } catch (Exception unknown) {
            result.setMessage(unknown.getMessage());
            log.error("", unknown);
            return result;
        }
        return result;
    }

    /**
     * 获取省级数据
     */
    @RequestMapping("/QueryProvince")
    public ResultVO queryProvince(){
        ResultVO result = new ResultVO();
        try {
            List<ProvinceVO> data = server.queryProvince();
            result.setCode(1);
            result.setData(data);
        } catch (BusinessException bex){
            result.setMessage(bex.getMsg());
            return result;
        } catch (ClientException cex) {
            result.setMessage(cex.getMsg());
            return result;
        } catch (Exception unknown) {
            result.setMessage(unknown.getMessage());
            log.error("", unknown);
            return result;
        }
        return result;
    }

    /**
     * 获取地区数据
     */
    @RequestMapping("/QueryZoneByLv")
    public ResultVO queryZoneByLv(@RequestBody QueryZoneByLvInput input){
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.queryZoneByLvInputValid(input);
        if (validresult.isError()) {
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try {
            List<SPMZoneVO> data = server.queryZoneByLv(input);
            result.setCode(1);
            result.setData(data);
        } catch (BusinessException bex){
            result.setMessage(bex.getMsg());
            return result;
        } catch (ClientException cex) {
            result.setMessage(cex.getMsg());
            return result;
        } catch (Exception unknown){
            result.setMessage(unknown.getMessage());
            log.error("", unknown);
            return result;
        }
        return result;

    }

    /**
     * 根据地区编码查询下属机构
     */
    @RequestMapping("/QueryManOrgByZone")
    public ResultVO queryManOrgByZone(@RequestBody QueryManOrgByZoneInput input){
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.queryManOrgByZoneInputValid(input);
        if (validresult.isError()) {
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try {
            List<SPMOrganVO> data = server.queryManOrgByZoneCd(input);
            result.setCode(1);
            result.setData(data);
        } catch (BusinessException bex){
            result.setMessage(bex.getMsg());
            return result;
        } catch (ClientException cex) {
            result.setMessage(cex.getMsg());
            return result;
        } catch (Exception unknown) {
            result.setMessage(unknown.getMessage());
            log.error("", unknown);
            return result;
        }
        return result;
    }

    /**
     * 查询机构基层直报员
     * 协同查询
     */
    @RequestMapping("/GetUserInformationByOrgcode")
    public ResultVO getUserInformationByOrgcode(@RequestBody OrgcodeInput input){
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.OrgcodeInputValid(input);
        if (validresult.isError()) {
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try{
            String data = server.getUserInformationByOrgcode(input);
            result.setCode(1);
            result.setData(data);
        } catch (BusinessException bex){
            result.setMessage(bex.getMsg());
            return result;
        } catch (ClientException cex) {
            result.setMessage(cex.getMsg());
            return result;
        } catch (Exception unknown) {
            result.setMessage(unknown.getMessage());
            log.error("", unknown);
            return result;
        }
        return result;

    }


    /**
     * 地区数据查询
     */
    @RequestMapping("/QueryZoneDataByZoneCode")
    public ResultVO queryZoneDataByZoneCode(@RequestBody ZoneInfoInput input){
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.ZoneInfoInputValid(input);
        if (validresult.isError()) {
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try{
            Object data = server.queryZoneDataByZoneCode(input);
            result.setCode(1);
            result.setData(data);
        } catch (BusinessException bex){
            result.setMessage(bex.getMsg());
            return result;
        } catch (ClientException cex) {
            result.setMessage(cex.getMsg());
            return result;
        } catch (Exception unknown) {
            result.setMessage(unknown.getMessage());
            log.error("", unknown);
            return result;
        }
        return result;

    }


    /**
     * 机构数据查询
     */


}
