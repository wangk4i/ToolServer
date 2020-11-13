package com.hydimi.tool.domain.enumtion;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/11/10 15:11
 */
public enum MessageActionEnum {

    ADD(1, "增加")
    , UPDATE(2, "修改")
    , DELETE(3, "删除")
    , INSPECT(4, "年审")
    , TURNDEATH(5, "转死亡")
    , RECOVER(6, "恢复");



    private final Integer code;

    private final String act;
    MessageActionEnum(Integer code, String act) {
        this.code = code;
        this.act = act;
    }

    public Integer getCode() {
        return code;
    }

    public String getAct() {
        return act;
    }

    public static String getValue(Integer code){
        MessageActionEnum[] messageActionEnums = values();
        for (MessageActionEnum actEnum : messageActionEnums){
            if (actEnum.code.equals(code)){
                return actEnum.act;
            }
        }
        return null;
    }

}
