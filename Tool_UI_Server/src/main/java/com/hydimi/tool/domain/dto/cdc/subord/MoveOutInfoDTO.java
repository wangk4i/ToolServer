package com.hydimi.tool.domain.dto.cdc.subord;

import lombok.Data;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/3 16:51
 */
@Data
public class MoveOutInfoDTO {
    private String MoveInAndOutId;
    private String OutType;
    private String State;
    private String ReportID;
    private String PersonID;
    private String OutOrgZoneCode;
    private String OutOrgCode;
    private String MoveOutTime;
    private String InOrgZoneCode;
    private String InOrgCode;
    private String MoveInTime;
    private String MoveOutCause;
    private String ExpiryTime;
    private String RefuseCause;
}
