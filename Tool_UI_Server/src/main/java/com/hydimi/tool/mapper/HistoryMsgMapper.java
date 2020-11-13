package com.hydimi.tool.mapper;

import com.hydimi.tool.domain.dto.HistoricalMsgDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/11/9 16:54
 */
@Mapper
public interface HistoryMsgMapper {

    @Select("select PatID AS MsgID,z.Nam AS ZoneNam,o.Nam AS OrganNam,m.* from MsgInfo.dbo.Exchange_PatInfo(nolock) m\n" +
            "left join sc_mh.dbo.SPM_SPMZone(nolock) z on z.Cd=m.Zone\n" +
            "left join sc_mh.dbo.SPM_SPMOrgan(nolock) o on o.Cd=m.Organ\n" +
            "where PatID =#{msgId} order by BuildTime desc")
    List<HistoricalMsgDTO> queryPatInfoMsg(String msgId);

    @Select("select FollowupID AS MsgID,z.Nam AS ZoneNam,o.Nam AS OrganNam,m.* from MsgInfo.dbo.Exchange_PatFollowup(nolock) m\n" +
            "left join sc_mh.dbo.SPM_SPMZone(nolock) z on z.Cd=m.Zone\n" +
            "left join sc_mh.dbo.SPM_SPMOrgan(nolock) o on o.Cd=m.Organ\n" +
            "where FollowupID=#{msgId} order by BuildTime desc")
    List<HistoricalMsgDTO> queryFollowupMsg(String msgId);

    @Select("select ReportCardID AS MsgID,z.Nam AS ZoneNam,o.Nam AS OrganNam,m.* from MsgInfo.dbo.Exchange_PatReportCard(nolock) m\n" +
            "left join sc_mh.dbo.SPM_SPMZone(nolock) z on z.Cd=m.Zone\n" +
            "left join sc_mh.dbo.SPM_SPMOrgan(nolock) o on o.Cd=m.Organ\n" +
            "where ReportCardID=#{msgId} order by BuildTime desc")
    List<HistoricalMsgDTO> queryReportMsg(String msgId);

    @Select("select LeaveCardID AS MsgID,z.Nam AS ZoneNam,o.Nam AS OrganNam,m.* from MsgInfo.dbo.Exchange_PatLeaveCard(nolock) m\n" +
            "left join sc_mh.dbo.SPM_SPMZone(nolock) z on z.Cd=m.Zone\n" +
            "left join sc_mh.dbo.SPM_SPMOrgan(nolock) o on o.Cd=m.Organ\n" +
            "where LeaveCardID=#{msgId} order by BuildTime desc")
    List<HistoricalMsgDTO> queryDischargeMsg(String msgId);

    @Select("select EmergID AS MsgID,z.Nam AS ZoneNam,o.Nam AS OrganNam,m.* from MsgInfo.dbo.Exchange_PatEmergency(nolock) m\n" +
            "left join sc_mh.dbo.SPM_SPMZone(nolock) z on z.Cd=m.Zone\n" +
            "left join sc_mh.dbo.SPM_SPMOrgan(nolock) o on o.Cd=m.Organ\n" +
            "where EmergID=#{msgId} order by BuildTime desc")
    List<HistoricalMsgDTO> queryEmergencyMsg(String msgId);

}
