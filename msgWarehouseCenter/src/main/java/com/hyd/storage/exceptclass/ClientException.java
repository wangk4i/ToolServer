package com.hyd.storage.exceptclass;

import com.hyd.storage.utils.LogUtil;
import lombok.Data;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/1 16:22
 */
@Data
public class ClientException extends RuntimeException {
    //默认500
    private Integer code = 500;

    //异常提示信息
    private String msg;

    //异常堆栈信息，默认使用StactException处理
    private Object data;

    private Exception e;


    public ClientException(String msg, Exception e) {
        this.msg = msg;
        this.e = e;
        LogUtil.clientErr(msg, e);
    }
}
