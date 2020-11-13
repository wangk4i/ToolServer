package com.hydimi.tool.mapper;

import com.hydimi.tool.domain.dto.SPMMoveOutPatDTO;
import com.hydimi.tool.domain.vo.FollowupCardVO;
import com.hydimi.tool.domain.vo.PatInfoCardVO;
import com.hydimi.tool.domain.vo.SPMUserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/7 10:28
 */
@Mapper
public interface SyncPatFollowupMapper {

    String queryOutPatListSql1 = "select * from (\n" +
            "\t                select a.Cd,PatNam,ZoneNam,OrgNam,IDCode,PatCode,e.Nam as GenderCd,BirthDate,InspectYear,a.SyncError,b.OutDate,b.InDate from SPM_SPMPatInfoMana(nolock) a \n" +
            "\t                inner join SPM_SPMPatMoveOut(nolock) b on a.Cd = b.PatInfoCd\n" +
            "\t                left join SPM_SPMDict e on e.Cd = a.GenderCd\n" +
            "\t                where left(b.InZoneCd,2) != '{0}' and b.MoveOutCd='OutType001' and b.MoveStatusCd = 'FlowState002' and b.DelStatus = 'DelLogo001' and left(OutDate,7) = #{Year} and a.State = '1'\n" +
            "\t                ) c \n" +
            "\t                left join\n" +
            "\t                (\n" +
            "\t\t                select PatInfoCd,sum(case when len(isnull(SyncError,'')) >0 then 1 else 0 end) as FollowError,sum(case when len(isnull(SyncTime,'')) = 0 then 1 else 0 end) as FollowNoSync \n" +
            "\t\t                from SPM_SPMPatFollowup(nolock) group by PatInfoCd \n" +
            "\t                ) d on c.Cd = d.PatInfoCd order by OutDate desc offset #{page}  rows fetch next #{limit} rows only";

    String queryOutPatListSql="select a.Cd,PatNam,IDCode,c.Nam AS GenderCd,OutZoneNam,OutOrgNam,InOrgNam,OutDate,InspectYear,b.SyncError from SPM_SPMPatInfoMana(nolock) a\n" +
            "left join SPM_SPMPatMoveOut(nolock) b on b.PatInfoCd=a.Cd\n" +
            "left join SPM_SPMDict(nolock) c on c.Cd=a.GenderCd\n" +
            "where left(OutDate,7)=#{Year} and SUBSTRING((select Cd from SPM_SPMZone where Cd=InZoneCd),1,2)!=#{ProvinceCode} \n" +
            "and b.MoveOutCd='OutType001' and b.MoveStatusCd = 'FlowState002'";

    String queryPatCard = "select a.Cd,PatNam,ZoneNam,OrgNam,IDCode,PatCode,b.Nam as GenderCd,BirthDate,InspectYear,SyncError,a.SyncTime,c.Nam as DelStatus,d.Nam as BaseStatusCd from SPM_SPMPatInfoMana(nolock) a \n" +
            "left join SPM_SPMDict(nolock) b  on b.Cd = a.GenderCd\n" +
            "left join SPM_SPMDict(nolock) c on c.Cd = a.DelStatus\n" +
            "left join SPM_SPMDict(nolock) d on d.Cd = a.BaseStatusCd\n";
    String queryPatCardByIdcode = queryPatCard + " where a.IDCode= #{Cd}";
    String queryPatCardByCd = queryPatCard + " where a.Cd= #{Cd}";

    String queryFollowupCard = "select top 100 a.Cd,FollowupDate,NextDate,b.ZoneCd,b.OrganCd,c.Nam as CaseClassCd,a.FIELDPK,d.Nam as DelStatus,case when a.SyncStatus='1' then '成功' when a.SyncStatus='0' then '正在同步' else '未同步' end as SyncStatus,a.SyncTime,a.SyncError from SPM_SPMPatFollowup(nolock) a \n" + // when a.SyncStatus='' then '准备同步'
            "left join SPM_SPMPatInfoMana(nolock) b  on b.Cd = a.PatInfoCd \n" +
            "left join SPM_SPMDict(nolock) c  on c.Cd = a.CaseClassCd \n" +
            "left join SPM_SPMDict(nolock) d  on d.Cd = a.DelStatus";
    String queryFollowupCardByIdcode = queryFollowupCard + " where b.IDCode = #{Cd} and a.State='1' order by a.FollowupDate desc";
    String queryFollowupCardByCd = queryFollowupCard + " where b.Cd = #{Cd} and a.State='1' order by a.FollowupDate desc";


    @Select(queryOutPatListSql)
    List<SPMMoveOutPatDTO> QueryOutPatList(String Year, String ProvinceCode);

    @Select(queryPatCardByIdcode)
    List<PatInfoCardVO> QueryPatInfoCardByIdcode(String Cd);

    @Select(queryPatCardByCd)
    List<PatInfoCardVO> QueryPatInfoCardByCd(String Cd);

    @Select(queryFollowupCardByIdcode)
    List<FollowupCardVO> QueryPatFollowupCardByIdcode(String Cd);

    @Select(queryFollowupCardByCd)
    List<FollowupCardVO> QueryPatFollowupCardByCd(String Cd);

    @Update("update SPM_SPMPatFollowup with(rowlock) set DelStatus='DelLogo002', LastModTime= convert(varchar(20), getdate(), 120) where Cd=#{Cd}")
    void delFollowupInfo(String Cd);

}
