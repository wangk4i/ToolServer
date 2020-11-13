package com.hyd.storage.domain.info;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/11/12 10:17
 */
public class SystemOperatorEnum {

    public final static CopyOnWriteArrayList<String> UserIdList = new CopyOnWriteArrayList<>();

    static {
        UserIdList.add("四川运维");
        UserIdList.add("U17091300001");
        UserIdList.add("U18121100005");
        UserIdList.add("U20040200001");
        UserIdList.add("U19031400004");
        UserIdList.add("U19031400005");
        UserIdList.add("U18080600003");
        UserIdList.add("U18081400023");
        UserIdList.add("U18081500022");
        UserIdList.add("U19121300014");
        UserIdList.add("U202011100002");
    }
}
