package com.hydimi.tool.domain.enumtion;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/2 10:55
 */
public enum SexEnum {
    MALE("1"), FEMALE("2");

    private final String sex;

    SexEnum(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }
}
