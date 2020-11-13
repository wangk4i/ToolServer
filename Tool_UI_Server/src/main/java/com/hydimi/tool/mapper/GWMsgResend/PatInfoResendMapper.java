package com.hydimi.tool.mapper.GWMsgResend;

import com.hydimi.tool.domain.dto.SPMPatDTO;
import com.hydimi.tool.domain.info.PatInfoQueryInput;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/10/22 18:19
 */
@Mapper
public interface PatInfoResendMapper {
    @Select("select top 2000 * from SPM_SPMPatInfoMana(nolock) where IDCode=#{input.IDCode} and DelStatus='DelLogo001' and State='1' and SyncStatus=#{input.syncStatus}")
    List<SPMPatDTO> patInfoQueryByIdcode(@Param("input") PatInfoQueryInput input, String provinceCode);

    @Select("<script> " +
            "select top 2000 * from SPM_SPMPatInfoMana(nolock) " +
            "where DelStatus='DelLogo001' and State='1' and SyncStatus=#{input.syncStatus} and ZoneCd like '${provinceCode}%' and SyncTime between #{input.starttime} and #{input.endtime} " +
            "<if test = 'input.syncStatus==1'>and len(isnull(SyncError,''))>0 </if>"+
            "<if test = 'input.SyncErr!=null '> and SyncError like '%${input.SyncErr}%' escape '['</if>" +
            "</script>")
    List<SPMPatDTO> patInfoQuery(@Param("input") PatInfoQueryInput input, String provinceCode);

    @Select("select distinct top 2000 SyncError from SPM_SPMPatInfoMana(nolock) " +
            "where DelStatus='DelLogo001' and State='1' and SyncStatus=#{input.syncStatus} and ZoneCd like '${provinceCode}%' and len(isnull(SyncError,''))>0 and SyncTime between #{input.starttime} and #{input.endtime} ")
    List<String> syncErrorQuery(@Param("input") PatInfoQueryInput input, String provinceCode);

    @Select("select top 2000 * from SPM_SPMPatInfoMana(nolock) " +
            "where len(isnull(SyncStatus,''))=0 and DelStatus='DelLogo001' and State='1' and ZoneCd like '${provinceCode}%' " +
            "and (CreTime between #{input.starttime} and #{input.endtime} or LastModTime between #{input.starttime} and #{input.endtime}) ")
    List<SPMPatDTO> patNotSyncQuery(@Param("input") PatInfoQueryInput input, String provinceCode);

    @Update("update SPM_SPMPatInfoMana with(rowlock) set InspectYear= year(getdate())-1, LastModTime=convert(varchar(20), getdate(), 120) where Cd=#{cd}")
    void resetInspectYear(String cd);
}
