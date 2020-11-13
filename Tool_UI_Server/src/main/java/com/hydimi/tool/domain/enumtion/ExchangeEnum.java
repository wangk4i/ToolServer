package com.hydimi.tool.domain.enumtion;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/1 14:23
 */
public enum ExchangeEnum {
    MESSAGE(1);

    private final Integer cate;

    ExchangeEnum(Integer cate) {
        this.cate = cate;
    }

    public Integer getCate() {
        return cate;
    }
}
