package com.hydimi.tool.domain.info;

import lombok.Data;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/10/19 12:00
 */
@Data
public class PatInfoQueryInput {
    public String starttime;
    public String endtime;
    public String actionType;
    public String syncStatus;
    public String IDCode;
    public String SyncErr;

}
