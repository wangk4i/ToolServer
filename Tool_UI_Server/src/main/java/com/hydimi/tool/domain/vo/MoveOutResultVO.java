package com.hydimi.tool.domain.vo;

import lombok.Data;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/3 16:47
 */
@Data
public class MoveOutResultVO {
    public String FIELDPK;
    public String IDCode;
    public String PatInfoCd;
    public String PatNam;
    public String OutOrgNam;
    public String OutZoneNam;
    public String OutDate;
    public String OutType;
    public String MoveStatusCd;
    public String RefuseCause;
    public String SyncState;

}
