package com.hydimi.tool.domain.vo;

import lombok.Data;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/7 9:54
 */
@Data
public class SyncFollowupVO {
    public String Cd;
    public String PatNam;
    public String ZoneNam;
    public String OrgNam;
    public String IDCode;
    public String PatCode;
    public String GenderCd;
    public String BirthDate;
    public String InspectYear;
    public String SyncError;
    public String LastModTime;
    public String FollowError;
    public String FollowNoSync;
    public String BaseStatusCd;
    public String OutDate;
    public String DelStatus;
    public String SyncTime;
    public String count;
}
