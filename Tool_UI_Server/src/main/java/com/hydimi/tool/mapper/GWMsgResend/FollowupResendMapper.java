package com.hydimi.tool.mapper.GWMsgResend;

import com.hydimi.tool.domain.dto.SPMFollowupDTO;
import com.hydimi.tool.domain.info.FollowupInfoQueryInput;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/10/22 18:20
 */
@Mapper
public interface FollowupResendMapper {
    @Select("select top 2000 p.IDCode,p.PatNam,p.ZoneCd,p.ZoneNam,p.OrganCd,p.OrgNam,p.InformedConsCd,f.* from SPM_SPMPatFollowup(nolock) f\n" +
            "left join SPM_SPMPatInfoMana(nolock) p on f.PatInfoCd=p.Cd\n" +
            "where  p.IDCode=#{input.IDCode} and f.SyncStatus=#{input.syncStatus} and f.DelStatus='DelLogo001' ")
    List<SPMFollowupDTO> followupInfoQueryByIdcode(@Param("input") FollowupInfoQueryInput input, String provinceCode);

    @Select("<script> " +
            "select top 2000 IDCode,PatNam,ZoneCd,ZoneNam,OrganCd,OrgNam,InformedConsCd,f.* from SPM_SPMPatFollowup(nolock) f\n" +
            "left join SPM_SPMPatInfoMana(nolock) p on f.PatInfoCd=p.Cd\n" +
            "where f.State='1' and f.DelStatus='DelLogo001' and f.SyncStatus=#{input.syncStatus} and p.ZoneCd like '${provinceCode}%' and f.SyncTime between #{input.starttime} and #{input.endtime} \n" +
            "<if test = 'input.syncStatus==1'>and len(isnull(f.SyncError,''))>0 </if>"+
            "<if test = 'input.SyncErr!=null '> and f.SyncError like '%${input.SyncErr}%' escape '['</if>" +
            "</script>")
    List<SPMFollowupDTO> followupInfoQuery(@Param("input") FollowupInfoQueryInput input, String provinceCode);

    @Select("select distinct top 2000 f.SyncError from SPM_SPMPatFollowup(nolock) f\n" +
            "left join SPM_SPMPatInfoMana(nolock) p on f.PatInfoCd=p.Cd \n" +
            "where f.State='1' and f.DelStatus='DelLogo001' and f.SyncStatus=#{input.syncStatus} and p.ZoneCd like '${provinceCode}%' and len(isnull(f.SyncError,''))>0 and f.SyncTime between #{input.starttime} and #{input.endtime} ")
    List<String> followSyncErrorQuery(@Param("input") FollowupInfoQueryInput input, String provinceCode);

    @Select("select top 2000 p.IDCode,p.PatNam,p.ZoneCd,p.ZoneNam,p.OrganCd,p.OrgNam,p.InformedConsCd,f.* from SPM_SPMPatFollowup(nolock) f " +
            "left join SPM_SPMPatInfoMana(nolock) p on f.PatInfoCd=p.Cd " +
            "where f.State='1' and f.DelStatus='DelLogo001' and len(isnull(f.SyncStatus,''))=0 and " +
            "p.ZoneCd like '${provinceCode}%' and (f.CreTime between #{input.starttime} and #{input.endtime} or f.LastModTime between #{input.starttime} and #{input.endtime})")
    List<SPMFollowupDTO> followupNotSyncQuery(@Param("input") FollowupInfoQueryInput input, String provinceCode);
}
