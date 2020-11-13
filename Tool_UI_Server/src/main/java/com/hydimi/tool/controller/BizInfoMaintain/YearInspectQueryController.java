package com.hydimi.tool.controller.BizInfoMaintain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hydimi.tool.domain.info.InspectYearInfo;
import com.hydimi.tool.domain.vo.ResultVO;
import com.hydimi.tool.domain.vo.ValidateVO;
import com.hydimi.tool.service.YearInspectQueryService;
import com.hydimi.tool.utils.LogUtil;
import com.hydimi.tool.validate.YearInspectQueryValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/3 16:30
 */
@RestController
@RequestMapping("/YearInspectQuery")
public class YearInspectQueryController {

    @Autowired
    private YearInspectQueryService server;
    @Autowired
    private YearInspectQueryValid valider;

    private Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

    /**
     * 年审时间查询
     */
    @RequestMapping("/queryPatInspectYear")
    public ResultVO queryPatInspectYear(){
        ResultVO result = new ResultVO();
        try{
            List<InspectYearInfo> data = server.queryPatInspectYear();
            result.setData(data);
            result.setCode(1);
        } catch (Exception unknown){
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: queryPatInspectYear \n", unknown);
        }
        return result;
    }

    /**
     * 年审详情查询
     */
    @RequestMapping("/queryPatInspectYearDetails")
    public ResultVO queryPatInspectYearDetails(@RequestBody InspectYearInfo input){
        ResultVO result = new ResultVO();
        try{
            List<InspectYearInfo> data = server.queryPatInspectYearDetails(input);
            result.setData(data);
            result.setCode(1);
        } catch (Exception unknown){
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: queryPatInspectYearDetails \n请求数据: "+gson.toJson(input), unknown);
        }
        return result;

    }

    /**
     * 年审死亡查询
     */
    @RequestMapping("/queryPatInspectYearDeath")
    public ResultVO queryPatInspectYearDeath(){
        ResultVO result = new ResultVO();
        try{
            List<InspectYearInfo> data = server.queryPatInspectYearDeath();
            result.setData(data);
            result.setCode(1);
        } catch (Exception unknown){
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: queryPatInspectYearDeath \n", unknown);
        }
        return result;

    }

    /**
     * 年审删除查询
     */
    @RequestMapping("/queryPatInspectYearDelete")
    public ResultVO queryPatInspectYearDelete(){
        ResultVO result = new ResultVO();
        try{
            List<InspectYearInfo> data = server.queryPatInspectYearDelete();
            result.setData(data);
            result.setCode(1);
        } catch (Exception unknown){
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: queryPatInspectYearDelete \n", unknown);
        }
        return result;

    }


}
