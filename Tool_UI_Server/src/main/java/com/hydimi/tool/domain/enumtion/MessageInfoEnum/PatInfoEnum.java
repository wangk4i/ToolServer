package com.hydimi.tool.domain.enumtion.MessageInfoEnum;


/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/1 11:31
 */
public enum PatInfoEnum {
    ADD(1), UPDATE(2), DELETE(3), INSPECT(4), TRUNDEATH(5), RESTORE(6);

    private final Integer action;

    PatInfoEnum(Integer action) {
        this.action = action;
    }

    public Integer getAction() {
        return action;
    }
}
