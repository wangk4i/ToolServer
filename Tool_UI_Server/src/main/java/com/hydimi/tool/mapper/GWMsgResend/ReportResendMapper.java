package com.hydimi.tool.mapper.GWMsgResend;

import com.hydimi.tool.domain.dto.SPMReportDTO;
import com.hydimi.tool.domain.info.InfoQueryInput;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/10/27 17:59
 */
@Mapper
public interface ReportResendMapper {

    @Select("select top 2000 * from SPM_SPMPatInciRpt(nolock) where IDCode=#{input.IDCode} and SyncStatus=#{input.syncStatus} and DelStatus='DelLogo001' and State='1'")
    List<SPMReportDTO> reportInfoQueryByIdcode(@Param("input") InfoQueryInput input, String provinceCode);

    @Select("<script>" +
            "select top 2000 * from SPM_SPMPatInciRpt(nolock) " +
            "where DelStatus='DelLogo001' and State='1' and SyncStatus=#{input.syncStatus} and ZoneCd like '${provinceCode}%' " +
            "and SyncTime between #{input.starttime} and #{input.endtime} " +
            "<if test = 'input.syncStatus==1'>and len(isnull(SyncError,''))>0 </if>"+
            "<if test = 'input.SyncErr!=null '> and SyncError like '%${input.SyncErr}%' escape '['</if>" +
            "</script>")
    List<SPMReportDTO> reportInfoQuery(@Param("input") InfoQueryInput input, String provinceCode);

    @Select("select distinct top 2000 SyncError from SPM_SPMPatInciRpt(nolock) " +
            "where DelStatus='DelLogo001' and State='1' and SyncStatus=#{input.syncStatus} and ZoneCd like '${provinceCode}%' " +
            "and len(isnull(SyncError,''))>0 and SyncTime between #{input.starttime} and #{input.endtime}")
    List<String> syncErrorQuery(@Param("input") InfoQueryInput input, String provinceCode);

    @Select("select top 2000 * from SPM_SPMPatInciRpt(nolock) " +
            "where len(isnull(SyncStatus,''))=0 and DelStatus='DelLogo001' and State='1' and ZoneCd like '${provinceCode}%' " +
            "and (CreTime between #{input.starttime} and #{input.endtime} or LastModTime between #{input.starttime} and #{input.endtime})")
    List<SPMReportDTO> reportNotSyncQuery(@Param("input") InfoQueryInput input, String provinceCode);

}
