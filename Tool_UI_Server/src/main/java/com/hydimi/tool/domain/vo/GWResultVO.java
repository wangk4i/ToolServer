package com.hydimi.tool.domain.vo;

import lombok.Data;


/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/31 18:31
 */
@Data
public class GWResultVO<T> {
    public Integer code ;
    public String message ;
    public T data ;

}
