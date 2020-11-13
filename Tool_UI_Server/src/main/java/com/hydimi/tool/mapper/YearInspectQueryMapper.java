package com.hydimi.tool.mapper;

import com.hydimi.tool.domain.info.InspectYearInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/11/9 11:11
 */
@Mapper
public interface YearInspectQueryMapper {
    @Select("select isnull(InspectYear,'') as InspectYear,count(1) as num from SPM_SPMPatInfoMana(nolock) \n" +
            "where DelStatus = 'DelLogo001' and State=1 and ZoneCd like '${provinceCode}%' and BaseStatusCd in ('InState001','InState003','InState004') group by InspectYear order by InspectYear desc")
    List<InspectYearInfo> queryPatInspectYear(@Param("provinceCode") String provinceCode);

    @Select("select isnull(SyncError,'') as SyncError,count(1) as num from SPM_SPMPatInfoMana(nolock) \n" +
            "where DelStatus='DelLogo001' and State=1 and ZoneCd like '${provinceCode}%' and BaseStatusCd in ('InState001','InState003','InState004') and isnull(InspectYear,'')!= #{inspectYear} group by SyncError order by num desc")
    List<InspectYearInfo> queryPatInspectYearDetails(@Param("provinceCode") String provinceCode,@Param("inspectYear") String inspectYear);

    @Select("select isnull(InspectYear,'') as InspectYear,count(1) as num from SPM_SPMPatInfoMana(nolock) \n" +
            "where State=1 and ZoneCd like '${provinceCode}%' and BaseStatusCd ='InState002' group by InspectYear order by InspectYear desc")
    List<InspectYearInfo> queryPatInspectYearDeath(@Param("provinceCode") String provinceCode);

    @Select("select isnull(InspectYear,'') as InspectYear,count(1) as num from SPM_SPMPatInfoMana(nolock) \n" +
            "where DelStatus='DelLogo002' and State=1 and ZoneCd like '${provinceCode}%' group by InspectYear order by InspectYear desc")
    List<InspectYearInfo> queryPatInspectYearDelete(@Param("provinceCode") String provinceCode);


}
