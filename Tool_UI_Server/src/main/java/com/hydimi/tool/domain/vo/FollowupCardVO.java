package com.hydimi.tool.domain.vo;

import lombok.Data;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/7 14:41
 */
@Data
public class FollowupCardVO {
    public String Cd;
    public String FollowupDate;
    public String NextDate;
    public String CaseClassCd;
    public String FIELDPK;
    public String DelStatus;
    public String SyncStatus;
    public String SyncTime;
    public String SyncError;

    public String ZoneCd;
    public String OrganCd;

}
