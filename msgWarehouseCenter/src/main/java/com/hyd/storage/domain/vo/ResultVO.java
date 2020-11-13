package com.hyd.storage.domain.vo;

import lombok.Data;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/11/12 17:59
 */
@Data
public class ResultVO<T> {
    private Integer code;
    private String message;
    private T data;

    public ResultVO() {
        this.code = -1;
    }

    /**
     * 用于错误处理
     * @param code 错误码
     * @param message 错误提示信息
     */
    public ResultVO(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public static ResultVO<String> error(Integer code, String msg){
        return new ResultVO<>(code, msg);
    }

    public static <T> ResultVO<T> success(T data){
        ResultVO resultVO = new ResultVO();
        resultVO.setData(data);
        resultVO.setCode(1);
        return resultVO;
    }
}