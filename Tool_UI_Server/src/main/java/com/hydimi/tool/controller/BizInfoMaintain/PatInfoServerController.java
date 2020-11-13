package com.hydimi.tool.controller.BizInfoMaintain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hydimi.tool.domain.dto.cdc.PersonDTO;
import com.hydimi.tool.domain.enumtion.GWInfoEnum;
import com.hydimi.tool.domain.info.TurnDeathInput;
import com.hydimi.tool.domain.dto.SPMPatDTO;
import com.hydimi.tool.domain.info.*;
import com.hydimi.tool.domain.vo.*;
import com.hydimi.tool.exceptclass.BusinessException;
import com.hydimi.tool.exceptclass.ClientException;
import com.hydimi.tool.service.PatInfoServerService;
import com.hydimi.tool.utils.LogUtil;
import com.hydimi.tool.validate.PatInfoServerValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/27 11:09
 */
@RestController
@RequestMapping("/PatInfoServer")
public class PatInfoServerController {

    @Autowired
    private PatInfoServerValid valider;
    @Autowired
    private PatInfoServerService server;

    private Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();


    /**
     * 患者查重
     */
    @RequestMapping("/GetRepeatInfoByIdcode")
    public ResultVO getRepeatInfoByIdcode(@RequestBody GetRepeatInfoByIdcodeInput input){
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.getRepeatInfoByIdcodeInputValid(input);
        if (validresult.isError()) {
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try {
            List<RepeatPersonVO> data = server.getRepeatInfoByIdcode(input);
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
            LogUtil.error("未知异常 接口: getRepeatInfoByIdcode \n请求数据: "+gson.toJson(input), unknown);
            return result;
        }
        return result;
    }

    /**
     * 患者删除
     */
    @RequestMapping("/DeleteBasicInformation")
    public ResultVO deleteBasicInformation(@RequestBody DeleteBaseInput input){
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.DeleteBaseInputValid(input);
        if (validresult.isError()){
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try {
            String data = server.deleteBasicInformation(input);
            result.setCode(1);
            result.setData(data);
        } catch (BusinessException bex) {
            // 已知可能出现的异常，在Service中记录
            result.setMessage(bex.getMsg());
            return result;
        } catch (ClientException cex){
            result.setMessage(cex.getMsg());
            return result;
        } catch (Exception unknown) {
            // 记录未知异常
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: deleteBasicInformation \n请求数据: "+gson.toJson(input), unknown);
            return result;
        }
        return result;

    }

    /**
     * 患者恢复
     */
    @RequestMapping("/RecoveryBasicInformation")
    public ResultVO recoveryBasicInformation(@RequestBody RecoveryBaseInput input){
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.RecoveryBaseInputValid(input);
        if (validresult.isError()){
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try {
            String data = server.recoveryBasicInformation(input);
            result.setCode(1);
            result.setData(data);
        }catch (BusinessException bex) {
            // 已知可能出现的异常，在Service中记录
            result.setMessage(bex.getMsg());
            return result;
        } catch (ClientException cex){
            result.setMessage(cex.getMsg());
            return result;
        } catch (Exception unknown) {
            // 记录未知异常
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: recoveryBasicInformation \n请求数据: "+gson.toJson(input), unknown);
            return result;
        }
        return result;

    }


    /**
     * 根据身份证/主键 查询患者信息
     */
    @RequestMapping("/QueryBasicInformation")
    public ResultVO queryBasicInformation(@RequestBody QueryBaseInput input){
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.QueryBaseInputValid(input);
        if (validresult.isError()){
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try {
            SPMPatDTO data = server.queryBasicInformation(input);
            result.setCode(1);
            result.setData(data);
        }catch (BusinessException bex) {
            // 已知可能出现的异常，在Service中记录
            result.setMessage(bex.getMsg());
            return result;
        } catch (ClientException cex){
            result.setMessage(cex.getMsg());
            return result;
        } catch (Exception unknown) {
            // 记录未知异常
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: queryBasicInformation \n请求数据: "+gson.toJson(input), unknown);
            return result;
        }
        return result;

    }

    /**
     * 患者同步
     */
    @RequestMapping("/SyncBasicInformation")
    public ResultVO syncBasicInformation(@RequestBody PatInfo input){
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.PatInfoValid(input);
        if (validresult.isError()){
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try {
            String message = server.syncBasicInformation(input);
            result.setCode(1);
            result.setMessage(message);
        }catch (BusinessException bex) {
            // 已知可能出现的异常，在Service中记录
            result.setMessage(bex.getMsg());
            return result;
        } catch (ClientException cex){
            result.setMessage(cex.getMsg());
            return result;
        } catch (Exception unknown) {
            // 记录未知异常
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: syncBasicInformation \n请求数据: "+gson.toJson(input), unknown);
            return result;
        }
        return result;

    }

    /**
     * 调国网接口，根据主键查患者
     */
    @RequestMapping("/QueryPatInfoMana")
    public ResultVO queryPatInfoMana(@RequestBody QueryPatByPkInput input){
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.QueryPatByPkInputValid(input);
        if (validresult.isError()){
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try {
            PersonDTO data = server.queryPatInfoMana(input);
            result.setCode(1);
            result.setData(data);
            result.setInfoMap(GWInfoEnum.patientMap);
        } catch (BusinessException bex) {
            // 已知可能出现的异常，在Service中记录
            result.setMessage(bex.getMsg());
            return result;
        } catch (ClientException cex){
            result.setMessage(cex.getMsg());
            return result;
        } catch (Exception unknown) {
            // 记录未知异常
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: queryPatInfoMana \n请求数据: "+gson.toJson(input), unknown);
            return result;
        }
        return result;

    }



    /**
     * 死亡患者同步
     */
    @RequestMapping("/PatTurnDeath")
    public ResultVO patTurnDeath(@RequestBody TurnDeathInput input){
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.TurnDeathInputValid(input);
        if (validresult.isError()){
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try{
            String message = server.patTurnDeath(input);
            result.setCode(1);
            result.setMessage(message);
        } catch (BusinessException bex) {
            // 已知可能出现的异常，在Service中记录
            result.setMessage(bex.getMsg());
            return result;
        } catch (ClientException cex){
            result.setMessage(cex.getMsg());
            return result;
        } catch (Exception unknown) {
            // 记录未知异常
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: queryPatInfoMana \n请求数据: "+gson.toJson(input), unknown);
            return result;
        }
        return result;

    }



}
