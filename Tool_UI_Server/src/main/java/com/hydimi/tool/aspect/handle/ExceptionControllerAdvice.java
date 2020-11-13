package com.hydimi.tool.aspect.handle;

import com.hydimi.tool.domain.enumtion.ResultCode;
import com.hydimi.tool.domain.vo.ResultVO;
import com.hydimi.tool.exceptclass.BusinessException;
import com.hydimi.tool.exceptclass.ClientException;
import com.hydimi.tool.utils.LogUtil;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/11/9 10:55
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO exceptionHandler(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        StringBuffer sb = new StringBuffer();
        fieldErrors.forEach(item->{
            sb.append(item.getDefaultMessage()).append(";");
        });
        String errMessage = sb.toString().substring(0, sb.length()-1);
        // 将错误信息返回前台
        return ResultVO.error(ResultCode.PARAM_EXCEPTION, errMessage);
    }

    @ExceptionHandler(ClientException.class)
    public ResultVO clientExceptionHandleer(ClientException e){
        LogUtil.clientErr(e.getMsg(), e.getE());
        return ResultVO.error(ResultCode.SYS_EXCEPTION, e.getMsg());
    }

    @ExceptionHandler(BusinessException.class)
    public ResultVO businessExceptionHandleer(BusinessException e){
        LogUtil.bizErr(e.getMsg());
        return ResultVO.error(ResultCode.SYS_EXCEPTION, e.getMsg());
    }

}
