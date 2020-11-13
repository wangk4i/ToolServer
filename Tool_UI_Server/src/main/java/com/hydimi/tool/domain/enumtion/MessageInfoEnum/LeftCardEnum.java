package com.hydimi.tool.domain.enumtion.MessageInfoEnum;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/1 11:44
 */
public enum LeftCardEnum {
    ADD(1), UPDATE(2), DELETE(3);

    private final Integer action;

    LeftCardEnum(Integer action) {
        this.action = action;
    }

    public Integer getAction() {
        return action;
    }
}
