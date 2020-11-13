package com.hyd.storage.mapper;

import com.hyd.storage.domain.info.MessageInfo;
import org.apache.ibatis.annotations.*;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/10/29 15:59
 */
@Mapper
public interface WriteBackMapper {

    @Select("select COUNT(1) from Exchange_PatInfo (nolock) where MsgFileID = #{id}")
    int existPatInfoMsg(String id);

    @Select("select COUNT(1) from Exchange_PatFollowup (nolock) where MsgFileID = #{id}")
    int existFollowupMsg(String id);

    @Select("select COUNT(1) from Exchange_PatReportCard (nolock) where MsgFileID = #{id}")
    int existCaseReportMsg(String id);

    @Select("select COUNT(1) from Exchange_PatLeaveCard (nolock) where MsgFileID = #{id}")
    int existLeftCardMsg(String id);

    @Select("select COUNT(1) from Exchange_PatEmergency (nolock) where MsgFileID = #{id}")
    int existEmergencyMsg(String id);

    @Insert("insert into Exchange_PatInfo (MsgFileID,PatID,Zone,Organ,LocalAction,GWAction,TrigerTime,BuildTime,ReceivedTime,WriteTime,IsLocal,Status,ErrMsg,Origin) " +
            "values (#{info.fileNam}, #{info.id}, #{info.zone}, #{info.organ}, #{info.msgaction}, #{info.gwaction}, #{info.extensionInfo.starttime}, #{info.fileTime}" +
            ", #{info.gwReceiveTime}, #{info.writeTime}, #{info.islocal}, #{info.extensionInfo.result}, #{info.extensionInfo.desc}, #{info.origin})")
    void patInfoWrite(@Param("info") MessageInfo info);

    @Insert("insert into Exchange_PatFollowup (MsgFileID,FollowupID,PatID,Zone,Organ,LocalAction,GWAction,TrigerTime,BuildTime,ReceivedTime,WriteTime,IsLocal,Status,ErrMsg,Origin) " +
            "values (#{info.fileNam}, #{info.id}, #{info.extensionInfo.patid}, #{info.zone}, #{info.organ}, #{info.msgaction}, #{info.gwaction}, #{info.extensionInfo.starttime}" +
            ", #{info.fileTime}, #{info.gwReceiveTime}, #{info.writeTime}, #{info.islocal}, #{info.extensionInfo.result}, #{info.extensionInfo.desc}, #{info.origin})")
    void followupWrite(@Param("info") MessageInfo info);

    @Insert("insert into Exchange_PatReportCard (MsgFileID,ReportCardID,PatID,Zone,Organ,LocalAction,GWAction,TrigerTime,BuildTime,ReceivedTime,WriteTime,IsLocal,Status,ErrMsg,Origin) " +
            "values (#{info.fileNam}, #{info.id}, #{info.extensionInfo.patid}, #{info.zone}, #{info.organ}, #{info.msgaction}, #{info.gwaction}, #{info.extensionInfo.starttime}" +
            ", #{info.fileTime}, #{info.gwReceiveTime}, #{info.writeTime}, #{info.islocal}, #{info.extensionInfo.result}, #{info.extensionInfo.desc}, #{info.origin})")
    void caseReportWrite(@Param("info") MessageInfo info);

    @Insert("insert into Exchange_PatLeaveCard (MsgFileID,LeaveCardID,ReportCardID,PatID,Zone,Organ,LocalAction,GWAction,TrigerTime,BuildTime,ReceivedTime,WriteTime,IsLocal,Status,ErrMsg,Origin)" +
            " values (#{info.fileNam}, #{info.id}, #{info.extensionInfo.reportid}, #{info.extensionInfo.patid}, #{info.zone}, #{info.organ}, #{info.msgaction}, #{info.gwaction}" +
            ", #{info.extensionInfo.starttime}, #{info.fileTime}, #{info.gwReceiveTime}, #{info.writeTime}, #{info.islocal}, #{info.extensionInfo.result}, #{info.extensionInfo.desc}, #{info.origin})")
    void leftCardWrite(@Param("info") MessageInfo info);

    @Insert("insert into Exchange_PatEmergency (MsgFileID,EmergID,PatID,Zone,Organ,LocalAction,GWAction,TrigerTime,BuildTime,ReceivedTime,WriteTime,IsLocal,Status,ErrMsg,Origin)" +
            " values (#{info.fileNam}, #{info.id}, #{info.extensionInfo.patid}, #{info.zone}, #{info.organ}, #{info.msgaction}, #{info.gwaction}, #{info.extensionInfo.starttime}" +
            ", #{info.fileTime}, #{info.gwReceiveTime}, #{info.writeTime}, #{info.islocal}, #{info.extensionInfo.result}, #{info.extensionInfo.desc}, #{info.origin})")
    void emergencyWrite(@Param("info") MessageInfo info);

    @Update("update Exchange_PatInfo with(rowlock) set ReceivedTime=#{info.gwReceiveTime}, WriteTime=#{info.writeTime}, IsLocal=#{info.islocal}, Status=#{info.extensionInfo.result}, ErrMsg=#{info.extensionInfo.desc}" +
            ", Origin=#{info.origin} where MsgFileID=#{info.fileNam}")
    void patInfoReWrite(@Param("info") MessageInfo info);

    @Update("update Exchange_PatFollowup with(rowlock) set ReceivedTime=#{info.gwReceiveTime}, WriteTime=#{info.writeTime}, IsLocal=#{info.islocal}, Status=#{info.extensionInfo.result}, ErrMsg=#{info.extensionInfo.desc}" +
            ", Origin=#{info.origin} where MsgFileID=#{info.fileNam}")
    void followupReWrite(@Param("info") MessageInfo info);

    @Update("update Exchange_PatReportCard with(rowlock) set ReceivedTime=#{info.gwReceiveTime}, WriteTime=#{info.writeTime}, IsLocal=#{info.islocal}, Status=#{info.extensionInfo.result}, ErrMsg=#{info.extensionInfo.desc}" +
            ", Origin=#{info.origin} where MsgFileID=#{info.fileNam}")
    void caseReportReWrite(@Param("info") MessageInfo info);

    @Update("update Exchange_PatLeaveCard with(rowlock) set ReceivedTime=#{info.gwReceiveTime}, WriteTime=#{info.writeTime}, IsLocal=#{info.islocal}, Status=#{info.extensionInfo.result}, ErrMsg=#{info.extensionInfo.desc}" +
            ", Origin=#{info.origin} where MsgFileID=#{info.fileNam}")
    void leftCardReWrite(@Param("info") MessageInfo info);

    @Update("update Exchange_PatEmergency with(rowlock) set ReceivedTime=#{info.gwReceiveTime}, WriteTime=#{info.writeTime}, IsLocal=#{info.islocal}, Status=#{info.extensionInfo.result}, ErrMsg=#{info.extensionInfo.desc}" +
            ", Origin=#{info.origin} where MsgFileID=#{info.fileNam}")
    void emergencyReWrite(@Param("info") MessageInfo info);

}
