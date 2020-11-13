package com.hydimi.tool.domain.enumtion;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/11/5 16:43
 */

/**
 * 业务异常提示信息枚举类
 */
public enum BussinessMsgEnum {

    /** 参数异常 */
    PARMETER_EXCEPTION(102, "参数异常!"),

    PARMETER_NULL_EXCEPTION(103, "必填参数未传入！"),

    /** 等待超时 */
    SERVICE_TIME_OUT(104, "服务调用超时！"),

    /** 参数过大 */
    PARMETER_BIG_EXCEPTION(105, "传入参数超出范围！"),

    SQL_EXCT_EXCEPTION(106, "SQL执行异常！"),

    SQL_TIME_OUT(107, "数据库连接异常！");


    private final Integer code;

    private final String msg;

    BussinessMsgEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
