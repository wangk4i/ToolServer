package com.hyd.storage.exceptclass;

import com.hyd.storage.utils.LogUtil;
import lombok.Data;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/11/13 10:11
 */
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

    public BusinessException(String msg, Exception e) {
        this.msg = msg;
        LogUtil.error(msg, e);
    }

}
