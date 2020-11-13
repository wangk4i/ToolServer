package com.hydimi.tool.domain.enumtion;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/1 11:46
 */
public enum MessageTypeEnum {
    PATINFO(1), FOLLOWUP(2), REPORTCARD(3), LEFTCARD(4), EMERDEAL(5);

    private final Integer type;

    MessageTypeEnum(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
