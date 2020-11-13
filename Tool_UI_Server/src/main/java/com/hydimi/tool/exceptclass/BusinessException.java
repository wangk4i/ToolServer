package com.hydimi.tool.exceptclass;

import com.hydimi.tool.domain.enumtion.BussinessMsgEnum;
import com.hydimi.tool.utils.LogUtil;
import lombok.Data;

@Data
public class BusinessException extends RuntimeException{

    //默认500
    private Integer code = 500;

    //异常提示信息
    private String msg;

    //异常堆栈信息，默认使用StactException处理
    private Object data;


    public BusinessException(String msg) {
        this.msg = msg;
        this.code = -1;
        LogUtil.warn(msg);
    }

    public BusinessException(BussinessMsgEnum msgEnum, String... strArr){
        this.code = msgEnum.getCode();
        this.msg = msgEnum.getMsg();
        for (String x : strArr){
            this.msg += x;
        }

    }

    public BusinessException(String msg, Exception e) {
        this.msg = msg;
        LogUtil.error(msg, e);
    }


}
