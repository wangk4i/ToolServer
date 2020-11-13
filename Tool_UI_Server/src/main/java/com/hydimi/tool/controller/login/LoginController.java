package com.hydimi.tool.controller.login;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hydimi.tool.domain.vo.LoginVO;
import com.hydimi.tool.domain.enumtion.BussinessMsgEnum;
import com.hydimi.tool.domain.info.LoginInfo;
import com.hydimi.tool.domain.vo.ResultVO;
import com.hydimi.tool.domain.vo.ValidateVO;
import com.hydimi.tool.exceptclass.BusinessException;
import com.hydimi.tool.exceptclass.ClientException;
import com.hydimi.tool.service.LoginService;
import com.hydimi.tool.utils.LogUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/11/5 16:39
 */
@RestController
@RequestMapping("/Login")
public class LoginController {

    @Autowired
    private LoginService server;

    private Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
    /**
     * 通过用户名密码登录 省级用户
     */
    @RequestMapping("/UserLogin")
    public ResultVO UserLogin(@RequestBody LoginInfo input){
        ResultVO result = new ResultVO();
        ValidateVO validresult = LoginInfoValid(input);
        if (validresult.isError()) {
            result.setMessage(validresult.getErrMessage());
            return result;
        }
        try{
            LoginVO data = server.userLogin(input);
            result.setData(data);
            result.setCode(1);
        } catch (BusinessException bex) {
            result.setMessage(bex.getMsg());
            return result;
        } catch (ClientException cex) {
            result.setMessage(cex.getMsg());
            return result;
        } catch (Exception unknown){
            result.setMessage(unknown.getMessage());
            LogUtil.error("未知异常 接口: getBasicInfoById \n请求数据: "+gson.toJson(input), unknown);
        }
        return result;

    }

    private ValidateVO LoginInfoValid(LoginInfo input) {
        ValidateVO result = new ValidateVO();
        result.setError(true);
        if (null == input || Strings.isEmpty(input.LoginAccount) || Strings.isEmpty(input.LoginPass)){
            throw new BusinessException(BussinessMsgEnum.PARMETER_NULL_EXCEPTION);
        }
        result.setError(false);
        return result;
    }
}
