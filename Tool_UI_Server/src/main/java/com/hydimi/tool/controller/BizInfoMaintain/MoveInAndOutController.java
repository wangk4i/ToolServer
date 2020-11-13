package com.hydimi.tool.controller.BizInfoMaintain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hydimi.tool.domain.dto.cdc.PersonDTO;
import com.hydimi.tool.domain.enumtion.GWInfoEnum;
import com.hydimi.tool.domain.info.CallBackInput;
import com.hydimi.tool.domain.info.PatInfoMoveInInput;
import com.hydimi.tool.domain.info.QueryMoveOutByIdcodeInput;
import com.hydimi.tool.domain.info.QueryMoveOutInput;
import com.hydimi.tool.domain.vo.*;
import com.hydimi.tool.exceptclass.BusinessException;
import com.hydimi.tool.exceptclass.ClientException;
import com.hydimi.tool.service.MoveInAndOutService;
import com.hydimi.tool.utils.LogUtil;
import com.hydimi.tool.validate.MoveInAndOutValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/3 16:28
 */
@RestController
@RequestMapping("/MoveInAndOut")
public class MoveInAndOutController {
    @Autowired
    private MoveInAndOutValid valider;
    @Autowired
    private MoveInAndOutService server;
    private Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    /**
     * 患者基本信息跨省迁出查询
     */
    @RequestMapping("/GetMoveOutResult")
    public ResultVO getMoveOutResult(@RequestBody QueryMoveOutInput input){
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.QueryMoveOutInputValid(input);
        if (validresult.isError()){
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try {
            List<MoveOutResultVO> data = server.getMoveOutResult(input);
            result.setCode(1);
            result.setData(data);
        } catch (BusinessException bex) {
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
     * 患者上挂撤回
     * 1.查询流转信息
     */
    @RequestMapping("/QueryMoveOutInformation")
    public ResultVO queryMoveOutInformation(@RequestBody QueryMoveOutByIdcodeInput input){
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.QueryMoveOutByIdcodeInputValid(input);
        if (validresult.isError()){
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try{
            List<MoveOutPatVO> data = server.queryMoveOutInformation(input);
            result.setCode(1);
            result.setData(data);
        } catch (BusinessException bex) {
            result.setMessage(bex.getMsg());
            return result;
        } catch (ClientException cex){
            result.setMessage(cex.getMsg());
            return result;
        } catch (Exception unknown) {
            // 记录未知异常
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: queryMoveOutInformation \n请求数据: "+gson.toJson(input), unknown);
            return result;
        }
        return result;

    }

    /**
     * 2.上挂回收
     */
    @RequestMapping("/CallBackMove")
    public ResultVO callBackMove(@RequestBody CallBackInput input){
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.CallBackInputValid(input);
        if (validresult.isError()){
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try{
            String id = server.callBackMove(input);
            result.setCode(1);
            result.setData(id);
        } catch (BusinessException bex) {
            result.setMessage(bex.getMsg());
            return result;
        } catch (ClientException cex){
            result.setMessage(cex.getMsg());
            return result;
        } catch (Exception unknown) {
            // 记录未知异常
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: callBackMove \n请求数据: "+gson.toJson(input), unknown);
            return result;
        }
        return result;

    }

    /**
     * 获取正迁入到本省的患者信息
     */
    @RequestMapping("/QueyrPatInfoByMoveCd")
    public ResultVO queyrPatInfoByMoveCd(@RequestBody PatInfoMoveInInput input){
        ResultVO result = new ResultVO();
        ValidateVO validresult = valider.PatInfoMoveInInputValid(input);
        if (validresult.isError()){
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try{
            PersonDTO data = server.queyrPatInfoByMoveCd(input);
            result.setCode(1);
            result.setData(data);
            result.setInfoMap(GWInfoEnum.patientMap2);
        } catch (BusinessException bex) {
            result.setMessage(bex.getMsg());
            return result;
        } catch (ClientException cex){
            result.setMessage(cex.getMsg());
            return result;
        } catch (Exception unknown) {
            // 记录未知异常
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: queyrPatInfoByMoveCd \n请求数据: "+gson.toJson(input), unknown);
            return result;
        }
        return result;
    }




}
