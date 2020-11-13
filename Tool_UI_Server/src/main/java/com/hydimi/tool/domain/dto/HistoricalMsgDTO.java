package com.hydimi.tool.domain.dto;

import lombok.Data;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/11/9 16:56
 */
@Data
public class HistoricalMsgDTO {
    public String MsgFileID;
    public String MsgID;
    public String PatID;
    public String ReportCardID;
    public String Zone;
    public String ZoneNam;
    public String Organ;
    public String OrganNam;
    public Integer LocalAction;
    public Integer GWAction;
    public String ActionNam;
    public String TrigerTime;
    public String BuildTime;
    public String ReceivedTime;
    public String WriteTime;
    public Integer IsLocal;
    public String Status;
    public String ErrMsg;
    public String Operator;


}
