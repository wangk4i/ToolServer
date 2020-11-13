package com.hydimi.tool.service;

import com.hydimi.tool.domain.vo.LoginVO;
import com.hydimi.tool.domain.info.LoginInfo;
import com.hydimi.tool.exceptclass.BusinessException;
import com.hydimi.tool.exceptclass.ClientException;
import com.hydimi.tool.mapper.LoginMapper;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/11/5 16:40
 */
@Service
public class LoginService {

    @Autowired(required = false)
    private LoginMapper mapper;



    public LoginVO userLogin(LoginInfo input) {
        LoginVO result = null;
        try {
            result = mapper.userQuery(input);
        } catch (MyBatisSystemException e) {
            throw new ClientException("系统异常，请联系管理员", e);
        }
        if (null == result){
            throw new BusinessException("用户名或密码错误");
        }


        return result;
    }
}
